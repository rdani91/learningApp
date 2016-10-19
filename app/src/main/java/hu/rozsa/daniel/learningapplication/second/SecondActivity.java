package hu.rozsa.daniel.learningapplication.second;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import hu.rozsa.daniel.learningapplication.MainActivity;
import hu.rozsa.daniel.learningapplication.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_second);
    }

    private void intentTypes() {
        //explicitre már láttunk rengeteg példát
        Intent explicit = new Intent(this, MainActivity.class);


        //implicit, Action-t adunk meg
        Intent cameraImplicitIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //beépített Camera-t akarjuk használni

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); //csak email appok kezeljék
    }

    private void serviceTypes() {
        //servicek fajtái
        startService(new Intent(this, SecondService.class)); //ekkor az onStartCommand fog meghívódni, background mód
        bindService(new Intent(this, SecondBindService.class), new ServiceConnection() { //ezzel lehet bindelni a service-t, ekkor az onBind metódus fut le.
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, 0);

        new SecondService().startForeground(23, createFakeNotification()); //így tudunk foreground service-t indítani


        //intent service
        ResultReceiver myReceiver = new ResultReceiver(new Handler()){
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData); //ez így nagyon csúnya, külön osztály kellene, de itt kapjuk meg az intent eredményét
            }
        };
        Intent intentServiceIntent = new Intent(Intent.ACTION_SYNC, null, this, SecondIntentService.class);
        intentServiceIntent.putExtra("receiver", myReceiver);
        startService(intentServiceIntent);

    }



    private Notification createFakeNotification() {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 12, new Intent[]{i}, PendingIntent.FLAG_UPDATE_CURRENT);
        return new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_gallery)  // the status icon
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("title")  // the label of the entry
                .setContentText("text")  // the contents of the entry
                .setContentIntent(pendingIntent)  // The intent to send when the entry is clicked
                .build(); //ez el fog hasan
    }
}
