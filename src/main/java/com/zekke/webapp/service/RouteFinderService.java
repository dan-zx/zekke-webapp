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

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Route;

/**
 * Route finder service interface. Provides a optimal routes for private spaces.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public interface RouteFinderService {

    /**
     * Provides an optimal route between the given positions if exists;
     * otherwise {@code null}.
     *
     * @param rootPosition the root geographic position.
     * @param targetPosition the target geographic position.
     * @return a Route.
     */
    Route findRoute(GeoPoint rootPosition, GeoPoint targetPosition);
}