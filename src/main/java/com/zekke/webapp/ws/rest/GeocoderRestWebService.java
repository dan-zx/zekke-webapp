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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;

import com.zekke.webapp.ZekkeException;
import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;
import com.zekke.webapp.service.GeocoderService;
import com.zekke.webapp.ws.GeocoderWebService;
import com.zekke.webapp.ws.WebServiceException;

/**
 * Geocoder RESTful web service implementation. It's currently accessed through
 * https://zekke.herokuapp.com/api/v1/geocoder/
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("geocoderWebService")
@Path(BaseRestWebService.VER_1_URL_PREFIX + "geocoder")
public class GeocoderRestWebService extends BaseRestWebService implements GeocoderWebService {

    private static final long serialVersionUID = -7489392067354820088L;
    private static final Logger LOG = LoggerFactory.getLogger(GeocoderRestWebService.class);

    private GeocoderService geocoderService;

    /**
     * Retrieves the place located at the given geographic point. Example URL:
     * https://zekke.herokuapp.com/api/v1/geocoder/places/by-position/place.json?latitude=some_value&longitude=some_value
     *
     * @param latitude the latitude coordinate.
     * @param longitude the longitude coordinate.
     * @return a Place in json format.
     */
    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/places/by-position/place.json")
    public Place findByPosition(
            @QueryParam("latitude")  Double latitude,
            @QueryParam("longitude") Double longitude) {
        GeoPoint position = new GeoPoint();
        position.setLatitude(latitude);
        position.setLongitude(longitude);

        try {
            return geocoderService.findByPosition(position);
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
     * Retrieves the list of places with a name like the given name. Example
     * URL:
     * https://zekke.herokuapp.com/api/v1/geocoder/places/like/some_name.json
     *
     * @param name a name.
     * @return a list of places in json format.
     */
    @GET
    @Override
    @Path("/places/like/{name}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> findLikeName(@PathParam("name") String name) {
        try {
            return geocoderService.findLikeName(name);
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
     * Retrieves the list of places' names that are in the radius of the given
     * geographic point with the name like the given name. Example URL:
     * https://zekke.herokuapp.com/api/v1/geocoder/names/in-area/like/some_name.json?latitude=some_value&longitude=some_value&radius=some_value
     *
     * @param name a name.
     * @param latitude the latitude coordinate.
     * @param longitude the longitude coordinate.
     * @param radius a radius.
     * @return a list of places' names in json format.
     */
    @GET
    @Override
    @Path("/names/in-area/like/{name}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> findNamesInAreaLikeName(
            @PathParam("name")       String name,
            @QueryParam("latitude")  Double latitude,
            @QueryParam("longitude") Double longitude,
            @QueryParam("radius")    Double radius) {
        GeoPoint center = new GeoPoint();
        center.setLatitude(latitude);
        center.setLongitude(longitude);

        try {
            return geocoderService.findNamesInAreaLikeName(name, center, radius);
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
     * Sets a GeocoderService. It's currently used by Spring.
     *
     * @param geocoderService a GeocoderService.
     */
    @Inject
    public void setGeocoderService(GeocoderService geocoderService) {
        this.geocoderService = geocoderService;
    }
}