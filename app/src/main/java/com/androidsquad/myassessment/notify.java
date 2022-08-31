package com.androidsquad.myassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class notify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        NotificationManager notificationmanager;
        notificationmanager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationmanager.cancel(1);
    }
}