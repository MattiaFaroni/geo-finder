package com.geocode.search.model.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipality")
public class Municipality extends Territory {}
