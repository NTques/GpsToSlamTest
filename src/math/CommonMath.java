package math;

import points.GeoPoint;

public class CommonMath {
    public static double distanceFormula(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static double calculateRadian(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2 - y1, x2 - x1);
    }

    // degree to radian
    public static double radiansFromDegrees(double degrees) {
        return degrees * (Math.PI / 180.0);
    }

    // radian to degree
    public static double degreesFromRadians(double radians) {
        return radians * (180.0 / Math.PI);
    }

    public static double angleBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2 - y1, x2 - x1);
    }
}
