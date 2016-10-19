package hu.rozsa.daniel.learningapplication.first;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import hu.rozsa.daniel.learningapplication.MainActivity;
import hu.rozsa.daniel.learningapplication.R;

public class DummyActivity extends AppCompatActivity {


    //this is the life cycle in the following order
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_first_dummy);

        startActivity(new Intent(this, MainActivity.class));
        startService(new Intent(this, DummyService.class));
        String settings = getString(R.string.action_settings);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
