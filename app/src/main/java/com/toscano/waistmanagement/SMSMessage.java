package com.toscano.waistmanagement;

import android.telephony.SmsManager;
import java.util.ArrayList;

public class SMSMessage {

    public static void sendLongSMS(String phoneNumber) {
        String message = "Goal Reached!";
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(message);
        smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
    }

}