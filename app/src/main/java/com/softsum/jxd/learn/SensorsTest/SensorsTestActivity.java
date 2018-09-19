package com.softsum.jxd.learn.SensorsTest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.softsum.jxd.learn.R;

import java.util.List;

public class SensorsTestActivity extends AppCompatActivity {

    protected SensorManager sensorManager;
    protected List<Sensor> sensors;
    protected TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_test);
        sensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        textView = findViewById(R.id.sensors_view);
    }

    private SensorEventListener accListener = new SensorEventListener(){
        public static final String TAG = "Sensors Acc";

        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            float values[] = event.values;
            Log.d(TAG, "onSensorChanged: " + "X: " + String.valueOf(values[0])
                            + "Y: " + String.valueOf(values[1])
                            + "Z: " + String.valueOf(values[2]));
            textView.setText("加速度传感器：\n" + "X: " + String.valueOf(values[0])
                    + "\nY: " + String.valueOf(values[1])
                    + "\nZ: " + String.valueOf(values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(accListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_UI);

    }
}
