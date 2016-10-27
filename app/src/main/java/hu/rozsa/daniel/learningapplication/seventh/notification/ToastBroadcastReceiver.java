package hu.rozsa.daniel.learningapplication.seventh.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ToastBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("my.package.TOAST")) {
            Toast.makeText(context, "Action clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
