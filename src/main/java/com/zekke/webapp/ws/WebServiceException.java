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
package com.zekke.webapp.ws;

import com.zekke.webapp.ZekkeException;
import com.zekke.webapp.ZekkeExceptionBuilder;

/**
 * WebServiceException class is subclass of ZekkeException. It is used for web
 * service exceptions.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public class WebServiceException extends ZekkeException {

    private static final long serialVersionUID = 2268558181499474385L;

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties.
     *
     * @param key the key for the desired message.
     */
    public WebServiceException(String key) {
        super(key);
    }

    /**
     * Constructs a new exception with the specified key associated to a message
     * in com/zekke/webapp/messages.properties formatted with the specified
     * arguments.
     *
     * @param key the key for the desired message.
     * @param formatArgs the objects to be formatted and substituted in the
     * message.
     */
    public WebServiceException(String key, Object[] formatArgs) {
        super(key, formatArgs);
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
    public WebServiceException(String key, Throwable cause) {
        super(key, cause);
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
    public WebServiceException(String key, Throwable cause, Object[] formatArgs) {
        super(key, cause, formatArgs);
    }

    /**
     * Builder is an implementation of ZekkeExceptionBuilder of ServiceException
     * type.
     */
    public static class Builder extends ZekkeExceptionBuilder<WebServiceException> { }
}