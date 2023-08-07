package math;

import points.GeoPoint;

public class GpsMath {
    public static final double EARTH_RADIUS_KM = 6371.0; // 지구의 반지름 (Km 단위)
    public static final double EARTH_RADIUS_Meter = EARTH_RADIUS_KM * 1000;

    public static double distanceFormulaKilometers(double lat1, double lon1, double lat2, double lon2) {
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi / 2.0) * Math.sin(deltaPhi / 2.0)
                + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2.0) * Math.sin(deltaLambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public static double distanceFormulaKilometers(GeoPoint startPoint, GeoPoint endPoint) {
        return distanceFormulaKilometers(
                startPoint.getLatitude(),
                startPoint.getLongitude(),
                endPoint.getLatitude(),
                endPoint.getLongitude());
    }

    public static double distanceFormulaMeters(double lat1, double lon1, double lat2, double lon2) {
        return distanceFormulaKilometers(lat1, lon1, lat2, lon2) * 1000;
    }

    public static double distanceFormulaMeters(GeoPoint startPoint, GeoPoint endPoint) {
        return distanceFormulaMeters(
                startPoint.getLatitude(),
                startPoint.getLongitude(),
                endPoint.getLatitude(),
                endPoint.getLongitude());
    }

    public static double angleBetweenTwoPoints(double lat1, double lon1, double lat2, double lon2) {
        // 위도, 경도를 라디안 단위로 변환
        double phi1 = lat1 * Math.PI / 180;
        double phi2 = lat2 * Math.PI / 180;
        double lambda1 = lon1 * Math.PI / 180;
        double lambda2 = lon2 * Math.PI / 180;

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2) -
                Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);

        return Math.atan2(y, x);
    }

    public static double angleBetweenTwoPoints(GeoPoint startPoint, GeoPoint endPoint) {
        return angleBetweenTwoPoints(startPoint.getLatitude(), startPoint.getLongitude(),
                endPoint.getLatitude(), endPoint.getLongitude());
    }
}