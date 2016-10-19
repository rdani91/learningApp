package hu.rozsa.daniel.learningapplication.fifth;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class ExampleFragment5th extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        BroadcastReceiverExample broadcastReceiverExample = new BroadcastReceiverExample();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        getContext().registerReceiver(broadcastReceiverExample, filter);
        getContext().unregisterReceiver(broadcastReceiverExample);


        //Open custom sharedPreff
        SharedPreferences settings = getActivity().getSharedPreferences("MY_PREFFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();

        //put value
        edit.putString("key", "value");
        edit.apply();


        //retrive value
        String string = settings.getString("key", "defaultValue");

        //clear sharedPreff
        edit.clear();
        edit.apply();


    }
}
