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
package com.zekke.webapp.service;

import java.util.List;

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;

/**
 * Geocoder service interface. Provides a custom geocoder for private spaces
 * that translates from coordinates to places and vice versa.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public interface GeocoderService {

    /**
     * Retrieves the place located at the given geographic point.
     *
     * @param position a geographic point.
     * @return a place.
     */
    Place findByPosition(GeoPoint position);

    /**
     * Retrieves the list of places with a name like the given name.
     *
     * @param name a name.
     * @return a list of places.
     */
    List<Place> findLikeName(String name);

    /**
     * Retrieves the list of places' names that are in the radius of the given
     * geographic point with the name like the given name.
     *
     * @param name a name.
     * @param center a geographic point.
     * @param radius a radius.
     * @return a list of places' names.
     */
    List<String> findNamesInAreaLikeName(String name, GeoPoint center, Double radius);
}