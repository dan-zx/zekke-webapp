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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;
import com.zekke.webapp.data.Route;
import com.zekke.webapp.data.dao.PathDao;
import com.zekke.webapp.data.dao.PlaceDao;
import com.zekke.webapp.service.RouteFinderService;
import com.zekke.webapp.service.ServiceException;
import com.zekke.webapp.util.ExtendedCollections;

/**
 * Implementation of RouteFinderService interface. Provides a optimal routes for
 * private spaces using the Dijkstra's algorithm.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("routeFinderService")
public class DijkstraRouteFinderService implements RouteFinderService, Serializable {

    private static final long serialVersionUID = -5532924222197984508L;
    private static final Logger LOG = LoggerFactory.getLogger(DijkstraRouteFinderService.class);

    private PlaceDao placeDao;
    private PathDao pathDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true, noRollbackFor = ServiceException.class)
    public Route findRoute(GeoPoint rootPosition, GeoPoint targetPosition) {
        Place root = placeDao.readByPosition(rootPosition);
        Place target = placeDao.readByPosition(targetPosition);

        validateRootAndTarget(root, target);

        List<Place> g = placeDao.readList();

        Map<Place, Double> d = initDistanceMap(root, g);
        Map<Place, Place> parent = new HashMap<>();
        Place u = root;

        while (!u.equals(target)) {
            List<Place> neighbors = placeDao.readByConnection(u.getId());

            if (!ExtendedCollections.isNullOrEmpty(neighbors)) {
                for (Place v : neighbors) {
                    if (g.contains(v)) {
                        double wUV = pathDao.readDistance(v.getId(), u.getId());
                        if (d.get(v) > d.get(u) + wUV) {
                            d.put(v, d.get(u) + wUV);
                            parent.put(v, u);
                        }
                    }
                }
            }

            g.remove(u);
            u = findMinimumDU(d, g);
        }

        return buildRoute(target, d, parent);
    }

    /**
     * Asserts that root and target are not {@code null}.
     *
     * @param root the root Place.
     * @param target the target Place.
     */
    private void validateRootAndTarget(Place root, Place target) {
        if (root == null && target == null) {
            throw new ServiceException.Builder()
                    .setMessageResource("routeFinderService.rootAndTarget.notFound")
                    .build();
        } else if (root == null && target != null) {
            throw new ServiceException.Builder()
                    .setMessageResource("routeFinderService.root.notFound")
                    .build();
        } else if (root != null && target == null) {
            throw new ServiceException.Builder()
                    .setMessageResource("routeFinderService.target.notFound")
                    .build();
        }
    }

    /**
     * Initializes the distance map that will be used to rebuild the optimal
     * route.
     *
     * @param root the root Place.
     * @param g the list of "nodes".
     * @return the distance map.
     */
    private Map<Place, Double> initDistanceMap(Place root, List<Place> g) {
        Map<Place, Double> d = new HashMap<>(g.size());

        for (Place p : g) {
            d.put(p, Double.POSITIVE_INFINITY);
        }

        d.put(root, 0.0);
        return d;
    }

    /**
     * Returs an unvisited place with the minimum distance.
     *
     * @param d the distance map.
     * @param g the list of "nodes".
     * @return a Place
     */
    private Place findMinimumDU(Map<Place, Double> d, List<Place> g) {
        Place min = null;

        for (Place entry : d.keySet()) {
            if (min == null || (d.get(min) > d.get(entry))) {
                if (g.contains(entry)) {
                    min = entry;
                }
            }
        }

        return min;
    }

    /**
     * Builds the route when the algorithm finishes.
     *
     * @param target the target Place.
     * @param d the distance map.
     * @param parent the parent map.
     * @return the optimal Route if exists; otherwise {@code null}.
     */
    private Route buildRoute(Place target, Map<Place, Double> d, Map<Place, Place> parent) {
        List<Place> path = new ArrayList<>(parent.size());
        path.add(target);
        Place pathPlace = parent.get(target);

        while (pathPlace != null) {
            path.add(pathPlace);
            pathPlace = parent.get(pathPlace);
        }

        Route route = new Route();
        route.setDistance(d.get(target));
        route.setPath(path);
        return route;
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

    /**
     * Sets a PathDao. It's currently used by Spring.
     *
     * @param pathDao a PathDao.
     */
    @Inject
    public void setPathDao(PathDao pathDao) {
        this.pathDao = pathDao;
    }
}