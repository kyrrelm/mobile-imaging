package com.ece290.mobileimagingasteroids.android.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ece290.mobileimagingasteroids.android.R;

/**
 * Created by ethan_000 on 2/14/2015.
 */
public class ImagingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.imaging_fragment_layout, container, false);
    }
}