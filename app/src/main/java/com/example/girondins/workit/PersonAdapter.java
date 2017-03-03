package com.example.girondins.workit;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Girondins on 01/10/16.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    private LayoutInflater inflater;
    private Person[] persons;
    private Controller cont;
    private Context context;
    private Person person;

    public PersonAdapter(Context context, Person[] persons, Controller cont){
        super(context,R.layout.personlayout,persons);
        this.persons = persons;
        this.cont = cont;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView viewPerson,viewHours;
        Button addHrs;

        if(convertView==null){
            convertView = (LinearLayout)inflater.inflate(R.layout.personlayout,parent,false);
        }

        addHrs = (Button) convertView.findViewById(R.id.addHoursBtn);
        viewPerson = (TextView) convertView.findViewById(R.id.nameView);
        viewHours = (TextView) convertView.findViewById(R.id.calcMonthTVID);
        Typeface customFont = Typeface.createFromAsset(context.getAssets(), "fonts/avocado.ttf");
        viewPerson.setTypeface(customFont);


        addHrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("This Is Click", persons[position].getName());
                person = persons[position];
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, TimePickerDialog.THEME_HOLO_DARK, new onTimeSet(), 0, 0, true);
                timePickerDialog.setTitle("Add Todays Hours");
                timePickerDialog.show();

            }
        });
        double currentMonth = cont.getTotalMonth(this.getItem(position).getAllHours(),cont.getMonth()+"", cont.getYear()+"");
        viewPerson.setText(this.getItem(position).getName());
        viewHours.setText( currentMonth + " / " + this.getItem(position).getCalcmonth());

        return convertView;
    }
    private class onTimeSet implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cont.addHours(hourOfDay,minute,person.getName(), cont.getToday());

        }
    }


}
