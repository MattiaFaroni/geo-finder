package com.geocode.search.repository;

import com.geocode.search.entity.CompositeKey;
import com.geocode.search.entity.Settlement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SettlementRepository extends JpaRepository<Settlement, CompositeKey> {

	// spotless:off
	@Query(value = "SELECT ST_AsGeoJSON(geom) from settlement where polygon_nm = :polygon_nm and iso3 = :iso3", nativeQuery = true)
	List<String> findGeoJson(@Param("polygon_nm") String name, @Param("iso3") String iso3);

	@Query(value = "SELECT ST_AsGeoJSON(geom) from settlement WHERE area_id = :areaId", nativeQuery = true)
	List<String> findGeoJson(@Param("areaId") double areaId);

	@Query(value = "SELECT * from settlement WHERE ST_Intersects(geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude),4326))", nativeQuery = true)
	Settlement intersect(@Param("longitude") double longitude, @Param("latitude") double latitude);
	// spotless:on
}
