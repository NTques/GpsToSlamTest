import Log.Log;
import math.CommonMath;
import math.GpsMath;
import math.KtmMath;
import math.SlamMath;
import points.GTSPoint;
import points.GeoPoint;
import points.KtmPoint;
import points.SlamPoint;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        KtmPoint startKtmPoint = KtmMath.geoPointToKtmPoint(37.465932, 127.124136);
        KtmPoint endKtmPoint = KtmMath.geoPointToKtmPoint(37.465798, 127.124107);

        Log.e("start", startKtmPoint.toString());
        Log.e("end", endKtmPoint.toString());

        SlamPoint startSlamPoint = new SlamPoint(2.724780, -2.330876);
        SlamPoint endSlamPoint = new SlamPoint(-12.386119, 0.497768);

        GTSPoint gtsPoint = new GTSPoint(startKtmPoint, startSlamPoint, endKtmPoint, endSlamPoint);

        KtmPoint des1 = KtmMath.geoPointToKtmPoint(37.4658517, 127.1239257);
        KtmPoint des2 = KtmMath.geoPointToKtmPoint(37.465798, 127.124107);

        KtmPoint currentKtmPoint = KtmMath.geoPointToKtmPoint(37.4658517, 127.1239257); // 현재 UGV 위치 - GPS
        SlamPoint result = SlamPointGenerator.geoPointToSlamPoint(gtsPoint, currentKtmPoint); // 현재 UGV 위치 - 계산된 SLAM 좌표

        // 계산된 SLAM 좌표 적용 - Initial Pose Publish
        initialPose(result);

        GeoPoint geoPoint = new GeoPoint(37.465932, 127.124136);
        GeoPoint geoPoint1 = new GeoPoint(37.465798, 127.124107);

        Log.d("StartPoint", "X: " + startKtmPoint.getX() + ", Y: " + startKtmPoint.getY());
        Log.d("EndPoint", "X: " + endKtmPoint.getX() + ", Y: " + endKtmPoint.getY());
        Log.d("차이", "X: " + (startKtmPoint.getX() - endKtmPoint.getX()) + ", Y: " + (startKtmPoint.getY() - endKtmPoint.getY()));
        Log.d("GPS Distance", String.valueOf(GpsMath.distanceFormulaMeters(geoPoint, geoPoint1)));
        Log.d("KTM Distance", String.valueOf(CommonMath.distanceFormula(startKtmPoint.getX(), startKtmPoint.getY(), endKtmPoint.getX(), endKtmPoint.getY())));
        Log.d("계산", String.valueOf(CommonMath.distanceFormula(0, 0, 2.5459084357135, 14.875566659029573)));

        double resolution = 0.05;
        KtmPoint maxKtmPoint = KtmMath.geoPointToKtmPoint(37.465932, 127.124136);
        KtmPoint minKtmPoint = KtmMath.geoPointToKtmPoint(37.465798, 127.124107);

        int width = (int) ((maxKtmPoint.getX() - minKtmPoint.getX()) / resolution);
        int height = (int) ((maxKtmPoint.getY() - minKtmPoint.getY()) / resolution);


    }

    public static void initialPose(SlamPoint slamPoint) {

    }
}