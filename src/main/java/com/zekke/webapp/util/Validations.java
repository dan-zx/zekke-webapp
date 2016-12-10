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

import java.util.Objects;

import static com.zekke.webapp.util.Messages.getMessage;
import static com.zekke.webapp.util.Strings.isBlank;

/**
 * IllegalArgumentException validation class.
 *
 * @author Daniel Pedraza-Arcega
 */
public class Validations {

    /**
     * @param expression a boolean expression.
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @throws IllegalArgumentException if {@code expression} is {@code false}
     */
    public static void require(boolean expression, String messageKey, Object... messageArguments) {
        if (!expression) throw new IllegalArgumentException(getMessage(messageKey, messageArguments));
    }

    /**
     * @param obj the object to test
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static void requireNonNull(Object obj, String messageKey, Object... messageArguments) {
        Objects.requireNonNull(obj, getMessage(messageKey, messageArguments));
    }

    /**
     * @param s the string to test
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @throws NullPointerException if {@code obj} is {@code null}
     * @throws IllegalArgumentException if {@code s} is empty.
     */
    public static void requireNonEmpty(String s, String messageKey, Object... messageArguments) {
        requireNonNull(s, messageKey, messageArguments);
        require(!s.isEmpty(), messageKey, messageArguments);
    }

    /**
     * @param s the string to test
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @throws NullPointerException if {@code obj} is {@code null}
     * @throws IllegalArgumentException if {@code s} is blank.
     */
    public static void requireNonBlank(String s, String messageKey, Object... messageArguments) {
        requireNonNull(s, messageKey, messageArguments);
        require(!isBlank(s), messageKey, messageArguments);
    }
}
