package com.toscano.waistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class WeightEnter extends AppCompatActivity {

    protected EditText dateEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_enter);

        dateEntry = findViewById(R.id.editWeightDate);

        dateEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //calendar instance.
                final Calendar c = Calendar.getInstance();

                //day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                //variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        //passing context.
                        WeightEnter.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //set date to our edit text.
                                dateEntry.setText((monthOfYear + 1) + "-" + dayOfMonth  + "-" + year);

                            }
                        },
                        //passing year, month and day for selected date.
                        year, month, day);
                //display date picker dialog.
                datePickerDialog.show();
            }

        });
    }

    public void openMainForm(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}
