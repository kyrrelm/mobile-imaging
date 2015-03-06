package com.ece290.mobileimagingasteroids.android.Fragment;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ece290.mobileimagingasteroids.MobileImagingAsteroids;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by ethan_000 on 2/14/2015.
 */
public class GameFragment extends AndroidFragmentApplication
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
        config.useAccelerometer = false;
        config.useCompass = false;
        return initializeForView(new MobileImagingAsteroids(), config);
    }

    public void onRotationUpdate(int rotationUpdate)
    {
        Log.i("GameFragment", "rotationUpdate:"+rotationUpdate);
    }

}
