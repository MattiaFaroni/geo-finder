package com.geocode.search.model.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital extends Place {}
