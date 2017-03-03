package com.example.girondins.workit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by Girondins on 14/09/15.
 * Klass som pratar med databas
 */
public class UserDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_PERSON ="person";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WAGE = "wage";
    public static final String COLUMN_CALCMONTH = "calcmonth";

    public static final String TABLE_HOURS = "hours";
    public static final String COLUMN_WORK = "work";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WORKER = "worker";


    private static final String DATABASE_NAME = "workingitttttt.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_PERSON = "CREATE TABLE " + TABLE_PERSON + "(" +
                                                    COLUMN_NAME + " text not null primary key, " +
                                                    COLUMN_WAGE + " double , " +
                                                    COLUMN_CALCMONTH + " integer);";


    private static final String DATABASE_CREATE_HOURS = "CREATE TABLE " + TABLE_HOURS + "(" +
                                                     COLUMN_DATE + " text not null, " +
                                                     COLUMN_WORK + " integer , " +
                                                     COLUMN_WORKER + " text, FOREIGN KEY ("+COLUMN_WORKER+") " +
                                                    "REFERENCES "+TABLE_PERSON+"("+COLUMN_NAME+"), primary key " +
                                                        "("+COLUMN_DATE+"," +COLUMN_WORKER +"));";



    private LinkedList<Person> inputs = new LinkedList<Person>();




    public UserDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PERSON);
        db.execSQL(DATABASE_CREATE_HOURS);

    }

    /**
     * Metod som kallas ifall databasen redan existerar
     * @param db databas
     * @param oldVersion äldreversion
     * @param newVersion nya versionen
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserDBHelper.class.getName(),"Updating database from version" + oldVersion + "to"
                                            + newVersion + ", which will replace all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_HOURS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_HOURS);
        onCreate(db);

    }

    public void addWorker(String name,double wage, int monthhour){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDBHelper.COLUMN_NAME,name);
        values.put(UserDBHelper.COLUMN_WAGE,wage);
        values.put(UserDBHelper.COLUMN_CALCMONTH,monthhour);
        db.insert(UserDBHelper.TABLE_PERSON,"",values);
        Log.d("Adding Worker", name);
    }

    public void removeWorker(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PERSON,UserDBHelper.COLUMN_NAME + "= ?",new String[]{name});
    }

    public void editWorker(String name,double wage,int calcmonth){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(wage != -1){
            cv.put(UserDBHelper.COLUMN_WAGE,wage);
        }
        if(calcmonth != -1) {
            cv.put(UserDBHelper.COLUMN_CALCMONTH, calcmonth);
        }

        db.update(UserDBHelper.TABLE_PERSON,cv,UserDBHelper.COLUMN_NAME + " = ?",new String []{name});


    }

    public void addNewHour(String name, int hours, String date){
        SQLiteDatabase db = getWritableDatabase();
        String statement = "INSERT INTO " + UserDBHelper.TABLE_HOURS + " VALUES ('" +
                date + "'," + hours + "," +
                "(SELECT " + UserDBHelper.COLUMN_NAME + " from " + UserDBHelper.TABLE_PERSON
                + " WHERE " + UserDBHelper.COLUMN_NAME + " = '" + name + "'));";

        db.execSQL(statement);


    }

    public void removeHour(String name, String date){
        SQLiteDatabase db = getWritableDatabase();
        String statement = "DELETE FROM " + UserDBHelper.TABLE_HOURS + " WHERE "
                + UserDBHelper.COLUMN_WORKER + "='" + name + "' AND " +
                UserDBHelper.COLUMN_DATE + "='" + date + "';";

        db.execSQL(statement);


    }

    public void editHour(String name, String date, int hour){
        SQLiteDatabase db = getWritableDatabase();

        if(date != null) {
            String statement = "UPDATE " + UserDBHelper.TABLE_HOURS + " SET " +
                    UserDBHelper.COLUMN_DATE + "='" + date + "' WHERE " +
                    UserDBHelper.COLUMN_WORKER + "=' " + name + "';";
            db.execSQL(statement);
        }
        if(hour != -1){
            String statement = "UPDATE " + UserDBHelper.TABLE_HOURS + " SET " +
                    UserDBHelper.COLUMN_WORK + " ='" + hour + "' WHERE " +
                    UserDBHelper.COLUMN_WORKER + "=' " + name + "';";
            db.execSQL(statement);
        }

    }




    public LinkedList<Person> getUsers(){
        int wage,name,calcmonth;

        inputs = new LinkedList<Person>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserDBHelper.TABLE_PERSON , null);

        wage = cursor.getColumnIndex(UserDBHelper.COLUMN_WAGE);
        name = cursor.getColumnIndex(UserDBHelper.COLUMN_NAME);
        calcmonth = cursor.getColumnIndex(UserDBHelper.COLUMN_CALCMONTH);

        Log.d("Rows", cursor.getCount() + "");

        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Person current = new Person(cursor.getString(name),cursor.getDouble(wage),cursor.getInt(calcmonth));
            inputs.add(getWorkersHours(current));
    }




        Log.d("Returning Users", inputs.size() + "");
        return inputs;

    }

    public Person getWorkersHours(Person person){
        int work,date;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserDBHelper.TABLE_HOURS +
                                        " WHERE " + UserDBHelper.COLUMN_WORKER + "= ?",new String[]{person.getName()});


        work = cursor.getColumnIndex(UserDBHelper.COLUMN_WORK);
        date = cursor.getColumnIndex(UserDBHelper.COLUMN_DATE);


        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Log.d("HOURSDATE", cursor.getString(date) + " is " + person.getName() + " minutes " + cursor.getInt(work));
            person.addHours(new Hours(cursor.getInt(work),cursor.getString(date)));
        }

        return person;
    }






}
