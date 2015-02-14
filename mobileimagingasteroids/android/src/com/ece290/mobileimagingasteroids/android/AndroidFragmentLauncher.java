package com.ece290.mobileimagingasteroids.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ece290.mobileimagingasteroids.MobileImagingAsteroids;
import android.support.v4.app.FragmentActivity;

public class AndroidFragmentLauncher extends FragmentActivity {//implements AndroidFragmentApplication.Callbacks {

  /*  @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        GameFragment fragment = new GameFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, fragment);
        trans.commit();
    }

    // 4. Create a Class that extends AndroidFragmentApplication which is the Fragment implementation for Libgdx.
    private class GameFragment extends AndroidFragmentApplication
    {
        // 5. Add the initializeForView() code in the Fragment's onCreateView method.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {  return initializeForView(new MyGdxGame());   }
    }


    @Override
    public void exit() {}*/


}
