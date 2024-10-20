package com.geocode.search.distance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.geocode.search.model.request.DistanceData;
import com.geocode.search.model.response.distance.DistanceResult;
import com.geocode.search.service.DistanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DistanceTest {

	@Autowired
	private DistanceService distanceService;

	@Test
	// spotless:off
    public void testDistanceService() {
        DistanceData distanceData = new DistanceData(45.34013837109795, 10.99827575828819, 45.340338619992515, 10.997156123590209);

        DistanceResult distanceResult = distanceService.calculateDistance(distanceData);

        assertNotNull(distanceResult);

        assertEquals(0.09053369083750669, distanceResult.getDistance());
    }
    // spotless:on

}
