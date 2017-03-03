package com.example.girondins.workit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Girondins on 06/10/16.
 */

public class AddHoursDialog extends DialogFragment {
    private LayoutInflater inflater;
    private View v,title;
    private Controller cont;
    private EditText hours,minutes;
    private Person person;
    private DatePicker dp;

    public void setController(Controller cont, Person person){
        this.cont = cont;
        this.person = person;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.add_hours, null);
        title = inflater.inflate(R.layout.titlexml,null);

        minutes = (EditText) v.findViewById(R.id.enterMinuteID);
        hours = (EditText) v.findViewById(R.id.enteHourID);
        dp = (DatePicker) v.findViewById(R.id.datePickerID);

        minutes.setFilters(new InputFilter[]{new InputFilterMinMax(01,60)});
        hours.setFilters(new InputFilter[]{new InputFilterMinMax(01,24)});



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setCustomTitle(title);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
          //      cont.addNewWorker(name.getText().toString());
                int day = dp.getDayOfMonth();
                int month = dp.getMonth();
                int year = dp.getYear();
                int h;
                int m;

                if(hours.getText().toString().matches("")){
                    h = 0;
                }else{
                    h = Integer.parseInt(hours.getText().toString());
                }

                if(minutes.getText().toString().matches("")){
                    m = 0;
                }else{
                    m = Integer.parseInt(minutes.getText().toString());
                }
                String date = year+"x"+month+"x"+day;

                cont.addHours(h,m ,person.getName(),date);

            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    private class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }


        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
