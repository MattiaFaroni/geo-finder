package com.geocode.search.service;

import com.geocode.search.model.request.DistanceData;
import com.geocode.search.model.response.distance.DistanceResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistanceService {

    /**
     * Method used to calculate the geographic distance between two points
     * @param distanceData area containing the input coordinates
     * @return geographic distance between two points
     */
    // spotless:off
    public DistanceResult calculateDistance(DistanceData distanceData) {

        double SEMI_MAJOR_AXIS_MT = 6378137;
        double SEMI_MINOR_AXIS_MT = 6356752.314245;
        double FLATTENING = 1 / 298.257223563;
        double ERROR_TOLERANCE = 1e-12;

        double U1 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(distanceData.getLatitude_1())));
        double U2 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(distanceData.getLatitude_2())));

        double sinU1 = Math.sin(U1);
        double cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2);
        double cosU2 = Math.cos(U2);

        double longitudeDifference = Math.toRadians(distanceData.getLongitude_2() - distanceData.getLongitude_1());
        double previousLongitudeDifference;

        double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;

        do {
            sinSigma = Math.sqrt(Math.pow(cosU2 * Math.sin(longitudeDifference), 2) + Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * Math.cos(longitudeDifference), 2));
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * Math.cos(longitudeDifference);
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * Math.sin(longitudeDifference) / sinSigma;
            cosSqAlpha = 1 - Math.pow(sinAlpha, 2);
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;

            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0;
            }

            previousLongitudeDifference = longitudeDifference;
            double C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 * cosSqAlpha));
            longitudeDifference = Math.toRadians(distanceData.getLongitude_2() - distanceData.getLongitude_1()) + (1 - C) * FLATTENING * sinAlpha * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))));

        } while (Math.abs(longitudeDifference - previousLongitudeDifference) > ERROR_TOLERANCE);

        double uSq = cosSqAlpha * (Math.pow(SEMI_MAJOR_AXIS_MT, 2) - Math.pow(SEMI_MINOR_AXIS_MT, 2)) / Math.pow(SEMI_MINOR_AXIS_MT, 2);

        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2)) - B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 * Math.pow(cos2SigmaM, 2))));
        double distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);

        return new DistanceResult(distanceMt/1000);
    }
    // spotless:on
}
