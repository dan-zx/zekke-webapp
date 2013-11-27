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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.util.ExtendedArrays;
import com.zekke.webapp.util.Strings;

/**
 * Messages an utility class that contains methods which return strings from the
 * message bundles located in com/zekke/webapp/messages.properties.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public final class Messages {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    public static final Collection<Locale> SUPPORTED_LOCALES = Collections.unmodifiableCollection(
            Arrays.asList(
                    DEFAULT_LOCALE,
                    new Locale("es"))
    );

    private static final Logger LOG = LoggerFactory.getLogger(Messages.class);
    private static final String RESOURCE_BUNDLE_BASE_NAME = "com.zekke.webapp.messages";
    private static final String MISSING_RESOURCE_KEY_FORMAT = "???%s???";

    /**
     * Default constructor. Do NOT try to initialize this class, it is suppose
     * to be an static utility.
     */
    private Messages() {
        throw new IllegalAccessError("This class cannot be instantiated nor extended");
    }

    /**
     * Gets a message for the given key from the default resource bundle.
     *
     * @param key the key for the desired message.
     * @return ???key??? if no message for the given key can be found; otherwise
     * the message for the given key.
     */
    public static String get(String key) {
        try {
            return ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, DEFAULT_LOCALE).getString(key);
        } catch (MissingResourceException ex) {
            LOG.warn("Can't find message for key: [{}]", key, ex);
            return String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        } catch (Exception ex) {
            LOG.error("An error occur while retrieving message for key: [{}]", key, ex);
            throw ex;
        }
    }

    /**
     * Gets a message for the given key from the default resource bundle
     * formatted with the given format arguments.
     *
     * @param key the key for the desired message.
     * @param formatArgs the objects to be formatted and substituted in the
     * message.
     * @return ???key??? if no message for the given key can be found; otherwise
     * the message for the given key formatted with the given format.
     */
    public static String get(String key, Object[] formatArgs) {
        String unformattedMessage = get(key);

        if (!ExtendedArrays.isNullOrEmpty(formatArgs)) {
            try {
                MessageFormat formatter = new MessageFormat(Strings.EMPTY, DEFAULT_LOCALE);
                formatter.applyPattern(unformattedMessage);
                return formatter.format(formatArgs);
            } catch (IllegalArgumentException ex) {
                LOG.warn("Can't format message: [{}] with args: {}", unformattedMessage, Arrays.deepToString(formatArgs), ex);
            }
        }

        return unformattedMessage;
    }

    /**
     * Gets a message for the given key from a resource bundle with the given
     * locale. {@link #DEFAULT_LOCALE} will be used if the given locale is not
     * in {@link #SUPPORTED_LOCALES}.
     *
     * @param key the key for the desired message.
     * @param locale the locale for which a resource bundle is desired
     * @return ???key??? if no string for the given key can be found; otherwise
     * the string for the given key.
     */
    public static String get(String key, Locale locale) {
        try {
            ResourceBundle bundle = SUPPORTED_LOCALES.contains(locale) ? ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale) : ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, DEFAULT_LOCALE);
            return bundle.getString(key);
        } catch (MissingResourceException ex) {
            LOG.warn("Can't find message for key: [{}]", key, ex);
            return String.format(MISSING_RESOURCE_KEY_FORMAT, key);
        } catch (Exception ex) {
            LOG.error("An error occur while retrieving message for key: [{}]", key, ex);
            throw ex;
        }
    }

    /**
     * Gets a message for the given key from a resource bundle with the given
     * locale formatted with the given format arguments. {@link #DEFAULT_LOCALE}
     * will be used if the given locale is not in {@link #SUPPORTED_LOCALES}.
     *
     * @param key the key for the desired message.
     * @param locale locale the locale for which a resource bundle is desired.
     * @param formatArgs the objects to be formatted and substituted in the
     * message.
     * @return ???key??? if no message for the given key can be found; otherwise
     * the message for the given key formatted with the given format arguments.
     */
    public static String get(String key, Locale locale, Object[] formatArgs) {
        String unformattedMessage = get(key, locale);

        if (!ExtendedArrays.isNullOrEmpty(formatArgs)) {
            try {
                MessageFormat formatter = SUPPORTED_LOCALES.contains(locale) ? new MessageFormat(Strings.EMPTY, locale) : new MessageFormat(Strings.EMPTY, DEFAULT_LOCALE);
                formatter.applyPattern(unformattedMessage);
                return formatter.format(formatArgs);
            } catch (IllegalArgumentException ex) {
                LOG.warn("Can't format message: [{}] with args: {}", unformattedMessage, Arrays.deepToString(formatArgs), ex);
            }
        }

        return unformattedMessage;
    }
}