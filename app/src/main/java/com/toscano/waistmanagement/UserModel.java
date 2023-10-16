package com.toscano.waistmanagement;

import android.content.Context;

//this class represents the valid user
public class UserModel {

    private String userName;
    private float goal = 0; //default to zero
    private boolean textPermission = false;
    private String SMSText = "0000000000";
    private static UserModel _loggedUser;

    //there can only be one valid user logged in at anytime
    public static UserModel getUserInstance(){
        if(_loggedUser == null){
            _loggedUser = new UserModel();
        }
        return _loggedUser;
    }

    //set the constructor to private
    private UserModel(){}

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public float getGoal() {
        return goal;
    }
    public void setGoal(float goal) {
        this.goal = goal;
    }
    public boolean isTextPermission() {return textPermission;}
    public void setTextPermission(boolean textPermission) {
        this.textPermission = textPermission;
    }

    public String getSMSText() {
        return SMSText;
    }

    public void setSMSText(String SMSText) {
        this.SMSText = SMSText;
    }
}

