package math;

import org.locationtech.proj4j.*;
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
}
