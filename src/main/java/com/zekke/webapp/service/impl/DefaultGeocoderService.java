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
package com.zekke.webapp.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;
import com.zekke.webapp.data.dao.PlaceDao;
import com.zekke.webapp.service.GeocoderService;

/**
 * Geocoder Service default implementation. Provides a custom geocoder for
 * private spaces.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("geocoderService")
public class DefaultGeocoderService implements GeocoderService, Serializable {

    private static final long serialVersionUID = -4847250660509635313L;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultGeocoderService.class);

    private PlaceDao placeDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Place findByPosition(GeoPoint position) {
        return placeDao.readByPosition(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Place> findLikeName(String name) {
        return placeDao.readLikeName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> findNamesInAreaLikeName(String name, GeoPoint center, Double radius) {
        return placeDao.readNamesInAreaLikeName(name, center, radius);
    }

    /**
     * Sets a PlaceDao. It's currently used by Spring.
     *
     * @param placeDao a PlaceDao.
     */
    @Inject
    public void setPlaceDao(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }
}