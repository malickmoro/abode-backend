/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.morosa.abode.entity.Location;
import com.morosa.abode.payload.response.GPSResponse.Data.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author plutus
 */
@Getter
@Setter
public class GPSResponse {

    private boolean found;
    private Data data;

    @Getter
    @Setter
    public static class Data {

        @JsonProperty("Table")
        private List<Table> table;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Table {

            @JsonProperty("District")
            private String district;

            @JsonProperty("GPSName")
            private String gpsName;

            @JsonProperty("Region")
            private String region;

            @JsonProperty("CenterLongitude")
            private Double centerLongitude;

            @JsonProperty("CenterLatitude")
            private Double centerLatitude;

            @JsonProperty("PostCode")
            private String postCode;
        }
    }

    public Location toLocation() {
        if (data == null || data.table == null || data.table.isEmpty()) {
            return null;
        }

        Table table = data.getTable().get(0);
        Location location = new Location();
        location.setPostCode(table.postCode);
        location.setGpsAddress(table.gpsName);
        location.setLatitude(table.centerLatitude);
        location.setLongitude(table.centerLongitude);
        location.setRegion(table.region);
        location.setDistrict(table.district);  // You might want this too
        return location;
    }
}
