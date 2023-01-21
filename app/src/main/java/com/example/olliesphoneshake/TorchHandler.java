package com.example.olliesphoneshake;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

import java.util.Optional;

public class TorchHandler {
    private final CameraManager manager;
    private boolean turnedOn = false;

    public TorchHandler(CameraManager m) {
        manager = m;
    }

    private Optional<String> getTorchableCamera() {
        for (String id : getCameraIds()) {
            if (isTorchable(id)) {
                return Optional.of(id);
            }
        }

        return Optional.empty();
    }

    private boolean isTorchable(String id) {
        CameraCharacteristics c = getCameraCharacteristics(id);
        return c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
    }

    private CameraCharacteristics getCameraCharacteristics(String id) {
        try {
            return manager.getCameraCharacteristics(id);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getCameraIds() {
        try {
            return manager.getCameraIdList();
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void turnTorchOnCamera(String id) {
        try {
            manager.setTorchMode(id, turnedOn);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private void update() {
        getTorchableCamera().ifPresent(this::turnTorchOnCamera);
    }

    public void toggle() {
        turnedOn = !turnedOn;
        update();
    }
}
