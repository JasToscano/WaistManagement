package com.toscano.waistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class WeightEnter extends AppCompatActivity {

    protected EditText dateEntry;
    protected EditText weightEntry;
    protected String isoDate;
    UserModel _user;
    WeightData _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_enter);

        dateEntry = findViewById(R.id.editWeightDate);
        weightEntry = findViewById(R.id.editWeightWeight);

        _user = UserModel.getUserInstance();
        _db = WeightData.getInstance(this);

        dateEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // passing context.
                        WeightEnter.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set date to our edit text.
                                dateEntry.setText((monthOfYear + 1) + "-" + dayOfMonth  + "-" + year);
                                isoDate = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" +
                                        String.valueOf(dayOfMonth);
                            }
                        },

                        year, month, day);

                datePickerDialog.show();
            }

        });
    }

    public void openMainForm(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
    public void onSaveClick(View view){
        String date = dateEntry.getText().toString();
        String sWeight = weightEntry.getText().toString();
        float weight = 0;

        //protect against an empty form
        if (!date.equals("") && !sWeight.equals("")){
            weight = Float.valueOf(sWeight);
            WeightClass entry = new WeightClass(isoDate, weight);
            Boolean weightAdd = _db.addEntry(entry, _user);

            //If goal reached and SMS on then send the message
            if(weight <= _user.getGoal()){
                if(_user.isTextPermission()){
                    SMSMessage.sendLongSMS(_user.getSMSText());
                }
            }
        }

        //back to main
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);

    }
}