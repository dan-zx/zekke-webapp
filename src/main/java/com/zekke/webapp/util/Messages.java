/*
 * Copyright 2013-2017 Daniel Pedraza-Arcega
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Utility class that contains methods which return strings from the message bundles located in
 * com/zekke/webapp/messages.properties.
 *
 * @author Daniel Pedraza-Arcega
 */
public class Messages {

    private static final String RESOURCE_BUNDLE_BASE_NAME = "com.zekke.webapp.messages";
    private static final String MISSING_RESOURCE_KEY_FORMAT = "???%s???";
    private static final Logger LOGGER = LoggerFactory.getLogger(Messages.class);
    private static final Set<Locale> SUPPORTED_LOCALES;

    static {
        Set<Locale> locales = new HashSet<>();
        locales.add(Locale.ROOT);
        SUPPORTED_LOCALES = unmodifiableSet(locales);
    }

    private Messages() {
        throw new AssertionError();
    }

    /**
     * Gets a message for the given key from {@value #RESOURCE_BUNDLE_BASE_NAME} resource bundle
     * formatted with the given format arguments.
     *
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @return {@value #MISSING_RESOURCE_KEY_FORMAT} if no message for the given key can be found;
     * otherwise the message for the given key formatted with the given format arguments.
     */
    public static String getMessage(String messageKey, Object... messageArguments) {
        return getMessage(messageKey, Locale.ROOT, messageArguments);
    }

    /**
     * Gets a message for the given key from {@value #RESOURCE_BUNDLE_BASE_NAME} resource bundle
     * formatted with the given format arguments.
     *
     * @param messageKey the key for the desired message.
     * @param locale the locale.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @return {@value #MISSING_RESOURCE_KEY_FORMAT} if no message for the given key can be found;
     * otherwise the message for the given key formatted with the given format arguments.
     */
    public static String getMessage(String messageKey, Locale locale, Object... messageArguments) {
        String message;
        try {
            message = getMessagesBundle(locale).getString(messageKey);
        } catch (MissingResourceException ex) {
            LOGGER.warn("Can't find message for key: [{}]", messageKey, ex);
            return String.format(MISSING_RESOURCE_KEY_FORMAT, messageKey);
        }
        if (messageArguments.length > 0) {
            try {
                return MessageFormat.format(message, messageArguments);
            } catch (IllegalArgumentException ex) {
                LOGGER.warn("Can't format message: [{}] with arguments: {}", message, Arrays.deepToString(messageArguments), ex);
            }
        }
        return message;
    }

    private static ResourceBundle getMessagesBundle(Locale locale) {
        return SUPPORTED_LOCALES.contains(locale) ? ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale) : ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, Locale.ROOT);
    }
}
