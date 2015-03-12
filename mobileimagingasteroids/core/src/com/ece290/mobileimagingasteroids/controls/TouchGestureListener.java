package com.ece290.mobileimagingasteroids.controls;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.ece290.mobileimagingasteroids.ControlsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hallvard on 11.03.2015.
 */
public class TouchGestureListener implements GestureDetector.GestureListener {

    public TouchGestureListener(){}

    private static List<ControlsListener> listeners = new ArrayList<ControlsListener>();

    public static void addListenser(ControlsListener listener) {
        listeners.add(listener);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        for(ControlsListener l : listeners)
            l.onShoot();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

        float x1n = initialPointer1.x;
        float y1n = initialPointer1.y;

        float x2n = initialPointer2.x;
        float y2n = initialPointer2.y;

        float x1p = pointer1.x;
        float y1p = pointer1.y;

        float x2p = pointer2.x;
        float y2p = pointer2.y;

        float dx1 = x1n - x2n;
        float dy1 = y1n - y2n;
        float initialDistance = (float) Math.sqrt(dx1*dx1+dy1*dy1);
        float dx2 = x1p - x2p;
        float dy2 = y1p - y2p;
        float distance = (float) Math.sqrt(dx2*dx2+dy2*dy2);

        int degrees = distance > initialDistance ? (int)distance : (int)-distance;

        for(ControlsListener cl : listeners) {
            cl.onRotationUpdate(degrees/2);
        }

        return true;
    }

    //----- Not used-----//


    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
/*
        for(ControlsListener cl : listeners) {
            cl.onVelocityUpdate(velocityX/10, velocityY/10);
        }
        */
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

}
