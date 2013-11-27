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
import com.zekke.webapp.data.Path;
import com.zekke.webapp.data.Place;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PathDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger(PathDaoTest.class);

    @Inject
    private PathDao pathDao;
    @Inject
    private PlaceDao placeDao;

    @Before
    public void setUp() {
        LOG.trace("setUp");

        assertNotNull(pathDao);
        assertNotNull(placeDao);
    }

    @Test
    @Transactional
    public void testCRUD() throws Exception {
        LOG.trace("testCRUD");

        Place p1 = new Place();
        p1.setName("Test place 2");
        GeoPoint point1 = new GeoPoint();
        point1.setLatitude(20.777);
        point1.setLongitude(99.555);
        p1.setPosition(point1);

        Place p2 = new Place();
        p2.setName("Test place 1");
        GeoPoint point2 = new GeoPoint();
        point2.setLatitude(10.222);
        point2.setLongitude(88.333);
        p2.setPosition(point2);

        Path e = new Path();
        e.setDistance(55.3233);
        e.setFromPlace(p1);
        e.setToPlace(p2);

        placeDao.save(p1);
        placeDao.save(p2);

        testCreate(e);
        testRead(e);
        testUpdate(e);
        testDelete(e);

        placeDao.delete(p1);
        placeDao.delete(p2);
    }

    private void testCreate(Path e) throws Exception {
        LOG.trace("testCreate");

        pathDao.save(e);

        assertNotNull(e.getId());
    }

    private void testRead(Path e) throws Exception {
        LOG.trace("testRead");

        Path eFromDB = pathDao.readById(e.getId());

        assertNotNull(eFromDB);
        assertEquals(e, eFromDB);
    }

    private void testUpdate(Path d) throws Exception {
        LOG.trace("testUpdate");

        d.setDistance(3333.5534);
        pathDao.update(d);
        Path dFromDB = pathDao.readById(d.getId());

        assertNotNull(dFromDB);
        assertEquals(d, dFromDB);
    }

    private void testDelete(Path e) throws Exception {
        LOG.trace("testDelete");

        pathDao.delete(e);
        Path eFromDB = pathDao.readById(e.getId());

        assertNull(eFromDB);
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadList() throws Exception {
        LOG.trace("testReadList");

        List<Path> expected = pathDao.readList();

        assertNotNull(expected);
        assertFalse(expected.isEmpty());
        assertEquals(696, expected.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void testReadDistance() throws Exception {
        LOG.trace("testReadDistance");

        double expected = 35.8514802281527;
        Double actual = pathDao.readDistance(2L, 1L);

        assertNotNull(actual);
        assertEquals(expected, actual.doubleValue(), 0d);
    }
}