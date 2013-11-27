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
package com.zekke.webapp.data.dao;

import java.util.List;

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;

/**
 * Place Data Access Object interface.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public interface PlaceDao extends GenericDao<Place, Long> {

    /**
     * Retrieves the list of places connected with the given places.
     *
     * @param placeId the id from the place.
     * @return a list of places.
     */
    List<Place> readByConnection(Long placeId);

    /**
     * Retrieves the list of places with a name like the given name.
     *
     * @param name a name.
     * @return a list of places.
     */
    List<Place> readLikeName(String name);

    /**
     * Retrieves the list of places' names that are in the radius of the given
     * geographic point with the name like the given name.
     *
     * @param name a name.
     * @param center a geographic point.
     * @param radius a radius.
     * @return a list of places' names.
     */
    List<String> readNamesInAreaLikeName(String name, GeoPoint center, Double radius);

    /**
     * Retrieves the place located at the given geographic point.
     *
     * @param position a geographic point.
     * @return a place.
     */
    Place readByPosition(GeoPoint position);
}