package hu.rozsa.daniel.learningapplication.fifth;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class HandlerExample {



    public void threadExample(){
//        String response = NetworkHandler.getInstance()     Ez így blokkolná a UI szálat
//                                 .longRunningNetworkTask();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetworkHandler.getInstance()
                                                      .longRunningNetworkTask();

                Handler h = new Handler(Looper.getMainLooper());
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        processResult(response);
                    }
                });

                //másik mód lehetne, ha lenne activity-nk, és arra postolnánk(activity.runOnUiThread(Runnable r))

            }
        }).start();


    }

    private void processResult(String response) {
        //Csinálunk valamit a UI szálon, pl: Toastot hozunk létre
    }
}
