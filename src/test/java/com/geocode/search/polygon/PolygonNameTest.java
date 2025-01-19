package com.geocode.search.polygon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.geocode.search.model.levels.PolygonLevel;
import com.geocode.search.model.response.polygon.PolygonResult;
import com.geocode.search.repository.SettlementRepository;
import com.geocode.search.service.PolygonService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PolygonNameTest {

    @Autowired
    private PolygonService polygonService;

    @MockitoBean
    private SettlementRepository settlementRepository;

    // spotless:off
    @Test
    public void testPolygonName() {

        String testName = "CASOTTO";
        String testIso3 = "ITA";

        List<String> settlementPolygon = List.of("[{\"type\":\"MultiPolygon\",\"coordinates\":[[[[9.29037,44.93658],[9.29027,44.93657],[9.29022,44.93678],[9.29044,44.93699],[9.29078,44.93661],[9.29037,44.93658]]]]}]");
        when(settlementRepository.findGeoJson(testName, testIso3)).thenReturn(settlementPolygon);

        PolygonResult polygonResult = polygonService.findGeoJson(PolygonLevel.settlement, testName, testIso3);

        assertNotNull(polygonResult);

        assertEquals("[[44.93658, 9.29037], [44.93657, 9.29027], [44.93678, 9.29022], [44.93699, 9.29044], [44.93661, 9.29078], [44.93658, 9.29037]]", polygonResult.getCoordinates().get(0).get(0).toString());

        assertEquals(0, polygonResult.getStatus().getCode());
        assertEquals("Polygon found", polygonResult.getStatus().getDescription());
    }
    // spotless:on
}
