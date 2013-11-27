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
package com.zekke.webapp.ws.rest.handler;

import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.Messages;
import com.zekke.webapp.util.RestUtils;

/**
 * Transforms any ConstraintViolationException thrown by the application into a
 * json format.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);

    private HttpHeaders headers;

    /**
     * Map any ConstraintViolationException to a json response responding with
     * {@link javax.ws.rs.core.Response.Status#INTERNAL_SERVER_ERROR}.
     *
     * @param ex the ConstraintViolationException to map to a response.
     * @return a response mapped from the supplied ConstraintViolationException.
     */
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(toJson(ex))
                .build();
    }

    /**
     * Transforms any ConstraintViolationException into a json string with the
     * format {@link com.zekke.webapp.util.RestUtils#JSON_EXCEPTION_FORMAT}.
     *
     * @param ex a ConstraintViolationException
     * @return a json string.
     */
    private String toJson(ConstraintViolationException ex) {
        Locale clientLocale = RestUtils.getClientLocale(headers, Messages.SUPPORTED_LOCALES, Messages.DEFAULT_LOCALE);

        StringBuilder messageBuilder = new StringBuilder();
        StringBuilder logMessageBuilder = new StringBuilder();

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            logMessageBuilder.append(constraintViolation.getRootBeanClass().getName())
                    .append('#')
                    .append(constraintViolation.getPropertyPath().toString())
                    .append("-->[")
                    .append(constraintViolation.getInvalidValue())
                    .append("], ");
            messageBuilder.append(Messages.get(constraintViolation.getMessageTemplate(), clientLocale))
                    .append(", ");
        }

        logMessageBuilder.delete(logMessageBuilder.length() - 2, logMessageBuilder.length());
        messageBuilder.delete(messageBuilder.length() - 2, messageBuilder.length());

        LOG.error("Validation errors: {}", logMessageBuilder);
        return RestUtils.toJson(ex, messageBuilder.toString());
    }

    /**
     * Sets the request HTTP headers. It's currently used by Jersey.
     *
     * @param headers the request HTTP headers.
     */
    @Context
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}