package com.geocode.search.repository;

import com.geocode.search.entity.CompositeKey;
import com.geocode.search.entity.Municipality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MunicipalityRepository extends JpaRepository<Municipality, CompositeKey> {

    // spotless:off
	@Query(value = "SELECT ST_AsGeoJSON(geom) from municipality where polygon_nm = :polygon_nm and iso3 = :iso3", nativeQuery = true)
	List<String> findGeoJson(@Param("polygon_nm") String name, @Param("iso3") String iso3);

	@Query(value = "SELECT ST_AsGeoJSON(geom) from municipality WHERE area_id = :areaId", nativeQuery = true)
	List<String> findGeoJson(@Param("areaId") double areaId);

	@Query(value = "SELECT * from municipality WHERE ST_Intersects(geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude),4326))", nativeQuery = true)
	Municipality intersect(@Param("longitude") double longitude, @Param("latitude") double latitude);
	// spotless:on
}
