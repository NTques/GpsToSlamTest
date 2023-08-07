package math;

import points.SlamPoint;

public class SlamMath {
    public static double distanceFormula(SlamPoint startPoint, SlamPoint endPoint) {
        return CommonMath.distanceFormula(
                startPoint.getX(), startPoint.getY(),
                endPoint.getX(), endPoint.getY()
        );
    }

    public static double angleBetweenTwoPoints(SlamPoint startPoint, SlamPoint endPoint) {
        return CommonMath.angleBetweenTwoPoints(
                startPoint.getX(), startPoint.getY(),
                endPoint.getX(), endPoint.getY()
        );
    }

    public static SlamPoint rotatePoint(double x, double y, double angle) {
        // 새로운 좌표 계산
        double xNew = x * Math.cos(angle) - y * Math.sin(angle);
        double yNew = x * Math.sin(angle) + y * Math.cos(angle);

        return new SlamPoint(xNew, yNew);
    }
}
