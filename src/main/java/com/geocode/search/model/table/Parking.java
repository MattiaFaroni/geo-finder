package com.geocode.search.model.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking")
public class Parking extends Place {}
