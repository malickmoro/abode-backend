/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morosa.abode.entity.Location;
import com.morosa.abode.payload.response.GPSResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author plutus
 */
@Slf4j
@Component
public class GPSUtil {

    private static final String URL = "https://ghanapostgps.sperixlabs.org/get-location";

    static {
        Unirest.config().connectTimeout(5000);
    }

    public Location getLocation(String gpsAddress) {
        try {
            HttpResponse<String> response = Unirest.post(URL)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("address", gpsAddress)
                    .asString();

            ObjectMapper mapper = new ObjectMapper();
            GPSResponse gpsResponse = mapper.readValue(response.getBody(), GPSResponse.class);

            if (gpsResponse.isFound() && gpsResponse.getData() != null && !gpsResponse.getData().getTable().isEmpty()) {
                return gpsResponse.toLocation();
            }

            return new Location();

        } catch (Exception e) {
            log.error("GPS lookup failed for: {}", gpsAddress, e);
            return null;
        }
    }
}
