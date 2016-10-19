package hu.rozsa.daniel.learningapplication.second;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SecondService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //this runs on the UI-Thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Do the task here
            }
        }).start();

        return START_STICKY; //or START_NOT_STICKY, START_REDELIVER_INTENT
    }
}
