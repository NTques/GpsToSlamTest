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

    public static SlamPoint geoPointToSlamPoint(GTSPoint gts, KtmPoint ktmDes) {
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

        Log.e("start ktm to des ktm radian", String.valueOf(startKtmToDesKtmRadian));

        double radian = startKtmToDesKtmRadian + rotateAngle + Math.PI;

        double x = gts.getSlamPoint1().getX() + distance * Math.cos(radian);
        double y = gts.getSlamPoint1().getY() + distance * Math.sin(radian);

        Log.i("rotate angle", String.valueOf(CommonMath.degreesFromRadians(rotateAngle)));

        return new SlamPoint(x, y);
    }

    public static GeoPoint slamPointToGeoPoint(GTSPoint gts, SlamPoint slamDes) {
        SlamPoint slamBcm = gts.getSlamPoint1();
        double distance = SlamMath.distanceFormula(slamBcm, slamDes);
        double rotateAngle = GTSMath.calculateRotation(gts);
        double startSlamToDesSlamRadian = CommonMath.calculateRadian(
                slamBcm.getX(), slamBcm.getY(),
                slamDes.getX(), slamDes.getY()
        );

        // 수정필요: 마이너스 플러스 조정 필요할 것으로 예상.
        double radian = startSlamToDesSlamRadian - rotateAngle - Math.PI;

        double x = gts.getKtmPoint1().getX() + distance * Math.cos(radian);
        double y = gts.getKtmPoint1().getY() + distance * Math.sin(radian);

        GeoPoint result = KtmMath.ktmPointToGeoPoint(x, y);

        return result;
    }
}
