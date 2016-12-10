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

/**
 * Various string utilities.
 *
 * @author Daniel Pedraza-Arcega
 */
public class Strings {

    public static final String EMPTY = "";
    public static final String BLANK_SPACE = " ";
    public static final String TAB = "\t";
    public static final String NEW_LINE = "\n";

    private Strings() {
        throw new AssertionError();
    }

    /**
     * @param s the string to check.
     * @return if the string is blank.
     */
    public static boolean isBlank(String s) {
        return s.trim().isEmpty();
    }
}
