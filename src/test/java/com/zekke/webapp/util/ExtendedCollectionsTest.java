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
package com.zekke.webapp.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedCollectionsTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExtendedCollectionsTest.class);

    @Test
    public void testIsCollectionNullOrEmpty() {
        LOG.trace("testIsCollectionNullOrEmpty");

        Collection<Integer> collectionWithElements = Arrays.asList(1, 2, 3);
        Collection<?> nullCollection = null;
        Collection<Object> emptyCollection = new HashSet<>(0);

        assertFalse(ExtendedCollections.isNullOrEmpty(collectionWithElements));
        assertTrue(ExtendedCollections.isNullOrEmpty(nullCollection));
        assertTrue(ExtendedCollections.isNullOrEmpty(emptyCollection));
    }

    @Test
    public void testIsMapNullOrEmpty() {
        LOG.trace("testIsMapNullOrEmpty");

        Map<Integer, Object> mapWithElements = new HashMap<>(1);
        mapWithElements.put(1, new Object());
        Map<?, ?> nullMap = null;
        Map<Integer, Object> emptyMap = new HashMap<>(0);

        assertFalse(ExtendedCollections.isNullOrEmpty(mapWithElements));
        assertTrue(ExtendedCollections.isNullOrEmpty(nullMap));
        assertTrue(ExtendedCollections.isNullOrEmpty(emptyMap));
    }
}