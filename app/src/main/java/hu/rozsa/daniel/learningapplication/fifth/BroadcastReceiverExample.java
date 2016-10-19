package hu.rozsa.daniel.learningapplication.fifth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class BroadcastReceiverExample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_SEND)){
            Uri data = intent.getData();
            if(data.equals(Uri.parse("email"))){
                //send email
            }
        }
        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
            //bekapcsolt a telefon.
        }
    }
}
