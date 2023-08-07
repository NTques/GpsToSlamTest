import math.KtmMath;
import math.SlamMath;
import points.GTSPoint;
import points.KtmPoint;
import points.SlamPoint;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        KtmPoint startKtmPoint = KtmMath.geoPointToKtmPoint(37.465932, 127.124136);
        KtmPoint endKtmPoint = KtmMath.geoPointToKtmPoint(37.465798, 127.124107);
        SlamPoint startSlamPoint = new SlamPoint(2.724780, -2.330876);
        SlamPoint endSlamPoint = new SlamPoint(-12.386119, 0.497768);

        GTSPoint gtsPoint = new GTSPoint(startKtmPoint, startSlamPoint, endKtmPoint, endSlamPoint);

        KtmPoint des1 = KtmMath.geoPointToKtmPoint(37.4658517, 127.1239257);
        KtmPoint des2 = KtmMath.geoPointToKtmPoint(37.465798, 127.124107);

        SlamPoint targetResult = new SlamPoint(-5.59, 16.2956);
        SlamPoint targetResult2 = new SlamPoint(-12.386119, 0.497768);

        SlamPoint result = SlamPointGenerator.geoPointToSlamPoint(gtsPoint, des1, 0);
        System.out.println("target -> " + targetResult);
        System.out.println("result -> " + result);
        System.out.println("difference -> " + SlamMath.distanceFormula(targetResult, result));

        SlamPoint result2 = SlamPointGenerator.geoPointToSlamPoint(gtsPoint, des2, 0);
        System.out.println("target2 -> " + targetResult2);
        System.out.println("result2 ->" + result2);
        System.out.println("difference2 -> " + SlamMath.distanceFormula(targetResult2, result2));
/*
        GeoPoint des = new GeoPoint(37.4658517, 127.1239257);
        GeoPoint des2 = new GeoPoint(37.465798, 127.124107);

        SlamPoint targetResult = new SlamPoint(-5.59, 16.2956);
        SlamPoint targetResult2 = new SlamPoint(-12.386119, 0.497768);

        SlamPoint result = SlamPointGenerator.geoPointToSlamPoint(gtsPointTest, des, 321);
        System.out.println("target -> " + targetResult);
        System.out.println("result -> " + result);
        System.out.println("difference -> " + SlamMath.distanceFormula(targetResult, result));

        SlamPoint result2 = SlamPointGenerator.geoPointToSlamPoint(gtsPointTest, des2, 71);
        System.out.println("target2 -> " + targetResult2);
        System.out.println("result2 ->" + result2);
        System.out.println("difference2 -> " + SlamMath.distanceFormula(targetResult2, result2));*/
    }
}