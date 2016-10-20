package hu.rozsa.daniel.learningapplication.sixth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionExample {
    private Context context;
    private final String targetURL = "http://www.json-generator.com/api/json/get/cbeNvXZjaW?indent=2";


    public boolean checkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public void downloadViaURLConnection(){
        URLConnection urlConnection = new URLConnection();
        urlConnection.downloadData(targetURL);

    }


}
