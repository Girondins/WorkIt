package com.example.girondins.workit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.Calendar;

/**
 * Created by Girondins on 18/10/16.
 */

public class MonthDialog extends DialogFragment{

    private static final int MAX_YEAR = 2100;
    private Activity act;
    private ViewUser viewUse;
    private String selected;
    private Controller cont;

    public void setComponents(Activity act, Controller cont) {
        this.act = act;
        this.cont = cont;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        final String[] months = {act.getString(R.string.january),act.getString(R.string.february), act.getString(R.string.march)
                ,act.getString(R.string.april),act.getString(R.string.may),act.getString(R.string.june),act.getString(R.string.july)
                ,act.getString(R.string.august),act.getString(R.string.september),act.getString(R.string.october),act.getString(R.string.november),
                act.getString(R.string.december)};

        View dialog = inflater.inflate(R.layout.month_dialog, null);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);
        ListView monthsList = (ListView) dialog.findViewById(R.id.monthList);

        monthsList.setAdapter(new MonthAdapter(act,months));
        monthsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selected = months[position];
            }
        });

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(MAX_YEAR);
        yearPicker.setValue(year);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        cont.updateMonth(selected,Integer.toString(yearPicker.getValue()));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}

