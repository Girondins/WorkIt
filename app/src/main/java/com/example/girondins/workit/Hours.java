package com.example.girondins.workit;

import java.util.Comparator;

/**
 * Created by Girondins on 04/10/16.
 */

public class Hours implements Comparable<Hours>{
    private int hour;
    private String date;

    public Hours(int hour, String date){
        this.hour = hour;
        this.date = date;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public int getHour(){
        return this.hour;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }


    @Override
    public int compareTo(Hours another) {
        String[] split = date.split("x");
        int day = Integer.parseInt(split[2]);
        String[] split2 = another.getDate().split("x");
        int day2 = Integer.parseInt(split2[2]);
        if (day > day2) {
            return 1;
        }
        else if (day <  day2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
