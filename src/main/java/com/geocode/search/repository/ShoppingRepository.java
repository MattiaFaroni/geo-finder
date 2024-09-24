package com.geocode.search.repository;

import com.geocode.search.entity.Shopping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

	// spotless:off
	@Query(value = "SELECT id, ST_X(geom) as longitude, ST_Y(geom) as latitude, ROUND(ST_Distance(geom,ST_SetSRID(ST_MakePoint(:longitude, :latitude),4326))::numeric,6) as distance FROM shopping ORDER BY distance LIMIT :limit", nativeQuery = true)
	List<List<String>> searchNearbyShopping(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("limit") int limit);
	// spotless:on

	Shopping findShoppingById(Long shoppingId);
}
