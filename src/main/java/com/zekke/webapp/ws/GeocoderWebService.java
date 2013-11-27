/*
 * Copyright 2013 ZeKKe Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zekke.webapp.ws;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zekke.webapp.data.Place;

/**
 * Geocoder web service interface represents a custom geocoder for private
 * spaces that translates from coordinates to places and viceversa.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public interface GeocoderWebService {

    /**
     * Retrieves the place located at the given geographic point.
     *
     * @param latitude the latitude coordinate.
     * @param longitude the longitude coordinate.
     * @return a Place
     */
    Place findByPosition(
            @NotNull(message = "latitude.required")  @Range(message = "latitude.notValid",  min =  -85, max =  85) Double latitude,
            @NotNull(message = "longitude.required") @Range(message = "longitude.notValid", min = -180, max = 180) Double longitude
    );

    /**
     * Retrieves the list of places with a name like the given name.
     *
     * @param name a name.
     * @return a list of places.
     */
    List<Place> findLikeName(
            @NotBlank(message = "place.name.required") String name
    );

    /**
     * Retrieves the list of places' names that are in the radius of the given
     * geographic point with the name like the given name.
     *
     * @param name a name.
     * @param latitude the latitude coordinate.
     * @param longitude the longitude coordinate.
     * @param radius a radius.
     * @return a list of places' names.
     */
    List<String> findNamesInAreaLikeName(
            @NotBlank(message = "place.name.required") String name,
            @NotNull(message = "latitude.required")  @Range(message = "latitude.notValid",  min =  -85, max =  85) Double latitude,
            @NotNull(message = "longitude.required") @Range(message = "longitude.notValid", min = -180, max = 180) Double longitude,
            @NotNull(message = "radius.required") Double radius);
}