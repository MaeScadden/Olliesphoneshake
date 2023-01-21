package com.example.olliesphoneshake;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ShakeHandler(getSensorManger(), new TorchHandler(getCameraManager()));
        setContentView(R.layout.activity_main);
    }

    private CameraManager getCameraManager() {
        CameraManager manager = (CameraManager) getSystemService(CAMERA_SERVICE);
        if (manager == null) {
            throw new RuntimeException("Unable to get the camera service, we wont be able to use the flash light");
        }

        return manager;
    }

    private SensorManager getSensorManger() {
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (manager == null) {
            throw new RuntimeException("Unable to get the sensor manager, we wont be able to detect a shake");
        }

        return manager;
    }
}
