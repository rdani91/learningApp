package hu.rozsa.daniel.learningapplication.fifth;

import android.os.AsyncTask;
import android.os.SystemClock;

public class AsyncTaskExample extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        SystemClock.sleep(3000);
        return "This is a test response";    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //ez már UI szálon fut le, így csinálhatunk mondjuk egy Toastot az eredménnyel
    }


}
