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
import java.util.Map;

/**
 * Provides utility methods for collections.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public final class ExtendedCollections {

    /**
     * Default constructor. Do NOT try to initialize this class, it is suppose
     * to be an static utility.
     */
    private ExtendedCollections() {
        throw new IllegalAccessError("This class cannot be instantiated nor extended");
    }

    /**
     * Checks that the given collection is either null or empty.
     *
     * @param collection the collection to check.
     * @return {@code true} if the given collection is either null or empty;
     * otherwise {@code false}.
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Checks that the given map is either null or empty.
     *
     * @param map the map to check.
     * @return {@code true} if the map collection is either null or empty;
     * otherwise {@code false}.
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}