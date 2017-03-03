package com.example.girondins.workit;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Girondins on 06/10/16.
 */

public class HoursAdapter extends ArrayAdapter<Hours> {
    private LayoutInflater inflater;
    private Hours[] hours;
    private Context context;
    private Controller cont;

    public HoursAdapter(Context context, Hours[] hours, Controller cont){
        super(context,R.layout.hourslayout,hours);
        this.hours = hours;
        this.cont = cont;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView hourswork, dateview;

        if(convertView==null){
            convertView = (LinearLayout)inflater.inflate(R.layout.hourslayout,parent,false);
        }
        Typeface secondFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/coredo.ttf");
        hourswork = (TextView) convertView.findViewById(R.id.hoursWorkedViewID);
        dateview = (TextView) convertView.findViewById(R.id.dateViewID);

        hourswork.setTypeface(secondFont);
        dateview.setTypeface(secondFont);
        hourswork.setText(cont.convertToHours(this.getItem(position).getHour()) + " / h");
        dateview.setText(hours[position].getDate());




        return convertView;
    }
}
