package com.geocode.search.service;

import com.geocode.search.entity.*;
import com.geocode.search.model.levels.FinderLevel;
import com.geocode.search.model.request.FinderData;
import com.geocode.search.model.response.*;
import com.geocode.search.model.response.finder.GeoElement;
import com.geocode.search.model.response.finder.GeoResults;
import com.geocode.search.model.response.finder.Hierarchy;
import com.geocode.search.model.response.finder.Other;
import com.geocode.search.repository.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinderService {

	private final HospitalRepository hospitalRepository;
	private final InstituteRepository instituteRepository;
	private final ParkingRepository parkingRepository;
	private final ShoppingRepository shoppingRepository;
	private final StreetRepository streetRepository;
	private final RegionRepository regionRepository;
	private final ProvinceRepository provinceRepository;
	private final MunicipalityRepository municipalityRepository;
	private final SettlementRepository settlementRepository;
	private final PointAddressRepository pointAddressRepository;

	/**
	 * Method used to find a list of geo elements
	 * @param level level you want to search for
	 * @param finderData parameters received in the body of the request
	 * @return geo elements found
	 */
	public GeoResults findGeoElements(FinderLevel level, FinderData finderData) {
		List<GeoElement> candidates = new ArrayList<>();
		List<List<String>> locations = findLocations(level, finderData);

		if (locations != null && !locations.isEmpty()) {
			candidates = createNewGeoElements(locations, level);
		}

		Status status;
		if (!candidates.isEmpty()) {
			status = new Status(0, Definition.GEO_ELEMENTS_FOUND);
		} else {
			status = new Status(1, Definition.GEO_ELEMENTS_NOT_FOUND);
		}

		return new GeoResults(candidates, status);
	}

	/**
	 * Method used to locate the closest locations based on input data
	 * @param level level you want to search for
	 * @param finderData input parameters
	 * @return list of locations extracted from the database
	 */
	private List<List<String>> findLocations(FinderLevel level, FinderData finderData) {
		double longitude = finderData.getLongitude();
		double latitude = finderData.getLatitude();
		int limit = finderData.getCandidates();

		return switch (level) {
			case pointAddress -> pointAddressRepository.searchNearbyPointAddress(longitude, latitude, limit);
			case hospital -> hospitalRepository.searchNearbyHospital(longitude, latitude, limit);
			case institute -> instituteRepository.searchNearbyInstitute(longitude, latitude, limit);
			case parking -> parkingRepository.searchNearbyParking(longitude, latitude, limit);
			case shopping -> shoppingRepository.searchNearbyShopping(longitude, latitude, limit);
		};
	}

	/**
	 * Method used to create new geo elements
	 * @param locations list of points on the map closest to where you are
	 * @param level level you want to search for
	 * @return list of geo elements
	 */
	// spotless:off
	private List<GeoElement> createNewGeoElements(List<List<String>> locations, FinderLevel level) {
		List<GeoElement> candidates = new ArrayList<>();

		for (List<String> location : locations) {

			GeoElement geoElement = findPlaceData(Long.valueOf(location.get(0)), level);
			geoElement.setLevel(String.valueOf(level));
			geoElement.setLongitude(Double.valueOf(location.get(1)));
			geoElement.setLatitude(Double.valueOf(location.get(2)));
			geoElement.setDistance(Double.valueOf(location.get(3)));

			findTerritoryHierarchy(Double.parseDouble(location.get(1)), Double.parseDouble(location.get(2)), geoElement);
			candidates.add(geoElement);
		}
		return candidates;
	}
	// spotless:on

	/**
	 * Method used to find information about where you are
	 * @param id id of the identified place
	 * @param level level you want to search for
	 * @return geo element where there is all the information about the place where
	 *         you are
	 */
	private GeoElement findPlaceData(Long id, FinderLevel level) {
		GeoElement geoElement = new GeoElement();
		Hierarchy hierarchy = new Hierarchy();

		switch (level) {
			case pointAddress:
				PointAddress pointAddress = pointAddressRepository.findPointAddressById(id);
				geoElement.setIso3(pointAddress.getIso3());
				findPointAddressHierarchy(pointAddress, hierarchy);
				break;
			case hospital:
				Hospital hospital = hospitalRepository.findHospitalById(id);
				if (hospital.getPoiName() != null && hospital.getPhoneNumber() != null) {
					geoElement.setOther(new Other(hospital.getPoiName(), hospital.getPhoneNumber()));
				}
				geoElement.setIso3(hospital.getIso3());
				findPlaceHierarchy(hospital, hierarchy);
				break;
			case institute:
				Institute institute = instituteRepository.findInstituteById(id);
				if (institute.getPoiName() != null && institute.getPhoneNumber() != null) {
					geoElement.setOther(new Other(institute.getPoiName(), institute.getPhoneNumber()));
				}
				geoElement.setIso3(institute.getIso3());
				findPlaceHierarchy(institute, hierarchy);
				break;
			case parking:
				Parking parking = parkingRepository.findParkingById(id);
				if (parking.getPoiName() != null && parking.getPhoneNumber() != null) {
					geoElement.setOther(new Other(parking.getPoiName(), parking.getPhoneNumber()));
				}
				geoElement.setIso3(parking.getIso3());
				findPlaceHierarchy(parking, hierarchy);
				break;
			case shopping:
				Shopping shopping = shoppingRepository.findShoppingById(id);
				if (shopping.getPoiName() != null && shopping.getPhoneNumber() != null) {
					geoElement.setOther(new Other(shopping.getPoiName(), shopping.getPhoneNumber()));
				}
				geoElement.setIso3(shopping.getIso3());
				findPlaceHierarchy(shopping, hierarchy);
				break;
		}
		geoElement.setHierarchy(hierarchy);
		return geoElement;
	}

	/**
	 * Method used to find the hierarchy of the place you are in
	 * @param place place on the map where you are
	 * @param hierarchy hierarchy area to be enhanced
	 */
	private void findPlaceHierarchy(Place place, Hierarchy hierarchy) {
		Street street = streetRepository.findStreetByLinkId(place.getLinkId());
		extractStreetData(street, hierarchy);
		hierarchy.setHouseNumber(place.getPoiStreetNumber());
	}

	/**
	 * Method used to find the hierarchy of the point address you are in
	 * @param pointAddress point address on the map where you are
	 * @param hierarchy hierarchy area to be enhanced
	 */
	private void findPointAddressHierarchy(PointAddress pointAddress, Hierarchy hierarchy) {
		Street street = streetRepository.findStreetByLinkId(pointAddress.getLinkId());
		extractStreetData(street, hierarchy);
		hierarchy.setHouseNumber(pointAddress.getAddress());
	}

	/**
	 * Method used to extract data from the street object
	 * @param street street information
	 * @param hierarchy hierarchy area to be enhanced
	 */
	private void extractStreetData(Street street, Hierarchy hierarchy) {
		if (street != null) {
			hierarchy.setStreetType(street.getType());
			hierarchy.setStreetName(street.getNameBase());
			hierarchy.setStreet(street.getName());

			if (street.getRightPostcode().equals(street.getLeftPostcode())) {
				hierarchy.setPostcode(street.getRightPostcode());
			} else {
				hierarchy.setPostcode(street.getRightPostcode() + ", " + street.getLeftPostcode());
			}
		}
	}

	/**
	 * Method used to find the hierarchy of the territory you are in
	 * @param longitude x coordinate
	 * @param latitude y coordinate
	 * @param geoElement geo element to create
	 */
	private void findTerritoryHierarchy(double longitude, double latitude, GeoElement geoElement) {
		Hierarchy hierarchy = geoElement.getHierarchy();
		hierarchy.setRegion(findRegionName(longitude, latitude));
		hierarchy.setProvince(findProvinceName(longitude, latitude));
		hierarchy.setMunicipality(findMunicipalityName(longitude, latitude));
		hierarchy.setSettlement(findSettlementName(longitude, latitude));
	}

	/**
	 * Method used to find the name of the region you are in
	 * @param longitude x coordinate
	 * @param latitude y coordinate
	 * @return name of the region you are in
	 */
	private String findRegionName(double longitude, double latitude) {
		Region region = regionRepository.intersect(longitude, latitude);
		return region != null ? region.getPolygonName() : null;
	}

	/**
	 * Method used to find the name of the province you are in
	 * @param longitude x coordinate
	 * @param latitude y coordinate
	 * @return name of the province you are in
	 */
	private String findProvinceName(double longitude, double latitude) {
		Province province = provinceRepository.intersect(longitude, latitude);
		return province != null ? province.getPolygonName() : null;
	}

	/**
	 * Method used to find the name of the municipality you are in
	 * @param longitude x coordinate
	 * @param latitude y coordinate
	 * @return name of the municipality you are in
	 */
	private String findMunicipalityName(double longitude, double latitude) {
		Municipality municipality = municipalityRepository.intersect(longitude, latitude);
		return municipality != null ? municipality.getPolygonName() : null;
	}

	/**
	 * Method used to find the name of the settlement you are in
	 * @param longitude x coordinate
	 * @param latitude y coordinate
	 * @return name of the settlement you are in
	 */
	private String findSettlementName(double longitude, double latitude) {
		Settlement settlement = settlementRepository.intersect(longitude, latitude);
		return settlement != null ? settlement.getPolygonName() : null;
	}
}
