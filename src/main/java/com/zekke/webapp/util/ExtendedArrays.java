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

/**
 * Provides utility methods for arryas.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public final class ExtendedArrays {

    /**
     * Default constructor. Do NOT try to initialize this class, it is suppose
     * to be an static utility.
     */
    private ExtendedArrays() {
        throw new IllegalAccessError("This class cannot be instantiated nor extended");
    }

    /**
     * Checks that the given array is either null or empty.
     *
     * @param <T> any type.
     * @param a the array to check
     * @return {@code true} if the given array is either null or empty;
     * otherwise {@code false}.
     */
    public static <T> boolean isNullOrEmpty(T[] a) {
        return a == null || a.length == 0;
    }
}