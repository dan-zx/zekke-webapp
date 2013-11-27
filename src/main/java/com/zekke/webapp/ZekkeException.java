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

import java.util.Locale;

import com.zekke.webapp.util.ExtendedArrays;

/**
 * ZekkeException class is the main exception found in this application
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public abstract class ZekkeException extends RuntimeException {

    private static final long serialVersionUID = 5332008697957682702L;

    private final String key;
    private Object[] formatArgs;

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties.
     *
     * @param key the key for the desired message.
     */
    public ZekkeException(String key) {
        super();
        this.key = key;
    }

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties formatted with the specified
     * arguments.
     *
     * @see Messages#get(java.lang.String)
     * @param key the key for the desired message.
     * @param formatArgs the objects to be formatted and substituted in the
     * message.
     */
    public ZekkeException(String key, Object[] formatArgs) {
        super();
        this.key = key;
        this.formatArgs = formatArgs;
    }

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties the specified cause
     *
     * @param key the key for the desired message.
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method). (A {@code null} value is permitted, and
     * indicates that the cause is nonexistent or unknown.)
     */
    public ZekkeException(String key, Throwable cause) {
        super(cause);
        this.key = key;
    }

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties formatted with the specified
     * arguments and the specified cause.
     *
     * @param key the key for the desired message.
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method). (A {@code null} value is permitted, and
     * indicates that the cause is nonexistent or unknown.)
     * @param formatArgs the objects to be formatted and substituted in the
     * message.
     */
    public ZekkeException(String key, Throwable cause, Object[] formatArgs) {
        super(cause);
        this.key = key;
        this.formatArgs = formatArgs;
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception instance (which may
     * be ???key??? if the submitted is invalid).
     */
    @Override
    public String getMessage() {
        if (ExtendedArrays.isNullOrEmpty(formatArgs)) {
            return Messages.get(key);
        }

        return Messages.get(key, formatArgs);
    }

    /**
     * Creates a localized message with the specified {@code Locale} only if the
     * that locale is supported by {@link Messages}
     *
     * @param locale a locale
     * @return the detail message string of this exception instance (which may
     * be ???key??? if the submitted is invalid).
     */
    public String getMessage(Locale locale) {
        if (ExtendedArrays.isNullOrEmpty(formatArgs)) {
            return Messages.get(key, locale);
        }

        return Messages.get(key, locale, formatArgs);
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception instance (which may
     * be ???key??? if the submitted is invalid).
     * @deprecated in favor of
     * {@link ZekkeException#getMessage(java.util.Locale)}
     */
    @Override
    @Deprecated
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }
}