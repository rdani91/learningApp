package hu.rozsa.daniel.learningapplication.ninth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.rozsa.daniel.learningapplication.R;

public class ExampleNativeFragment extends Fragment {


    static {
        System.loadLibrary("hello-android-jni");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_native, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvNativeText = (TextView) view.findViewById(R.id.tvNativeText);

        tvNativeText.setText(getMsgFromJni());
    }

    public native String getMsgFromJni();

    public native String getSecondNativeString();

}
