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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class GeocoderWebServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(GeocoderWebServiceTest.class);

    @Inject
    private GeocoderWebService geocoderWebService;

    @Before
    public void setUp() {
        LOG.trace("setUp");

        assertNotNull(geocoderWebService);
    }

    @Test
    public void testFindByPosition() throws Exception {
        LOG.trace("testFindByPosition");

        Place p = new Place();
        p.setId(129l);
        p.setName("CIRIA - 2");
        GeoPoint point = new GeoPoint();
        point.setLatitude(19.0540128741435);
        point.setLongitude(-98.2830825448036);
        p.setPosition(point);
        Place pFromDB = geocoderWebService.findByPosition(19.053952, -98.283028);

        assertEquals(p, pFromDB);
    }

    @Test
    public void testFindLikeName() throws Exception {
        LOG.trace("testFindLikeName");

        Place p1 = new Place();
        p1.setId(32L);
        p1.setName("Humanidades");
        GeoPoint pp1 = new GeoPoint();
        pp1.setLatitude(19.0535007520221);
        pp1.setLongitude(-98.2809099555016);
        p1.setPosition(pp1);

        Place p2 = new Place();
        p2.setId(33L);
        p2.setName("Humanidades - 2");
        GeoPoint pp2 = new GeoPoint();
        pp2.setLatitude(19.0532649724942);
        pp2.setLongitude(-98.2804620265961);
        p2.setPosition(pp2);

        Place p3 = new Place();
        p3.setId(34L);
        p3.setName("Humanidades - 3");
        GeoPoint pp3 = new GeoPoint();
        pp3.setLatitude(19.0528998938543);
        pp3.setLongitude(-98.2805827260017);
        p3.setPosition(pp3);

        Place p4 = new Place();
        p4.setId(35L);
        p4.setName("Humanidades - 4");
        GeoPoint pp4 = new GeoPoint();
        pp4.setLatitude(19.052547490794);
        pp4.setLongitude(-98.2811057567596);
        p4.setPosition(pp4);

        List<Place> expected = Arrays.asList(p1, p2, p3, p4);

        List<Place> actual = geocoderWebService.findLikeName("Humanidades");

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(expected.size(), expected.size());
        assertEquals(expected, expected);
    }

    @Test
    public void testFindNamesInAreaLikeName() throws Exception {
        LOG.trace("testFindNamesInAreaLikeName");

        List<String> expected = Arrays.asList("Estacionamiento 6", "Estacionamiento 5");

        List<String> actual = geocoderWebService.findNamesInAreaLikeName("Estacionamiento", 19.05351, -98.28321, 200d);

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }
}