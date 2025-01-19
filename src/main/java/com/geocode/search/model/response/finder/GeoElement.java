package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoElement implements Serializable {

    private String level;
    private String iso3;
    private Double longitude;
    private Double latitude;
    private Double distance;
    private Hierarchy hierarchy;
    private Other other;
}
