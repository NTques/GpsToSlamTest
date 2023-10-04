package points;

public class GTSPoint { // points.GeoPoint to points.SlamPoint
    private KtmPoint ktmPoint1;
    private SlamPoint slamPoint1;

    private KtmPoint ktmPoint2;
    private SlamPoint slamPoint2;

    public GTSPoint(KtmPoint ktmPoint1, SlamPoint slamPoint1, KtmPoint ktmPoint2, SlamPoint slamPoint2) {
        this.ktmPoint1 = ktmPoint1;
        this.slamPoint1 = slamPoint1;
        this.ktmPoint2 = ktmPoint2;
        this.slamPoint2 = slamPoint2;
    }

    public KtmPoint getKtmPoint1() {
        return ktmPoint1;
    }

    public SlamPoint getSlamPoint1() {
        return slamPoint1;
    }

    public KtmPoint getKtmPoint2() {
        return ktmPoint2;
    }

    public SlamPoint getSlamPoint2() {
        return slamPoint2;
    }

    @Override
    public String toString() {
        return "GTSPoint{" +
                "ktmPoint1=" + ktmPoint1 +
                ", slamPoint1=" + slamPoint1 +
                ", ktmPoint2=" + ktmPoint2 +
                ", slamPoint2=" + slamPoint2 +
                '}';
    }
}
