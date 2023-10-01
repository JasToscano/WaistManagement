package com.toscano.waistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button editBtn = findViewById(R.id.buttonEditWeight);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        openSettings();
        return false;
    }

    public void openSettings(){
        Intent intent = new Intent(this, UserSettings.class);
        startActivity(intent);
    }

    public void openEdit(View view){
        Intent intent = new Intent(this, EditView.class);
        startActivity(intent);
    }

    public void openWeightForm(View view){
        Intent intent = new Intent(this, WeightEnter.class);
        startActivity(intent);
    }
}
