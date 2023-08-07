package math;

import points.GTSPoint;

public class GTSMath {
    public static double calculateRotation(GTSPoint gtsPoint) {
        double x1 = gtsPoint.getSlamPoint1().getX(), y1 = gtsPoint.getSlamPoint1().getY(); // 첫 번째 선분의 첫 좌표
        double x2 = gtsPoint.getSlamPoint2().getX(), y2 = gtsPoint.getSlamPoint2().getY(); // 첫 번째 선분의 두 번째 좌표
        double x3 = gtsPoint.getKtmPoint1().getX(), y3 = gtsPoint.getKtmPoint1().getY(); // 두 번째 선분의 첫 좌표
        double x4 = gtsPoint.getKtmPoint2().getX(), y4 = gtsPoint.getKtmPoint2().getY(); // 두 번째 선분의 두 번째 좌표

        // 두 선분의 방향 벡터를 계산합니다.
        double vABx = x2 - x1;
        double vABy = y2 - y1;
        double vCDx = x4 - x3;
        double vCDy = y4 - y3;

        // 두 벡터의 내적을 계산합니다.
        double dotProduct = vABx * vCDx + vABy * vCDy;

        // 각 벡터의 크기(magnitude)를 계산합니다.
        double magnitudeAB = Math.sqrt(vABx * vABx + vABy * vABy);
        double magnitudeCD = Math.sqrt(vCDx * vCDx + vCDy * vCDy);

        // 두 벡터 사이의 각도를 계산합니다. (라디안 단위)
        double angleRad = Math.acos(dotProduct / (magnitudeAB * magnitudeCD));

        return angleRad;
    }
}
