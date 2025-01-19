package com.geocode.search.model.response;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status implements Serializable {

    private Integer code;
    private String description;

    public Status(Integer code, Definition definition) {
        this.code = code;
        this.description = definition.getDescription();
    }
}
