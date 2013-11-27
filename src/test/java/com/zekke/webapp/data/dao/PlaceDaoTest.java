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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import org.springframework.transaction.annotation.Transactional;

import com.zekke.webapp.config.TestConfig;
import com.zekke.webapp.data.GeoPoint;
import com.zekke.webapp.data.Place;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlaceDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceDaoTest.class);

    @Inject
    private PlaceDao placeDao;

    @Before
    public void setUp() {
        LOG.trace("setUp");

        assertNotNull(placeDao);
    }

    @Test
    @Transactional
    public void testCRUD() throws Exception {
        LOG.trace("testCRUD");

        Place p = new Place();
        p.setName("Test place");
        GeoPoint point = new GeoPoint();
        point.setLatitude(20.777);
        point.setLongitude(99.555);
        p.setPosition(point);

        testCreate(p);
        testRead(p);
        testUpdate(p);
        testDelete(p);
    }

    private void testCreate(Place p) throws Exception {
        LOG.trace("testCreate");

        placeDao.save(p);

        assertNotNull(p.getId());
    }

    private void testRead(Place p) throws Exception {
        LOG.trace("testRead");

        Place pFromDB = placeDao.readById(p.getId());

        assertNotNull(pFromDB);
        assertEquals(p, pFromDB);
    }

    private void testUpdate(Place p) throws Exception {
        LOG.trace("testUpdate");

        p.setName("New name");
        GeoPoint point = new GeoPoint();
        point.setLatitude(9.2222);
        point.setLongitude(-24.7775);
        p.setPosition(point);
        placeDao.update(p);
        Place pFromDB = placeDao.readById(p.getId());

        assertNotNull(pFromDB);
        assertEquals(p, pFromDB);
    }

    private void testDelete(Place p) throws Exception {
        LOG.trace("testDelete");

        placeDao.delete(p);
        Place pFromDB = placeDao.readById(p.getId());

        assertNull(pFromDB);
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadList() throws Exception {
        LOG.trace("testReadList");

        List<Place> expected = placeDao.readList();

        assertNotNull(expected);
        assertFalse(expected.isEmpty());
        assertEquals(154, expected.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadByPosition() throws Exception {
        LOG.trace("testReadByPosition");

        Place p = new Place();
        p.setId(129L);
        p.setName("CIRIA - 2");
        GeoPoint point = new GeoPoint();
        point.setLatitude(19.0540128741435);
        point.setLongitude(-98.2830825448036);
        p.setPosition(point);
        GeoPoint position = new GeoPoint();
        position.setLatitude(19.053952);
        position.setLongitude(-98.283028);
        Place pFromDB = placeDao.readByPosition(position);

        assertEquals(p, pFromDB);
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadByConnection() throws Exception {
        LOG.trace("testReadByConnection");

        Place p1 = new Place();
        p1.setId(1L);
        p1.setName("Area de esparcimiento");
        GeoPoint pp1 = new GeoPoint();
        pp1.setLatitude(19.0580159920845);
        pp1.setLongitude(-98.2833185791969);
        p1.setPosition(pp1);

        Place p2 = new Place();
        p2.setId(4L);
        p2.setName("Cancha de beisbol 1 - Salida");
        GeoPoint pp2 = new GeoPoint();
        pp2.setLatitude(19.0582416241016);
        pp2.setLongitude(-98.2835975289345);
        p2.setPosition(pp2);

        Place p3 = new Place();
        p3.setId(5L);
        p3.setName("Parada Autobus Foraneo");
        GeoPoint pp3 = new GeoPoint();
        pp3.setLatitude(19.057620501516);
        pp3.setLongitude(-98.2837477326393);
        p3.setPosition(pp3);

        List<Place> expected = Arrays.asList(p1, p2, p3);

        List<Place> actual = placeDao.readByConnection(2L);

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(expected.size(), expected.size());
        assertEquals(expected, expected);
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadLikeName() throws Exception {
        LOG.trace("testReadLikeName");

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

        List<Place> actual = placeDao.readLikeName("Humanidades");

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(expected.size(), expected.size());
        assertEquals(expected, expected);
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadNamesInAreaLikeName() throws Exception {
        LOG.trace("testReadNamesInAreaLikeName");

        List<String> expected = Arrays.asList("Estacionamiento 6", "Estacionamiento 5");

        GeoPoint center = new GeoPoint();
        center.setLatitude(19.05351);
        center.setLongitude(-98.28321);
        List<String> list = placeDao.readNamesInAreaLikeName("Estacionamiento", center, 200d);

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertTrue(list.size() <= 10);
        assertEquals(expected, list);
    }
}