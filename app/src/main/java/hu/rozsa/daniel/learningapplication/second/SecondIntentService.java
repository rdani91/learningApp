package hu.rozsa.daniel.learningapplication.second;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class SecondIntentService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SecondIntentService(String name) {
        super(name);
    }

    public SecondIntentService() {
        super("example");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras(); //ebbe a Bundle-ben kapjuk meg az adatokat, amit esetleg küldtünk az intenten keresztül

        //pl:
        String exampleValue = extras.getString("KEY");

        ResultReceiver receiver = intent.getParcelableExtra("receiver"); //ezen keresztül tudunk visszaszólni a hívó félnek

        Bundle returnBundle = new Bundle();//ebbe pakoljuk a visszaküldeni kívánt adatokat
        receiver.send(STATUS_FINISHED, returnBundle); // itt visszküldök egy csomagot


        stopSelf(); //ezzel megölöm a service-t0
    }
}
