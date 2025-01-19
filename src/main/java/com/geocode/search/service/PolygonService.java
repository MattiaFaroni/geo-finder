package com.geocode.search.service;

import com.geocode.search.entity.Municipality;
import com.geocode.search.entity.Province;
import com.geocode.search.entity.Region;
import com.geocode.search.entity.Settlement;
import com.geocode.search.model.levels.PolygonLevel;
import com.geocode.search.model.response.Definition;
import com.geocode.search.model.response.Status;
import com.geocode.search.model.response.polygon.PolygonResult;
import com.geocode.search.repository.MunicipalityRepository;
import com.geocode.search.repository.ProvinceRepository;
import com.geocode.search.repository.RegionRepository;
import com.geocode.search.repository.SettlementRepository;
import io.sentry.Sentry;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolygonService {

    private final SettlementRepository settlementRepository;
    private final RegionRepository regionRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;

    /**
     * Method used to extract a polygon from the database
     * @param level database table
     * @param polygonName name of the polygon
     * @param iso3 ISO3 code of the country
     * @return polygon extracted from the database
     */
    public PolygonResult findGeoJson(PolygonLevel level, String polygonName, String iso3) {
        List<String> geoJson =
                switch (level) {
                    case region -> regionRepository.findGeoJson(polygonName, iso3);
                    case province -> provinceRepository.findGeoJson(polygonName, iso3);
                    case municipality -> municipalityRepository.findGeoJson(polygonName, iso3);
                    case settlement -> settlementRepository.findGeoJson(polygonName, iso3);
                };
        return generatePolygon(geoJson);
    }

    /**
     * Method used to extract a polygon from the database
     * @param level database table
     * @param longitude longitude
     * @param latitude latitude
     * @return polygon extracted from the database
     */
    public PolygonResult findGeoJson(PolygonLevel level, double longitude, double latitude) {
        List<String> geoJson = new ArrayList<>();
        switch (level) {
            case region:
                Region region = regionRepository.intersect(longitude, latitude);
                if (region != null && region.getArea_id() != null) {
                    geoJson = regionRepository.findGeoJson(region.getArea_id());
                }
                break;
            case province:
                Province province = provinceRepository.intersect(longitude, latitude);
                if (province != null && province.getArea_id() != null) {
                    geoJson = provinceRepository.findGeoJson(province.getArea_id());
                }
                break;
            case municipality:
                Municipality municipality = municipalityRepository.intersect(longitude, latitude);
                if (municipality != null && municipality.getArea_id() != null) {
                    geoJson = municipalityRepository.findGeoJson(municipality.getArea_id());
                }
                break;
            case settlement:
                Settlement settlement = settlementRepository.intersect(longitude, latitude);
                if (settlement != null && settlement.getArea_id() != null) {
                    geoJson = settlementRepository.findGeoJson(settlement.getArea_id());
                }
                break;
        }
        return generatePolygon(geoJson);
    }

    /**
     * Method used to process polygon extracted from the database
     * @param geoJson list of extracted polygons
     * @return polygon extracted from the database
     */
    // spotless:off
	private PolygonResult generatePolygon(List<String> geoJson) {
		Status status = null;
		List<List<List<List<BigDecimal>>>> coordinates = new ArrayList<>();
		List<List<List<BigDecimal>>> multiPolygon;
		List<List<BigDecimal>> polygon;
		List<BigDecimal> point;

		if (geoJson != null && !geoJson.isEmpty()) {
			for (String geoJsonItem : geoJson) {

				try {
					GeometryJSON geometryJSON = new GeometryJSON();
					Geometry geometry = geometryJSON.read(geoJsonItem);

					multiPolygon = new ArrayList<>();
					for (int index = 0; index < geometry.getBoundary().getNumGeometries(); index++) {
						polygon = new ArrayList<>();

						LinearRing linearRing = (LinearRing) geometry.getBoundary().getGeometryN(index);
						Coordinate[] polygonCoordinates = linearRing.getCoordinates();

						for (Coordinate pointOfGeometry : polygonCoordinates) {

							point = new ArrayList<>();
							point.add(BigDecimal.valueOf(pointOfGeometry.getY()));
							point.add(BigDecimal.valueOf(pointOfGeometry.getX()));

							polygon.add(point);
						}
						multiPolygon.add(polygon);
					}
					coordinates.add(multiPolygon);

					status = new Status(0, Definition.POLYGON_FOUND);

				} catch (Exception e) {
					Sentry.captureException(e);
					coordinates = new ArrayList<>();
					status = new Status(1, Definition.POLYGON_PROCESSING_FAILED);
				}
			}
		} else {
			status = new Status(1, Definition.POLYGON_NOT_FOUND);
		}
		return new PolygonResult(coordinates, status);
	}
	// spotless:on
}
