package com.example.girondins.workit;


import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.girondins.workit.Person;
import com.example.girondins.workit.PersonAdapter;
import com.example.girondins.workit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Start extends Fragment {
    private ListView personView;
    private Button addBtn ;
    private Person[] persons;
    private Controller cont;
    private Activity active;
    private TextView tv;
    private int cMonth;


    public Start() {
        // Required empty public constructor
    }

    public void setCont(Controller cont,Activity active, Person[] persons){
        this.cont = cont;
        this.active = active;
        this.persons = persons;
      //  persons[0] = new Person("Johan");
      //  persons[1] = new Person("Fred");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        addBtn = (Button) view.findViewById(R.id.addPersonBtn);
        tv = (TextView) view.findViewById(R.id.monthOver);
        personView = (ListView) view.findViewById(R.id.personViewID);
        addBtn.setOnClickListener(new AddPerson());
        Typeface customFont = Typeface.createFromAsset(active.getAssets(), "fonts/avocado.ttf");
        addBtn.setText(active.getString(R.string.addPerson));
        cMonth = cont.getMonth();

        addBtn.setTypeface(customFont);
        if (cMonth == 0) {
            tv.setText(this.getString(R.string.january));
            tv.setTypeface(customFont);

        }
        if (cMonth == 1) {
            tv.setText(this.getString(R.string.february));
            tv.setTypeface(customFont);

        }
        if (cMonth == 2) {
            tv.setText(this.getString(R.string.march));
            tv.setTypeface(customFont);
        }
        if (cMonth == 3) {
            tv.setText(this.getString(R.string.april));
            tv.setTypeface(customFont);
        }
        if (cMonth == 4) {
            tv.setText(this.getString(R.string.may));
            tv.setTypeface(customFont);
        }
        if (cMonth == 5) {
            tv.setTypeface(customFont);
        }
        if (cMonth == 6) {
            tv.setText(this.getString(R.string.july));
            tv.setTypeface(customFont);
        }
        if (cMonth == 7) {
            tv.setText(this.getString(R.string.august));
            tv.setTypeface(customFont);
        }
        if (cMonth == 8) {
            tv.setText(this.getString(R.string.september));
            tv.setTypeface(customFont);
        }
        if (cMonth == 9) {
            tv.setText(this.getString(R.string.october));
            tv.setTypeface(customFont);
        }
        if (cMonth == 10) {
            tv.setText(this.getString(R.string.november));
            tv.setTypeface(customFont);
        }
        if (cMonth == 11) {
            tv.setText(this.getString(R.string.december));
            tv.setTypeface(customFont);

        }


        if(persons != null) {
            personView.setAdapter(new PersonAdapter(active, persons, cont));
            personView.setOnItemClickListener(new ViewPerson());
        }


        return view;
    }

    private class AddPerson implements View.OnClickListener{

        @Override
        public void onClick(View v) {
         cont.initAddDialog();
        }
    }

    public void updateAdapter(Person[] updatePersons){
        personView.setAdapter(new PersonAdapter(active, updatePersons, cont));
        personView.setOnItemClickListener(new ViewPerson());
        persons = updatePersons;
        Log.d("Adapter Updated", updatePersons.length + "");
    }

    private class ViewPerson implements android.widget.AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("Clicked on", position + " and " + persons.length);
            cont.viewWorker(persons[position]);
        }
    }
}
