package com.ece290.mobileimagingasteroids.android;

import org.opencv.core.Point;

import java.util.HashSet;

/**
 * Created by Kyrre on 06.03.2015.
 */
public class GestureDetector {

    public static void detect(HashSet<Point> fingerTips, Point centroid) {

        Point middleFinger = findMiddleFinger(fingerTips, centroid);

    }

    public static double distance(Point p0, Point p1){
        return Math.hypot((Math.abs(p0.x - p1.x)), (Math.abs(p0.y - p1.y)));
    }

    private static Point findMiddleFinger(HashSet<Point> fingerTips, Point centroid) {
        double length = 0;
        Point middleFinger = null;
        for (Point p: fingerTips){
            if (distance(p, centroid) > length){
                middleFinger = p;
                length = distance(p, centroid);
            }
        }
        return middleFinger;
    }
}
