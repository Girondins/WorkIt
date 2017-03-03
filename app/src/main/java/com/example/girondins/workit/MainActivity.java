package com.example.girondins.workit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {
    private Controller cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeBackground();
        cont = new Controller(this);
    }

    public void changeBackground(){
        LinearLayout relative = (LinearLayout) findViewById(R.id.mainLayout);
        Calendar c = Calendar.getInstance();
        int cMonth = c.get(Calendar.MONTH);
            if (cMonth == 0) {
                relative.setBackgroundResource(R.drawable.january);

            }
            if (cMonth == 1) {
                relative.setBackgroundResource(R.drawable.february);

            }
            if (cMonth == 2) {
                relative.setBackgroundResource(R.drawable.march);
            }
            if (cMonth == 3) {
                relative.setBackgroundResource(R.drawable.april);
            }
            if (cMonth == 4) {
                relative.setBackgroundResource(R.drawable.may);

            }
            if (cMonth == 5) {
                relative.setBackgroundResource(R.drawable.june);

            }
            if (cMonth == 6) {
                relative.setBackgroundResource(R.drawable.july);

            }
            if (cMonth == 7) {
                relative.setBackgroundResource(R.drawable.august);

            }
            if (cMonth == 8) {
                relative.setBackgroundResource(R.drawable.september);

            }
            if (cMonth == 9) {
                relative.setBackgroundResource(R.drawable.october);

            }
            if (cMonth == 10) {
                relative.setBackgroundResource(R.drawable.november);

            }
            if (cMonth == 11) {
                relative.setBackgroundResource(R.drawable.december);


        }


    }



}
