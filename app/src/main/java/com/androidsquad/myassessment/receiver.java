package com.androidsquad.myassessment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

public class receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Notification Dialog Closed",
                    Toast.LENGTH_LONG).show();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(001);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(context,  0, new Intent(), 0);
            NotificationCompat.Builder mb = new NotificationCompat.Builder(context,"Notification");
            mb.setContentIntent(resultPendingIntent);
        }
    }

