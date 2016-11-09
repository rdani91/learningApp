package hu.rozsa.daniel.learningapplication.ninth;

public interface SomePlugin {


    void runOnUiThread(Runnable r);

    void getDataFromServer(String key, OnCompleteResult callback);


    interface OnCompleteResult {

        void onSuccess(String result);

        void onError(String errorMsg);
    }
}
