package hu.rozsa.daniel.learningapplication.eighth;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import hu.rozsa.daniel.learningapplication.R;

public class SensorFragment extends Fragment implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor magneticSensor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticSensor != null) {


        } else {
            Toast.makeText(getContext(), "No Magnetic field sensor :(", Toast.LENGTH_SHORT)
                 .show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        System.out.println("values are : " + x + "  " + y + "   " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
