import Log.Log;
import math.*;
import points.GTSPoint;
import points.GeoPoint;
import points.KtmPoint;
import points.SlamPoint;

public class SlamPointGenerator {
    public static GeoPoint locationFromLocation(GeoPoint fromGeopoint, double distance, double bearingDegrees) {
        double distanceKm = distance / 1000.0;
        double distanceRadians = distanceKm / 6371.0;
        //6,371 = Earth's radius in km
        double bearingRadians = CommonMath.radiansFromDegrees(bearingDegrees);
        double fromLatRadians = CommonMath.radiansFromDegrees(fromGeopoint.getLatitude());
        double fromLonRadians = CommonMath.radiansFromDegrees(fromGeopoint.getLongitude());

        double toLatRadians = Math.asin(Math.sin(fromLatRadians) * Math.cos(distanceRadians)
                + Math.cos(fromLatRadians) * Math.sin(distanceRadians) * Math.cos(bearingRadians));

        double toLonRadians = fromLonRadians + Math.atan2(Math.sin(bearingRadians)
                * Math.sin(distanceRadians) * Math.cos(fromLatRadians), Math.cos(distanceRadians)
                - Math.sin(fromLatRadians) * Math.sin(toLatRadians));

        // adjust toLonRadians to be in the range -180 to +180...
        toLonRadians = ((toLonRadians + 3 * Math.PI) % (2 * Math.PI)) - Math.PI;

        return new GeoPoint(
                CommonMath.degreesFromRadians(toLatRadians),
                CommonMath.degreesFromRadians(toLonRadians)
        );
    }

 /*   public static SlamPoint geoPointToSlamPoint(GTSPoint gts, GeoPoint des, double difDegree) {
        double distance = GpsMath.distanceFormulaMeters(gts.getGeoPoint1(), des);

        Log.d("distance", String.valueOf(distance));

        double rotateAngle = GTSMath.calculateRotation(gts);

        double radian = GpsMath.angleBetweenTwoPoints(gts.getGeoPoint1(), des) + rotateAngle + Math.PI
                + CommonMath.radiansFromDegrees(difDegree);

        double x = gts.getSlamPoint1().getX() + distance * Math.cos(radian);
        double y = gts.getSlamPoint1().getY() + distance * Math.sin(radian);


        Log.i("rotate angle", String.valueOf(CommonMath.degreesFromRadians(rotateAngle)));

        Log.i("degree between geo1 to des", String.valueOf(CommonMath.degreesFromRadians(
                GpsMath.angleBetweenTwoPoints(gts.getGeoPoint1(), des) + Math.PI))
        );

        Log.i("degree between slam1 to des", String.valueOf(
                Math.toDegrees(
                        SlamMath.angleBetweenTwoPoints(gts.getSlamPoint1(), new SlamPoint(x, y))
                )
        ));

        Log.i("gts geo degree", String.valueOf(
                Math.toDegrees(
                        GpsMath.angleBetweenTwoPoints(gts.getGeoPoint1(), gts.getGeoPoint2())
                )
        ));

        Log.i("gts slam degree", String.valueOf(
                CommonMath.degreesFromRadians(SlamMath.angleBetweenTwoPoints(gts.getSlamPoint1(), gts.getSlamPoint2()))
        ));

        Log.e("radian", String.valueOf(Math.toDegrees(radian)));

        return new SlamPoint(x, y);
    }*/

    public static SlamPoint geoPointToSlamPoint(GTSPoint gts, KtmPoint ktmDes, double difDegree) {
        KtmPoint ktmBcm = gts.getKtmPoint1();

        Log.e("gts slam distance", String.valueOf(
                SlamMath.distanceFormula(gts.getSlamPoint1(), gts.getSlamPoint2())
        ));
        Log.e("gts ktm distance", String.valueOf(
                CommonMath.distanceFormula(
                        gts.getKtmPoint1().getX(), gts.getKtmPoint1().getY(),
                        gts.getKtmPoint2().getX(), gts.getKtmPoint2().getY()
                )
        ));
        Log.i("ktm bench mark", ktmBcm.toString());
        Log.i("ktm destination", ktmDes.toString());

        double distance = CommonMath.distanceFormula(
                ktmBcm.getX(), ktmBcm.getY(),
                ktmDes.getX(), ktmDes.getY()
        );

        Log.d("distance", String.valueOf(distance));

        double rotateAngle = GTSMath.calculateRotation(gts);
        double startKtmToDesKtmRadian = CommonMath.calculateRadian(
                ktmBcm.getX(), ktmBcm.getY(),
                ktmDes.getX(), ktmDes.getY()
        );

        double radian = startKtmToDesKtmRadian + rotateAngle + Math.PI
                + CommonMath.radiansFromDegrees(difDegree);

        double x = gts.getSlamPoint1().getX() + distance * Math.cos(radian);
        double y = gts.getSlamPoint1().getY() + distance * Math.sin(radian);

        Log.i("rotate angle", String.valueOf(CommonMath.degreesFromRadians(rotateAngle)));

        return new SlamPoint(x, y);
    }
}
