package com.example.girondins.workit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Girondins on 18/10/16.
 */

public class MonthAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private String[] months;
    private Context context;

    public MonthAdapter(Context context, String[] months){
        super(context,R.layout.month_view,months);
        this.months = months;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView monthView;

        if(convertView==null){
            convertView = (LinearLayout)inflater.inflate(R.layout.month_view,parent,false);
        }

        monthView = (TextView) convertView.findViewById(R.id.selMonthID);

        monthView.setText(months[position]);




        return convertView;
    }
}
