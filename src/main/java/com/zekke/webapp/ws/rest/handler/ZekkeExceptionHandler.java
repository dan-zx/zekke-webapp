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

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.Messages;
import com.zekke.webapp.ZekkeException;
import com.zekke.webapp.util.RestUtils;

/**
 * Transforms any ZekkeException thrown by the application into a json format.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Provider
public class ZekkeExceptionHandler implements ExceptionMapper<ZekkeException> {

    private static final Logger LOG = LoggerFactory.getLogger(ZekkeExceptionHandler.class);

    private HttpHeaders headers;

    /**
     * Map any ZekkeException to a json response responding with
     * {@link javax.ws.rs.core.Response.Status#INTERNAL_SERVER_ERROR}.
     *
     * @param ex the ZekkeException to map to a response.
     * @return a response mapped from the supplied ZekkeException.
     */
    @Override
    public Response toResponse(ZekkeException ex) {
        LOG.error("ZeKKe application failure", ex);
        String message = ex.getMessage(RestUtils.getClientLocale(headers, Messages.SUPPORTED_LOCALES, Messages.DEFAULT_LOCALE));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(RestUtils.toJson(ex, message))
                .build();
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