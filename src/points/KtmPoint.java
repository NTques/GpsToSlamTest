package points;

public class KtmPoint {
    double x;
    double y;

    public KtmPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "KtmPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
