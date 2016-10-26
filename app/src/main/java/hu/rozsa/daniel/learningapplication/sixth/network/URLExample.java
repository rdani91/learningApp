package hu.rozsa.daniel.learningapplication.sixth.network;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLExample implements NetworkPlugin {
    private Gson gson = new Gson();

    @Override
    public JsonSampleEntity downloadDataSync(String url, String id) {
        InputStream inputStream = null;
        BufferedReader br;
        String response = "";
        try {
            URL connection = new URL(url + "?id=" + id);
            inputStream = connection.openStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = br.readLine()) != null) {
                response += line;
            }
            JsonSampleEntity jsonSampleEntity = gson.fromJson(response, JsonSampleEntity.class);
            br.close();
            return jsonSampleEntity;
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void downloadDataAsync(final String url, final String id, final OnComplete callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JsonSampleEntity jsonSampleEntity = downloadDataSync(url, id);
                if (jsonSampleEntity != null) {
                    callback.onSuccess(jsonSampleEntity);
                } else {
                    callback.onError("error at downloading");
                }
            }
        });
    }

}
