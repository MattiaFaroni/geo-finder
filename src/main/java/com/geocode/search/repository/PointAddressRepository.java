package com.geocode.search.repository;

import com.geocode.search.model.table.PointAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointAddressRepository extends JpaRepository<PointAddress, Long> {

	@Query(value = "SELECT id, ST_X(geom) as longitude, ST_Y(geom) as latitude,ST_Distance(geom,ST_SetSRID(ST_MakePoint(:longitude, :latitude),4326)) as distance FROM pointaddress ORDER BY distance LIMIT :limit", nativeQuery = true)
	List<List<String>> searchNearbyPointAddress(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("limit") int limit);

	PointAddress findPointAddressById(Long pointAddressId);
}
