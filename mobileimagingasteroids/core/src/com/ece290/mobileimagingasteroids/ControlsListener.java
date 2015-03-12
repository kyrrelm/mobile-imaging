package com.ece290.mobileimagingasteroids;

import java.util.Observable;

/**
 * Created by ethan_000 on 3/5/2015.
 */
public interface ControlsListener{


    public void onRotationUpdate(int rotationUpdate);

    public void onVelocityUpdate(float velX, float velY);

    public void onShoot();

}
