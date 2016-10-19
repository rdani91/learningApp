package hu.rozsa.daniel.learningapplication.fifth;

import android.os.SystemClock;

public class NetworkHandler {
    private static NetworkHandler instance = new NetworkHandler();

    private NetworkHandler(){

    }

    public static NetworkHandler getInstance(){
        return instance;
    }

    public String longRunningNetworkTask(){

        SystemClock.sleep(3000);
        return "This is a test response";
    }


}
