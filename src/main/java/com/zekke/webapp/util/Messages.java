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
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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

    private Messages() {
        throw new AssertionError();
    }

    /**
     * Gets a message for the given key from the default resource bundle formatted with the given
     * format arguments.
     *
     * @param messageKey the key for the desired message.
     * @param messageArguments the objects to be formatted and substituted in the message.
     * @return ???key??? if no message for the given key can be found; otherwise the message for the
     * given key formatted with the given format arguments.
     */
    public static String get(String messageKey, Object... messageArguments) {
        String message;
        try {
            message = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, Locale.ROOT).getString(messageKey);
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
}
