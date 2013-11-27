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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jodah.typetools.TypeResolver;

/**
 * ZekkeExceptionBuilder is a helper that constructs easily any
 * {@code ZekkeException} using {@code Messages} class to get resource messages.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 * @param <T> the {@code ZekkeException} type.
 */
public abstract class ZekkeExceptionBuilder<T extends ZekkeException> {

    private String messageKey;
    private Throwable cause;
    private final Class<T> type;
    private final List<Object> formatArgs;

    /**
     * Default constructor. Initializes the generic type and the format aguments
     * list.
     */
    @SuppressWarnings("unchecked")
    protected ZekkeExceptionBuilder() {
        type = (Class<T>) TypeResolver.resolveRawArgument(ZekkeExceptionBuilder.class, getClass());
        formatArgs = new ArrayList<>(0);
    }

    /**
     * Sets the search key to an associated message located in
     * com/zekke/webapp/messages.properties for the new exception.
     *
     * @param key the key for the desired message.
     * @return this object.
     */
    public ZekkeExceptionBuilder<T> setMessageResource(String key) {
        this.messageKey = key;
        return this;
    }

    /**
     * Sets a cause for the new exception.
     *
     * @param cause the cause.
     * @return this object.
     */
    public ZekkeExceptionBuilder<T> setCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    /**
     * Adds a format argument for the message of the new exception.
     *
     * @param formatArg an object
     * @return this object.
     */
    public ZekkeExceptionBuilder<T> addResourceFormatArg(Object formatArg) {
        formatArgs.add(formatArg);
        return this;
    }

    /**
     * Builds a new exception of the inherited type.
     *
     * @return a new {@code ZekkeException}.
     * @throws RuntimeException if something goes wrong.
     */
    public T build() {
        try {
            if (cause != null && !formatArgs.isEmpty()) {
                return type.getConstructor(String.class, Throwable.class, Object[].class).newInstance(messageKey, cause, formatArgs.toArray());
            } else if (cause != null && formatArgs.isEmpty()) {
                return type.getConstructor(String.class, Throwable.class).newInstance(messageKey, cause);
            } else if (cause == null && !formatArgs.isEmpty()) {
                return type.getConstructor(String.class, Object[].class).newInstance(messageKey, formatArgs.toArray());
            } else {
                return type.getConstructor(String.class).newInstance(messageKey);
            }
        } catch (NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }
}