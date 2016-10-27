package hu.rozsa.daniel.learningapplication.seventh.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import hu.rozsa.daniel.learningapplication.R;

public class ForegroundServiceExample extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_menu_gallery)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fake_ragnar))
                .setContentTitle("service running title")
                .setOngoing(true)
                .setContentIntent(createSecondActivityIntent())
                .setContentText("service running text");
        Notification notification = builder.build();
        startForeground(1245, notification);
        return super.onStartCommand(intent, flags, startId);

    }

    private PendingIntent createSecondActivityIntent() {
        Intent i = new Intent(getApplicationContext(), SampleNotiActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(getApplicationContext(), 111, i, 0);
    }

}
