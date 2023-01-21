package com.example.olliesphoneshake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeHandler {
    public ShakeHandler(final SensorManager sensorManager, final TorchHandler torchHandler) {
        registerSensorEventHandler(sensorManager, torchHandler);
    }

    private void registerSensorEventHandler(final SensorManager sensorManager, final TorchHandler torchHandler) {
        sensorManager.registerListener(
                new SensorEventHandler(torchHandler),
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    static private class SensorEventHandler implements SensorEventListener {
        private final TorchHandler torchHandler;

        public SensorEventHandler(TorchHandler torchHandler) {
            this.torchHandler = torchHandler;
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            torchHandler.toggle();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
