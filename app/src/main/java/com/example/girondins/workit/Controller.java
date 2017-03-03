package com.example.girondins.workit;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TimeZone;

/**
 * Created by Girondins on 04/10/16.
 */

public class Controller {
    private UserDBHelper db;
    private LinkedList<Person> workers;
    private Activity activity;
    private Person[] persons;
    private Start start;
    private ViewUser view;

    public Controller(Activity activity){
        this.activity = activity;
        db = new UserDBHelper(activity);
        start = new Start();
        initateWorkersFromDB();
        start.setCont(this,activity,persons);
        setFragment(start,false);
    }

    public void initateWorkersFromDB(){
        workers = db.getUsers();
        if(workers.size() != 0) {
            persons = new Person[workers.size()];
            for (int i = 0; i < workers.size(); i++) {
                persons[i] = workers.get(i);
            }
        }
    }

    public void setFragment(Fragment frag, boolean backstack){
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainLay, frag);
        if(backstack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void addNewWorker(String name, double wage, int workmonth){
        db.addWorker(name,wage,workmonth);
        initateWorkersFromDB();
        start.updateAdapter(persons);

    }

    public void initAddDialog(){
        AddPersonDialog adp = new AddPersonDialog();
        adp.setController(this);
        adp.setStyle(DialogFragment.STYLE_NO_FRAME,0);
        adp.show(activity.getFragmentManager(), "Add");
    }

    public void initAddHoursDialog(Person person){
        AddHoursDialog ahd = new AddHoursDialog();
        ahd.setController(this,person);
        ahd.show(activity.getFragmentManager(), "Add Hours");
    }

    public void viewWorker(Person person){
        view = new ViewUser();
        view.setController(this,activity,person);
        setFragment(view,true);
    }

    public void addHours(int hour, int minute, String person, String date){
        if(checkIfAlreadyExists(person,date) == false) {
            Log.d("Date is", date);
            db.addNewHour(person, convertWork(hour, minute), date);
            initateWorkersFromDB();
            start.updateAdapter(persons);
            view.updateHours(findPerson(person));
        }else{
            Toast.makeText(activity,R.string.hoursexist,Toast.LENGTH_SHORT).show();
        }

    }


    public int getYear(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.YEAR);
    }
    public int getMonth(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.MONTH);
    }
    public int getDay(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getToday(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        String today;
        today = calendar.get(Calendar.YEAR) + "x" + calendar.get(Calendar.MONTH) + "x" + calendar.get(Calendar.DAY_OF_MONTH);
        return today;
    }

    public double getTotalMonth(LinkedList<Hours> allHours, String month, String year){
        String[] split;
        double amount = 0;
        for(int i = 0; i<allHours.size(); i++){
          split = allHours.get(i).getDate().split("x");
            if(split[0].equals(year)) {
                if (split[1].equals(month)) {
                    amount = amount + allHours.get(i).getHour();
                }
            }
        }
        return convertToHours(amount);
    }

    public void changeMonth(){
        MonthDialog monthDial = new MonthDialog();
        monthDial.setComponents(activity,this);
        monthDial.show(activity.getFragmentManager(),"Change");
    }

    public void setCurrentMonth(){
        LinearLayout relative = (LinearLayout) activity.findViewById(R.id.mainLayout);
        if (getMonth() == 0) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.january);

        }
        if (getMonth() == 1) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.february);

        }
        if (getMonth() == 2) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.march);
        }
        if (getMonth() == 3) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.april);
        }
        if (getMonth() == 4) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.may);

        }
        if (getMonth() == 5) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.june);

        }
        if (getMonth() == 6) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.july);

        }
        if (getMonth() == 7) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.august);

        }
        if (getMonth() == 8) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.september);

        }
        if (getMonth() == 9) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.october);

        }
        if (getMonth() == 10) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.november);

        }
        if (getMonth() == 11) {
            start = new Start();
            initateWorkersFromDB();
            start.setCont(this,activity,persons);
            setFragment(start,false);
            relative.setBackgroundResource(R.drawable.december);


        }
    }

    public void updateMonth(String month, String year){
        view.updadteMonth(month, year);
        LinearLayout relative = (LinearLayout) activity.findViewById(R.id.mainLayout);

        if (month.equals(activity.getString(R.string.january))) {
            relative.setBackgroundResource(R.drawable.january);

        }
        if (month.equals(activity.getString(R.string.february))) {
            relative.setBackgroundResource(R.drawable.february);

        }
        if (month.equals(activity.getString(R.string.march))) {
            relative.setBackgroundResource(R.drawable.march);
        }
        if (month.equals(activity.getString(R.string.april))) {
            relative.setBackgroundResource(R.drawable.april);
        }
        if (month.equals(activity.getString(R.string.may))) {
            relative.setBackgroundResource(R.drawable.may);

        }
        if (month.equals(activity.getString(R.string.june))) {
            relative.setBackgroundResource(R.drawable.june);

        }
        if (month.equals(activity.getString(R.string.july))) {
            relative.setBackgroundResource(R.drawable.july);

        }
        if (month.equals(activity.getString(R.string.august))) {
            relative.setBackgroundResource(R.drawable.august);

        }
        if (month.equals(activity.getString(R.string.september))) {
            relative.setBackgroundResource(R.drawable.september);

        }
        if (month.equals(activity.getString(R.string.october))) {
            relative.setBackgroundResource(R.drawable.october);

        }
        if (month.equals(activity.getString(R.string.november))) {
            relative.setBackgroundResource(R.drawable.november);

        }
        if (month.equals(activity.getString(R.string.december))) {
            relative.setBackgroundResource(R.drawable.december);


        }
    }

    public void editName(String person){
        EditPersonDialog editP = new EditPersonDialog();
        editP.setComp(activity,this,person);
        editP.show(activity.getFragmentManager(),"Edit");
    }


    public int convertWork(int hour, int minutes){
      int totalMinutes, hoursInMinutes;
        hoursInMinutes = hour * 60;
        totalMinutes = hoursInMinutes + minutes;
        return  totalMinutes;
    }

    public double convertToHours(double minutes){
        double convert = minutes/60;
            return Math.floor(convert * 100) / 100;
    }

    public boolean checkIfAlreadyExists(String person,String date){

        for(int i = 0 ; i<persons.length; i++){
            if(persons[i].getName().equals(person)){
                Log.d(person,persons[i].getName());
               return persons[i].checkIfExists(date);
            }
        }


        return false;
    }

    public Hours[] convertToArray(LinkedList<Hours> hours, String month, String year){
        String[] split;
        LinkedList<Hours> monthHours = new LinkedList<Hours>();

        for(int i = 0; i<hours.size(); i++){
            split = hours.get(i).getDate().split("x");
            if(split[0].equals(year)) {
                if (split[1].equals(month)) {
                    monthHours.add(hours.get(i));
                }
            }
        }
        Collections.sort(monthHours);
        Hours[]converted = monthHours.toArray(new Hours[monthHours.size()]);
        return converted;
    }

    public Person findPerson(String person){
        for(int i = 0; i<persons.length; i++){
            if(persons[i].getName().equals(person)){
                return persons[i];
            }
        }
        return null;
    }
}
