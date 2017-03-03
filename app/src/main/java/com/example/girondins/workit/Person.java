package com.example.girondins.workit;

import android.util.Log;

import java.util.LinkedList;

/**
 * Created by Girondins on 01/10/16.
 */

public class Person {
    private String name;
    private double wage;
    private int calcmonth;
    private LinkedList<Hours> allHours = new LinkedList<Hours>();

    public Person(String name, double wage, int calcmonth){
        this.name = name;
        this.wage = wage;
        this.calcmonth = calcmonth;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }


    public LinkedList<Hours> getAllHours(){
        return this.allHours;
    }

    public void addHours(Hours hour){
        allHours.add(hour);
    }

    public void setWage(double wage){
        this.wage = wage;
    }

    public double getWage(){
        return this.wage;
    }

    public void setCalcmonth(int calcmonth){
        this.calcmonth = calcmonth;
    }

    public int getCalcmonth(){
        return this.calcmonth;
    }

    public boolean checkIfExists(String date){
        for(int i=0; i<allHours.size(); i++){
            Log.d("HOURSsssssss", allHours.get(i).getDate());
            if(allHours.get(i).getDate().equals(date)){
                Log.d("FOUND", "SAMEEE");
                return true;
            }
        }
        return false;
    }

}
