package hu.rozsa.daniel.learningapplication.sixth.network;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpExample implements NetworkPlugin {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    @Override
    public JsonSampleEntity downloadDataSync(String url, String id) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response execute = client.newCall(request)
                                     .execute();
            String responseJsonString = execute.body()
                                               .string();
            return gson.fromJson(responseJsonString, JsonSampleEntity.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void downloadDataAsync(String url, String id, final OnComplete callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request)
              .enqueue(new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {
                      callback.onError(e.getMessage());
                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      callback.onSuccess(gson.fromJson(response.body()
                                                               .string(), JsonSampleEntity.class));
                  }
              });
    }
}
