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
package com.zekke.webapp.data.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;
import com.zekke.webapp.data.dao.PlaceDao;

/**
 * Hibernate implementation of PlaceDAO interface.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("placeDao")
public class PlaceHibernateDao extends GenericHibernateDao<Place, Long> implements PlaceDao {

    private static final long serialVersionUID = 3455167350339365334L;
    private static final Logger LOG = LoggerFactory.getLogger(PlaceHibernateDao.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Place> readList() {
        LOG.debug("Read list of Places");
        return getSessionFactory().getCurrentSession()
                .getNamedQuery("Place.readList")
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Place> readByConnection(Long placeId) {
        LOG.debug("Read list of places by connection --> placeId={}", placeId);
        return getSessionFactory().getCurrentSession()
                .getNamedQuery("Place.readByConnection")
                .setLong("placeId", placeId)
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Place> readLikeName(String name) {
        LOG.debug("Read list of places like --> name={}", name);
        return getSessionFactory().getCurrentSession()
                .getNamedQuery("Place.readLikeName")
                .setString("name", name)
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> readNamesInAreaLikeName(String name, GeoPoint center, Double radius) {
        LOG.debug("Read list of names in area center={}, radius={}, like --> name={}", name, center, radius);
        return getSessionFactory().getCurrentSession()
                .getNamedQuery("Place.readNamesInAreaLikeName")
                .setString("name", name)
                .setDouble("radius", radius)
                .setProperties(center)
                .setMaxResults(10)
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place readByPosition(GeoPoint position) {
        LOG.debug("Read place by --> position={}", position);
        return (Place) getSessionFactory().getCurrentSession()
                .getNamedQuery("Place.readByPosition")
                .setMaxResults(1)
                .setProperties(position)
                .uniqueResult();
    }
}
