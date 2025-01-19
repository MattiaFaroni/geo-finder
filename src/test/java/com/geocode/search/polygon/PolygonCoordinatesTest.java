package com.geocode.search.polygon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.geocode.search.entity.Settlement;
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
public class PolygonCoordinatesTest {

    @Autowired
    private PolygonService polygonService;

    @MockitoBean
    private SettlementRepository settlementRepository;

    // spotless:off
    @Test
    public void testPolygonCoordinates() {

        double longitude = 9.284863449488805;
        double latitude = 44.72515737244064;

        Settlement settlement = new Settlement();
        settlement.setArea_id(20502334L);
        when(settlementRepository.intersect(longitude, latitude)).thenReturn(settlement);

        List<String> settlementPolygon = List.of("{\"type\":\"MultiPolygon\",\"coordinates\":[[[[9.28387,44.72576],[9.28434,44.72594],[9.28586,44.7257],[9.28514,44.7233],[9.28411,44.72331],[9.28348,44.7256],[9.28387,44.72576]]]]}");
        when(settlementRepository.findGeoJson(settlement.getArea_id())).thenReturn(settlementPolygon);

        PolygonResult polygonResult = polygonService.findGeoJson(PolygonLevel.settlement, longitude, latitude);

        assertNotNull(polygonResult);

        assertEquals("[[44.72576, 9.28387], [44.72594, 9.28434], [44.7257, 9.28586], [44.7233, 9.28514], [44.72331, 9.28411], [44.7256, 9.28348], [44.72576, 9.28387]]", polygonResult.getCoordinates().get(0).get(0).toString());

        assertEquals(0, polygonResult.getStatus().getCode());
        assertEquals("Polygon found", polygonResult.getStatus().getDescription());
    }
    // spotless:on
}
