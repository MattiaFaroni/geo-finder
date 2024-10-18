package com.geocode.search.finder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.geocode.search.entity.Hospital;
import com.geocode.search.model.levels.FinderLevel;
import com.geocode.search.model.request.FinderData;
import com.geocode.search.model.response.finder.GeoResults;
import com.geocode.search.repository.*;
import com.geocode.search.service.FinderService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FinderTest {

	@Autowired
	private FinderService finderService;

	@MockBean
	private HospitalRepository hospitalRepository;

	// spotless:off
    @Test
    public void testFinderService() {
        FinderData finderData = new FinderData(7.67420466959107, 45.04137661312246, 1);

        List<List<String>> nearbyHospital = List.of(Arrays.asList("10581", "7.6744", "45.04191", "0.0005680277544163"));
        when(hospitalRepository.searchNearbyHospital(finderData.getLongitude(), finderData.getLatitude(), 1)).thenReturn(nearbyHospital);

        Hospital hospital = new Hospital();
        hospital.setLinkId(592384213L);
        hospital.setPoiId(50933210L);
        hospital.setPoiName("MOLINETTE");
        hospital.setPoiStreetNumber("88");
        hospital.setIso3("ITA");
        hospital.setPhoneNumber("0116331633");
        when(hospitalRepository.findHospitalById(Long.valueOf(nearbyHospital.get(0).get(0)))).thenReturn(hospital);

        GeoResults geoResults = finderService.findGeoElements(FinderLevel.hospital, finderData);

        assertNotNull(geoResults);

        assertEquals("hospital", geoResults.getCandidates().get(0).getLevel());
        assertEquals("ITA", geoResults.getCandidates().get(0).getIso3());
        assertEquals(7.6744, geoResults.getCandidates().get(0).getLongitude());
        assertEquals(45.04191, geoResults.getCandidates().get(0).getLatitude());
        assertEquals("5.680277544163E-4",geoResults.getCandidates().get(0).getDistance().toString());

        assertEquals("PIEMONTE", geoResults.getCandidates().get(0).getHierarchy().getRegion());
        assertEquals("TORINO", geoResults.getCandidates().get(0).getHierarchy().getProvince());
        assertEquals("TORINO", geoResults.getCandidates().get(0).getHierarchy().getMunicipality());
        assertEquals("TORINO", geoResults.getCandidates().get(0).getHierarchy().getSettlement());
        assertEquals("CORSO", geoResults.getCandidates().get(0).getHierarchy().getStreetType());
        assertEquals("BRAMANTE", geoResults.getCandidates().get(0).getHierarchy().getStreetName());
        assertEquals("CORSO BRAMANTE", geoResults.getCandidates().get(0).getHierarchy().getStreet());
        assertEquals("10126", geoResults.getCandidates().get(0).getHierarchy().getPostcode());
        assertEquals("88", geoResults.getCandidates().get(0).getHierarchy().getHouseNumber());

        assertEquals("MOLINETTE", geoResults.getCandidates().get(0).getOther().getPlace());
        assertEquals("0116331633", geoResults.getCandidates().get(0).getOther().getPhoneNumber());

        assertEquals(0, geoResults.getStatus().getCode());
        assertEquals("Geo elements found", geoResults.getStatus().getDescription());
    }
    // spotless:on
}
