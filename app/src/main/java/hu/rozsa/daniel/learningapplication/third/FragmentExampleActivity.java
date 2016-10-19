package hu.rozsa.daniel.learningapplication.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hu.rozsa.daniel.learningapplication.R;

public class FragmentExampleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_example);

        loadFragment(ExampleFragment.newInstance());


    }

    private void loadFragment(Fragment targetFragmentToLoad) {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.fragmentContainer, targetFragmentToLoad)
                                   .commit();
    }


    private Fragment findCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    }

    public void sendMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
             .show();
    }
}
