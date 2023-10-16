package com.toscano.waistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginBTN;
    TextView passwordMSG;
    UserData _db;
    UserModel validUser;
    WeightData _weights;
    private DialogInterface.OnClickListener dialogClickListener;

    private void loginSuccess(View view, String _user){

        //start the weight Database
        _weights = WeightData.getInstance(this);

        //instantiate UserModel
        validUser = UserModel.getUserInstance();
        validUser.setUserName(_user);
        validUser.setGoal(_weights.getGoal(validUser));

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.textPassword);
        loginBTN = findViewById(R.id.buttonLogin);
        passwordMSG = findViewById(R.id.passwordMessage);

        //call singleton
        _db = UserData.getInstance(this);

        //admin and admin
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _username = username.getText().toString();
                String _password = password.getText().toString();

                boolean validUser = _db.checkUserName(_username);
                boolean validPass = _db.checkUserPassword(_username, _password);

                //first test if a registered user
                if(validUser){
                    if(validPass){
                        Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        loginSuccess(v, _username);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Incorrect Password!!!",Toast.LENGTH_SHORT).show();
                    }
                }
                //testing
                else if(_username.equals("admin") && _password.equals("admin")){

                    loginSuccess(v, "admin");
                }
                //to do: auto register a user if the username isn't valid
                //From http://www.wolinlabs.com/blog/yes.no.message.box.html
                else if(!validUser) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder
                            .setTitle("Account Not Recognized")
                            .setMessage("Would you like to register?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    //call insertUser
                                    Boolean userAdded = _db.insertUser(_username, _password);

                                    if(userAdded){
                                        Toast.makeText(MainActivity.this,"Account added",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Account failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("No", null) //Do nothing on no
                            .show();
                }
                else {
                    //incorrect
                    Toast.makeText(MainActivity.this, "LOGIN FAILED!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            //if the passwords meets the minimum
            //enable the button
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 5) {
                    passwordMSG.setVisibility(View.VISIBLE);
                } else {
                    passwordMSG.setVisibility(View.INVISIBLE);
                    loginBTN.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}