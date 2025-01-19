package com.geocode.search.model.request;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DistanceData {

    private double latitude_1;
    private double longitude_1;
    private double latitude_2;
    private double longitude_2;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
