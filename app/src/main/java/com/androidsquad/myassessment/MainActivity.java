package com.androidsquad.myassessment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int NOTIFICATION_ID=001;
    String CHANNEL_ID="Notification";
    Button gen_toast,gen_notification,gen_dialog,change_txt;
    EditText text;
    Dialog dialog;
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text= findViewById(R.id.UserInput);
        gen_toast=findViewById(R.id.toast);
        gen_notification=findViewById(R.id.notify);
        gen_dialog=findViewById(R.id.dial);
        change_txt=findViewById(R.id.change_text);
        String txt= text.getText().toString();
            gen_toast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, text.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            gen_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_LONG).show();
                        return;
                    }
                    generateNofication(text.getText().toString());
                }
            });
            gen_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_LONG).show();
                    } else {
                        generateDialog(txt);
                    }
                }
            });
            change_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_LONG).show();
                    } else {
                        text.setText("Completed");
                    }
                }
            });
    }
    public void generateNofication(String s)
    {
        NotificationChannel(s);
        Intent intent= new Intent(this,LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Intent dismissIntent= new Intent(this,receiver.class);
        dismissIntent.putExtra("ID",NOTIFICATION_ID);
        PendingIntent pendingdismissIntent= PendingIntent.getBroadcast(this,0,dismissIntent,0);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, CHANNEL_ID);
        CharSequence msg= text.getText();
        builder.setSmallIcon(R.drawable.notification_icon);
        builder.setContentTitle("Text Generated");
        builder.setContentText(msg);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.notification,"DISMISS",pendingdismissIntent);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }
    public void generateDialog(String s)
    {
        dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        TextView txt= dialog.findViewById(R.id.msg);
        txt.setText(text.getText().toString());
        ImageView btn= dialog.findViewById(R.id.dismiss);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void NotificationChannel(String s)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="Notification";
            String description=s;
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel= new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}