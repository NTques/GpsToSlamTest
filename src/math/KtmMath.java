package math;

import org.locationtech.proj4j.*;
import points.GeoPoint;
import points.KtmPoint;

public class KtmMath {
    static CRSFactory crsFactory = new CRSFactory();
    private static final CoordinateReferenceSystem wgs84 = crsFactory.createFromName("epsg:4326");
    private static final CoordinateReferenceSystem ktm = crsFactory.createFromName("epsg:5186");
    public static KtmPoint geoPointToKtmPoint(double latitude, double longitude) {
        // 경위도(WGS84) 좌표를 KTM 좌표로 변환
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform wgsToKtm = ctFactory.createTransform(wgs84, ktm);

        ProjCoordinate result = new ProjCoordinate();
        wgsToKtm.transform(new ProjCoordinate(longitude, latitude), result);

        double ktmX = result.x;
        double ktmY = result.y;

        return new KtmPoint(ktmX, ktmY);
    }

    public static GeoPoint ktmPointToGeoPoint(double x, double y) {
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform wgsToKtm = ctFactory.createTransform(ktm, wgs84);

        ProjCoordinate result = new ProjCoordinate();
        wgsToKtm.transform(new ProjCoordinate(0, 0), result);

        double lat = result.y;
        double lon = result.x;

        return new GeoPoint(lat, lon);
    }
}
