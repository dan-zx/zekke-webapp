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
package com.zekke.webapp.ws.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.Range;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;

import com.zekke.webapp.ZekkeException;
import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Route;
import com.zekke.webapp.service.RouteFinderService;

/**
 * Route finder RESTful web service. It's currently accessed
 * through https://zekke.herokuapp.com/api/v1/route-finder
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("routeFinderWebService")
@Path(BaseWebService.VER_1_URL_PATH + "route-finder")
public class RouteFinderWebService extends BaseWebService {

    private static final long serialVersionUID = -7598784812983620304L;
    private static final Logger LOG = LoggerFactory.getLogger(RouteFinderWebService.class);

    private RouteFinderService routeFinderService;

    /**
     * Provides an optimal route between the given positions if exists;
     * otherwise {@code null}. Example URL:
     * https://zekke.herokuapp.com/api/v1/route-finder/route.json?root-latitude=some_value&root-longitude=some_value&target-latitude=some_value&target-longitude=some_value
     *
     * @param rootLatitude the root latitude coordinate.
     * @param rootLongitude the root longitude cooordinate.
     * @param targetLatitude the target latitude coordinate.
     * @param targetLongitude the target longitude coordinate.
     * @return a Route in json format.
     */
    @GET
    @Path("/route.json")
    @Produces(MediaType.APPLICATION_JSON)
    public Route findRoute(
            @NotNull(message = "{route.root.latitude.required}") @Range(message = "{latitude.notValid}", min = -85, max = 85) @QueryParam("root-latitude") Double rootLatitude,
            @NotNull(message = "{route.root.longitude.required}") @Range(message = "{longitude.notValid}", min = -180, max = 180) @QueryParam("root-longitude") Double rootLongitude,
            @NotNull(message = "{route.target.latitude.required}") @Range(message = "{latitude.notValid}", min = -85, max = 85) @QueryParam("target-latitude") Double targetLatitude,
            @NotNull(message = "{route.target.longitude.required}") @Range(message = "{longitude.notValid}", min = -180, max = 180) @QueryParam("target-longitude") Double targetLongitude) {
        GeoPoint rootPosition = new GeoPoint();
        rootPosition.setLatitude(rootLatitude);
        rootPosition.setLongitude(rootLongitude);

        GeoPoint targetPosition = new GeoPoint();
        targetPosition.setLatitude(targetLatitude);
        targetPosition.setLongitude(targetLongitude);

        try {
            return routeFinderService.findRoute(rootPosition, targetPosition);
        } catch (DataAccessException | TransactionException ex) {
            throw new WebServiceException.Builder()
                    .setMessageResource("webService.dataAccessFailure")
                    .setCause(ex)
                    .build();
        } catch (ZekkeException ex) {
            // Seems silly but it's the only way to be handled directly by ZekkeExceptionHandler class 
            // otherwise it will be wrapped into a WebServiceException hiding the real one in the front end
            throw ex;
        } catch (Exception ex) {
            throw new WebServiceException.Builder()
                    .setMessageResource("webService.unknownFailure")
                    .setCause(ex)
                    .build();
        }
    }

    /**
     * Sets a RouteFinderService. It's currently used by Spring.
     *
     * @param routeFinderService a RouteFinderService.
     */
    @Inject
    public void setRouteFinderService(RouteFinderService routeFinderService) {
        this.routeFinderService = routeFinderService;
    }
}