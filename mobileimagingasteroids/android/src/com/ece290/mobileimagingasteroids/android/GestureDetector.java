package com.ece290.mobileimagingasteroids.android;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Kyrre on 06.03.2015.
 */
public class GestureDetector {
    static int count = 0;

    public static void detect(ArrayList<Point> fingerTips, Point centroid, Mat mRgba) {
        for (Point p: fingerTips){
            Core.circle(mRgba, p, 10, new Scalar(150, 50, 255));
            Core.line(mRgba, p, centroid, new Scalar(150, 50, 50),10);
        }
        //Log.d("Angle","count: "+count);
        Point middleFinger = findMiddleFinger(fingerTips,centroid);
        if (middleFinger != null){
            Core.circle(mRgba, findMiddleFinger(fingerTips, centroid), 20, new Scalar(200, 200, 255));
            double x = Math.abs(middleFinger.x - centroid.x);
            double y = Math.abs(middleFinger.y - centroid.y);
            double mag = Math.sqrt(x * x + y * y);
            x = x/mag;
            y = y/mag;
            Point p = new Point(y,x);
            Core.circle(mRgba, p, 15, new Scalar(150, 50, 255));
        }
        count++;
    }

    public static double distance(Point p0, Point p1){
        return Math.hypot((Math.abs(p0.x - p1.x)), (Math.abs(p0.y - p1.y)));
    }

    private static Point findMiddleFinger(ArrayList<Point> fingerTips, Point centroid) {
        if (fingerTips.size() != 5){
            Log.d("GestureDetector", "These are not the fingers your looking for");
            return null;
        }
        double length = 0;
        Point middleFinger = null;
        for (Point p: fingerTips){
            Log.d("Angle","----------------------------------");
            int sum = 0;
            for (Point pp: fingerTips){
                if (!p.equals(pp)){
                    if (angle(centroid, p, pp)>0){
                        sum++;
                    }else{
                        sum--;
                    }
                    Log.d("Angle","angle: "+angle(centroid, p, pp));
                }
            }
            if (sum == 0){
                return p;
            }
            Log.d("Angle","----------------------------------");
        }
        return null;
    }

    private static double angle(Point centroid, Point p0, Point p1){
        double x0 = p0.x - centroid.x;
        double y0 = p0.y - centroid.y;
        double x1 = p1.x - centroid.x;
        double y1 = p1.y - centroid.y;
        return Math.atan2(x0, y0)-Math.atan2(x1, y1);
    }
}
