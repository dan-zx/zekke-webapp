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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zekke.webapp.config.TestConfig;
import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;
import com.zekke.webapp.data.Route;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RouteFinderServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(RouteFinderServiceTest.class);

    @Inject
    private RouteFinderService routeFinderService;

    @Before
    public void setUp() throws Exception {
        LOG.trace("setUp");

        assertNotNull(routeFinderService);
    }

    @Test
    public void testFindShortestRoute() throws Exception {
        LOG.trace("findShortestRoute");

        Route expected = new Route();
        expected.setPath(buildExpectedRoute());
        expected.setDistance(159.2452279695584);
        Place root = expected.getPath().get(expected.getPath().size() - 1);
        Place target = expected.getPath().get(0);

        Route result = routeFinderService.findRoute(root.getPosition(), target.getPosition());

        assertNotNull(result);
        assertNotNull(result.getDistance());
        assertTrue(result.getDistance() > 0);
        assertEquals(expected.getDistance(), result.getDistance());
        assertNotNull(result.getPath());
        assertFalse(result.getPath().isEmpty());
        assertEquals(expected.getPath().size(), result.getPath().size());
        assertArrayEquals(expected.getPath().toArray(), result.getPath().toArray());
    }

    private List<Place> buildExpectedRoute() {
        Place p9 = new Place();
        p9.setId(9l);
        p9.setName("Sorteo UDLA");
        GeoPoint point9 = new GeoPoint();
        point9.setLatitude(19.0564036015444);
        point9.setLongitude(-98.2824441790581);
        p9.setPosition(point9);

        Place p8 = new Place();
        p8.setId(8l);
        p8.setName("Estacionamiento 9 - Entrada");
        GeoPoint point8 = new GeoPoint();
        point8.setLatitude(19.0564365593696);
        point8.setLongitude(-98.2830584049225);
        p8.setPosition(point8);

        Place p7 = new Place();
        p7.setId(7l);
        p7.setName("Planta Fisica");
        GeoPoint point7 = new GeoPoint();
        point7.setLatitude(19.0564568411049);
        point7.setLongitude(-98.2834419608116);
        p7.setPosition(point7);

        Place p6 = new Place();
        p6.setId(6l);
        p6.setName("Acceso Principal");
        GeoPoint point6 = new GeoPoint();
        point6.setLatitude(19.0568650105012);
        point6.setLongitude(-98.2837209105492);
        p6.setPosition(point6);

        return Arrays.asList(p9, p8, p7, p6);
    }
}