package com.ece290.mobileimagingasteroids.android.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ece290.mobileimagingasteroids.android.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Created by ethan_000 on 2/14/2015.
 */
public class ImagingFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {

    private JavaCameraView mOpenCvCameraView;
    private Mat mRgba;


    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(getActivity().getApplicationContext()) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    mOpenCvCameraView.enableFpsMeter();
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test,container,false);
        mOpenCvCameraView = (JavaCameraView) view.findViewById(R.id.camera_view);
        mOpenCvCameraView.setCvCameraViewListener(this);

        //mOpenCvCameraView.enableView();
        //View v = getView();
        //mOpenCvCameraView = (JavaCameraView) v.findViewById(R.id.camera_view);
        //mOpenCvCameraView.enableView();

        //return inflater.inflate(R.layout.imaging_fragment_layout, container, false);
        //return inflater.inflate(R.layout.test, container, false);
        return view;

    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        return mRgba;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, getActivity().getApplicationContext(), mLoaderCallback);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }


}