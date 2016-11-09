package hu.rozsa.daniel.learningapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hu.rozsa.daniel.learningapplication.eighth.LocationExampleFragment;
import hu.rozsa.daniel.learningapplication.eighth.SensorFragment;
import hu.rozsa.daniel.learningapplication.eighth.TouchExampleFragment;
import hu.rozsa.daniel.learningapplication.fourth.ListViewFragment;
import hu.rozsa.daniel.learningapplication.fourth.RecycleViewFragment;
import hu.rozsa.daniel.learningapplication.ninth.ExampleNativeFragment;
import hu.rozsa.daniel.learningapplication.ninth.MyDrawFragment;
import hu.rozsa.daniel.learningapplication.seventh.customtag.CustomTagFragment;
import hu.rozsa.daniel.learningapplication.seventh.notification.NotificationExampleFragment;
import hu.rozsa.daniel.learningapplication.sixth.db_cp.ContentProviderFragment;
import hu.rozsa.daniel.learningapplication.third.EventHandling;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadFragment(Fragment targetFragment) {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.fragmentContainer, targetFragment)
                                   .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.third_events) {
            loadFragment(new EventHandling());

        } else if (id == R.id.fourth_listView) {
            loadFragment(new ListViewFragment());

        } else if (id == R.id.fourth_recycleView) {
            loadFragment(new RecycleViewFragment());
        } else if (id == R.id.sixth_content_provider) {
            loadFragment(new ContentProviderFragment());
        } else if (id == R.id.notification_example) {
            loadFragment(new NotificationExampleFragment());
        } else if (id == R.id.custom_tag) {
            loadFragment(new CustomTagFragment());
        } else if (id == R.id.touch) {
            loadFragment(new TouchExampleFragment());
        } else if (id == R.id.sensor) {
            loadFragment(new SensorFragment());
        } else if (id == R.id.location) {
            loadFragment(new LocationExampleFragment());
        } else if( id == R.id.my_draw){
            loadFragment(new MyDrawFragment());
        } else if( id == R.id.native_){
            loadFragment(new ExampleNativeFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onTvClick(View view) {
        Toast.makeText(this, "Dummy text has been clicked!", Toast.LENGTH_SHORT)
             .show();
    }
}
