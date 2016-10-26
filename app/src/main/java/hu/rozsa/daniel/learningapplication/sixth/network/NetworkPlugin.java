package hu.rozsa.daniel.learningapplication.sixth.network;

public interface NetworkPlugin {

    JsonSampleEntity downloadDataSync(String url, String id);

    void downloadDataAsync(String url, String id, OnComplete callback);


    interface OnComplete {

        void onSuccess(JsonSampleEntity result);

        void onError(String errorMsg);

    }

}
