package com.geocode.search.repository;

import com.geocode.search.model.table.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {

	Street findStreetByLinkId(Long linkId);
}
