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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.zekke.webapp.data.Route;

/**
 * Route web service interface represents the route operations that should be
 * available in any kind of web service.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public interface RouteFinderWebService {

    /**
     * Provides an optimal route between the given positions if exists;
     * otherwise {@code null}.
     *
     * @param rootLatitude the root latitude coordinate.
     * @param rootLongitude the root longitude cooordinate.
     * @param targetLatitude the target latitude coordinate.
     * @param targetLongitude the target longitude coordinate.
     * @return a Route.
     */
    Route findRoute(
            @NotNull(message = "route.root.latitude.required")    @Range(message = "latitude.notValid",  min =  -85,  max =  85) Double rootLatitude,
            @NotNull(message = "route.root.longitude.required")   @Range(message = "longitude.notValid", min = -180,  max = 180) Double rootLongitude,
            @NotNull(message = "route.target.latitude.required")  @Range(message = "latitude.notValid",  min =  -85,  max =  85) Double targetLatitude,
            @NotNull(message = "route.target.longitude.required") @Range(message = "longitude.notValid", min = -180,  max = 180) Double targetLongitude
    );
}