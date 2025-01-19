package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geocode.search.model.response.Status;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoResults implements Serializable {

    private List<GeoElement> candidates;
    private Status status;
}
