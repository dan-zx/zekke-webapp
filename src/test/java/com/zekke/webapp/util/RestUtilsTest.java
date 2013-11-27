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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.Messages;

public class RestUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(RestUtilsTest.class);

    @Test
    public void testToJson() {
        LOG.trace("testToJson");

        IllegalArgumentException ex = new IllegalArgumentException("Something wrong");
        String expected = String.format(RestUtils.JSON_EXCEPTION_FORMAT, ex.getClass().getSimpleName(), ex.getMessage());
        String actual = RestUtils.toJson(ex);

        assertNotNull(actual);
        assertEquals(expected, actual);

        actual = RestUtils.toJson(ex, ex.getMessage());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetClientLocale() {
        LOG.trace("testGetClientLocale");

        HttpHeaders headers = mock(HttpHeaders.class);
        when(headers.getAcceptableLanguages()).thenReturn(Arrays.asList(Locale.FRENCH, Locale.KOREAN));

        Locale expected = Messages.DEFAULT_LOCALE;
        Locale actual = RestUtils.getClientLocale(headers, Messages.SUPPORTED_LOCALES, Messages.DEFAULT_LOCALE);
        assertNotNull(actual);
        assertEquals(expected, actual);

        expected = new Locale("es");

        when(headers.getAcceptableLanguages()).thenReturn(Arrays.asList(Locale.CHINESE, expected));

        actual = RestUtils.getClientLocale(headers, Messages.SUPPORTED_LOCALES, Messages.DEFAULT_LOCALE);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}