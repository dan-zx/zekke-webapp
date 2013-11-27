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
package com.zekke.webapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagesTest {

    private static final Logger LOG = LoggerFactory.getLogger(MessagesTest.class);
    private static final String MISSING_RESOURCE_KEY_FORMAT = "???%s???";

    @Test
    public void testGetMessage() throws Exception {
        LOG.trace("testGetMessage");

        String key = "mock.message";
        String keyNotFoundStr = String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        String actual = Messages.get(key);

        assertNotNull(actual);

        assertFalse(actual.trim().isEmpty());
        assertFalse(actual.equals(keyNotFoundStr));
    }

    @Test
    public void testGetFormattedMessage() throws Exception {
        LOG.trace("testGetFormattedMessage");

        String key = "mock.message.with.args";
        Object[] formatArgs = {56.7, "arg0ToBind", false};
        String keyNotFoundStr = String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        String actual = Messages.get(key, formatArgs);

        assertNotNull(actual);

        assertFalse(actual.trim().isEmpty());
        assertFalse(actual.equals(keyNotFoundStr));

        for (Object formatArg : formatArgs) {
            assertTrue(actual.contains(formatArg.toString()));
        }
    }

    @Test
    public void testGetLocaleMessage() throws Exception {
        LOG.trace("testGetLocaleMessage");

        Locale locale = null;

        for (Locale l : Messages.SUPPORTED_LOCALES) {
            if (!l.equals(Messages.DEFAULT_LOCALE)) {
                locale = l;
            }
        }

        String key = "mock.message";
        String keyNotFoundStr = String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        String actual = Messages.get(key, locale);

        assertNotNull(actual);

        assertFalse(actual.trim().isEmpty());
        assertFalse(actual.equals(keyNotFoundStr));
    }

    @Test
    public void testGetFormattedLocaleMessage() throws Exception {
        LOG.trace("testGetFormattedLocaleMessage");

        Locale locale = null;

        for (Locale l : Messages.SUPPORTED_LOCALES) {
            if (!l.equals(Messages.DEFAULT_LOCALE)) {
                locale = l;
            }
        }

        String key = "mock.message.with.args";
        Object[] formatArgs = {56, "arg0ToBind", false};
        String keyNotFoundStr = String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        String actual = Messages.get(key, locale, formatArgs);

        assertNotNull(actual);

        assertFalse(actual.trim().isEmpty());
        assertFalse(actual.equals(keyNotFoundStr));

        for (Object formatArg : formatArgs) {
            assertTrue(actual.contains(formatArg.toString()));
        }
    }
}