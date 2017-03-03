package com.example.girondins.workit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Girondins on 05/10/16.
 */

public class AddPersonDialog extends DialogFragment {
    private LayoutInflater inflater;
    private View v;
    private Controller cont;
    private EditText name,wage,monthhour;
    private double wager;
    private int calcmonth;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.add_person, null);
        name = (EditText) v.findViewById(R.id.enterName);
        wage = (EditText) v.findViewById(R.id.enterWageID);
        monthhour = (EditText) v.findViewById(R.id.enterMonthHours);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle(R.string.add_person);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkFields();
                cont.addNewWorker(name.getText().toString(),wager,calcmonth);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.yellowshape);
        return builder.create();
    }

    public void setController(Controller cont){
        this.cont = cont;
    }

    public void checkFields(){
        if(wage.getText().toString().matches("")){
            wager = -1;
            Log.d("WAGE IS NOT SET", "LOL");
        }else{
            wager = Double.parseDouble(wage.getText().toString());
        }

        if(monthhour.getText().toString().matches("")){
            calcmonth = -1;
            Log.d("MONTHH IS NOT SET", "LOL");
        }else{
            calcmonth = Integer.parseInt(monthhour.getText().toString());
        }
    }

}
