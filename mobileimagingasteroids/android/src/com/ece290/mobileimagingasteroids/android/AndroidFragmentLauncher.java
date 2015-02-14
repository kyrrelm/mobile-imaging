package com.ece290.mobileimagingasteroids.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ece290.mobileimagingasteroids.MobileImagingAsteroids;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication.Callbacks;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AndroidFragmentLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        GameFragment fragment = new GameFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, fragment);
        trans.commit();
    }

    private class GameFragment extends AndroidFragmentApplication
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
    }


    @Override
    public void exit() {}


}
