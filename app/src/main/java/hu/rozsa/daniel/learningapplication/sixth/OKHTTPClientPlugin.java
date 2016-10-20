package hu.rozsa.daniel.learningapplication.sixth;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHTTPClientPlugin {


    OkHttpClient client = new OkHttpClient();


    public void downloadData(String targetUrl){
        Request request = new Request.Builder()
                .get()
                .url(targetUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

}
