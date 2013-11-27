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

import java.util.Collection;
import java.util.Locale;

import javax.ws.rs.core.HttpHeaders;

/**
 * RestUtils class contains miscellaneous RESTful utility methods.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public final class RestUtils {

    public static final String JSON_EXCEPTION_FORMAT = "{\"error\":\"%1$s\",\"message\":\"%2$s\"}";

    /**
     * Default constructor. Do NOT try to initialize this class, it is suppose
     * to be an static utility.
     */
    private RestUtils() {
        throw new IllegalAccessError("This class cannot be instantiated or extended");
    }

    /**
     * Finds the fist supported locale in the given HTTP headers.
     *
     * @param headers HTTP headers.
     * @param supportedLocales a collection of supported locales.
     * @param defaultLocale a default locale.
     * @return a locale.
     */
    public static Locale getClientLocale(HttpHeaders headers, Collection<Locale> supportedLocales, Locale defaultLocale) {
        if (headers != null && !ExtendedCollections.isNullOrEmpty(headers.getAcceptableLanguages())) {
            for (Locale locale : headers.getAcceptableLanguages()) {
                if (supportedLocales.contains(locale)) {
                    return locale;
                }
            }
        }

        return defaultLocale;
    }

    /**
     * Transforms any throwable into a json string with the format
     * {@link #JSON_EXCEPTION_FORMAT}.
     *
     * @param <T> any throwable type.
     * @param throwable a throwable.
     * @return a json string.
     */
    public static <T extends Throwable> String toJson(T throwable) {
        return String.format(JSON_EXCEPTION_FORMAT, throwable.getClass().getSimpleName(), throwable.getMessage());
    }

    /**
     * Transforms any throwable into a json string with the format
     * {@link #JSON_EXCEPTION_FORMAT} using the given message.
     *
     * @param <T> any throwable type.
     * @param throwable a throwable.
     * @param message a message.
     * @return a json string.
     */
    public static <T extends Throwable> String toJson(T throwable, String message) {
        return String.format(JSON_EXCEPTION_FORMAT, throwable.getClass().getSimpleName(), message);
    }
}