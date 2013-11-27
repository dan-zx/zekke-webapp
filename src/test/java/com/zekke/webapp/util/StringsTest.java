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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringsTest.class);

    @Test
    public void testIsNullOrBlank() throws Exception {
        LOG.trace("testIsNullOrBlank");

        assertTrue(Strings.isNullOrBlank(null));
        assertTrue(Strings.isNullOrBlank(""));
        assertTrue(Strings.isNullOrBlank("       	  "));
        assertTrue(Strings.isNullOrBlank("\t"));
        assertTrue(Strings.isNullOrBlank("\n"));
        assertTrue(Strings.isNullOrBlank("\r"));
    }
}