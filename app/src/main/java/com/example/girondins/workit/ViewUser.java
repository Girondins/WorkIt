package com.example.girondins.workit;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewUser extends Fragment {
    private Controller cont;
    private Activity act;
    private Person person;
    private int cYear;
    private int cMonth;
    private String monthText, yearText;
    private ListView hours;
    private TextView income,worked,worker,year,month;
    private  Button addWork,edit;
    private LinearLayout mon;


    public ViewUser() {
        // Required empty public constructor
    }

    public void setController(Controller cont, Activity act, Person person) {
        this.cont = cont;
        this.act = act;
        this.person = person;
        Calendar c = Calendar.getInstance();
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        yearText = Integer.toString(cYear);
        convertMonth();

        Log.d(this.person.getName(), this.person.getWage() + " : " + this.person.getCalcmonth());
    }

    public void convertMonth() {
        if (cMonth == 0) {
            monthText = act.getString(R.string.january);
        }
        if (cMonth == 1) {
            monthText = act.getString(R.string.february);
        }
        if (cMonth == 2) {
            monthText = act.getString(R.string.march);
        }
        if (cMonth == 3) {
            monthText = act.getString(R.string.april);
        }
        if (cMonth == 4) {
            monthText = act.getString(R.string.march);
        }
        if (cMonth == 5) {
            monthText = act.getString(R.string.june);
        }
        if (cMonth == 6) {
            monthText = act.getString(R.string.july);
        }
        if (cMonth == 7) {
            monthText = act.getString(R.string.august);
        }
        if (cMonth == 8) {
            monthText = act.getString(R.string.september);
        }
        if (cMonth == 9) {
            monthText = act.getString(R.string.october);
        }
        if (cMonth == 10) {
            monthText = act.getString(R.string.november);
        }
        if (cMonth == 11) {
            monthText = act.getString(R.string.december);
        }
    }

    public int monthNumber(String month) {

        if (month.equals(act.getString(R.string.january))) {
            return 0;
        }
        if (month.equals(act.getString(R.string.february))) {
            return 1;
        }
        if (month.equals(act.getString(R.string.march))) {
            return 2;
        }
        if (month.equals(act.getString(R.string.april))) {
            return 3;
        }
        if (month.equals(act.getString(R.string.may))) {
            return 4;
        }
        if (month.equals(act.getString(R.string.june))) {
            return 5;
        }
        if (month.equals(act.getString(R.string.july))) {
            return 6;
        }
        if (month.equals(act.getString(R.string.august))) {
            return 7;
        }
        if (month.equals(act.getString(R.string.september))) {
            return 8;
        }
        if (month.equals(act.getString(R.string.october))) {
            return 9;
        }
        if (month.equals(act.getString(R.string.november))) {
            return 10;
        }
        if (month.equals(act.getString(R.string.december))) {
            return 11;
        }

        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_user, container, false);
        mon = (LinearLayout) v.findViewById(R.id.monthLayout);
        year = (TextView) v.findViewById(R.id.yearView);
        month = (TextView) v.findViewById(R.id.monthView);
        edit = (Button) v.findViewById(R.id.editBtn);
        addWork = (Button) v.findViewById(R.id.addWorkHoursBtn);
        worker = (TextView) v.findViewById(R.id.workerName);
        income = (TextView) v.findViewById(R.id.incView);
        worked = (TextView) v.findViewById(R.id.workedView);
        hours = (ListView) v.findViewById(R.id.hourListID);
        hours.setAdapter(new HoursAdapter(act,cont.convertToArray(person.getAllHours(),monthNumber(monthText)+ "",yearText),cont));
        worker.setText(person.getName());
        Log.d("CONVERTED MONTH NUMBER: " + monthText + " IS ", monthNumber(monthText) + "");
        income.setText("Earned: " + person.getWage() * cont.getTotalMonth(person.getAllHours(),monthNumber(monthText) + "", yearText) + " SEK" );
        worked.setText("Hours worked: " + cont.getTotalMonth(person.getAllHours(),monthNumber(monthText)+"", yearText) + "/" + person.getCalcmonth());
        addWork.setOnClickListener(new addNewWorkTime());
        mon.setOnClickListener(new ChangeMonth());
        year.setText(cYear + "");
        month.setText(monthText);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            cont.editName(person.getName());
            }
        });
        Typeface customFont = Typeface.createFromAsset(act.getAssets(), "fonts/avocado.ttf");
        Typeface secondFont = Typeface.createFromAsset(act.getAssets(), "fonts/coredo.ttf");
        worker.setTypeface(customFont);
        income.setTypeface(secondFont);
        worked.setTypeface(secondFont);
        year.setTypeface(secondFont);
        month.setTypeface(customFont);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    cont.setCurrentMonth();
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    public void updateHours(Person person){
        this.person = person;
        hours.setAdapter(new HoursAdapter(act, cont.convertToArray(person.getAllHours(),monthNumber(monthText)+"",yearText),cont));
        income.setText("Earned: " + person.getWage() * cont.getTotalMonth(person.getAllHours(),monthNumber(monthText) + "",yearText) + " SEK" );
        worked.setText("Hours worked: " + cont.getTotalMonth(person.getAllHours(),monthNumber(monthText)+"",yearText) + "/" + person.getCalcmonth());
    }

    public void updadteMonth(String months, String years){
        monthText = months;
        yearText = years;
        month.setText(monthText);
        year.setText(yearText);
        hours.setAdapter(new HoursAdapter(act, cont.convertToArray(person.getAllHours(),monthNumber(monthText)+"",yearText),cont));
        income.setText("Earned: " + person.getWage() * cont.getTotalMonth(person.getAllHours(),monthNumber(monthText) + "",yearText) + " SEK" );
        worked.setText("Hours worked: " + cont.getTotalMonth(person.getAllHours(),monthNumber(monthText)+"",yearText) + "/" + person.getCalcmonth());
    }

    private class addNewWorkTime implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cont.initAddHoursDialog(person);
        }
    }

    private class ChangeMonth implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            cont.changeMonth();
        }
    }



}
