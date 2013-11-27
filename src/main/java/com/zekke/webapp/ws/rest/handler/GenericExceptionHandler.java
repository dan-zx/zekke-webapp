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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.util.RestUtils;

/**
 * Transforms any throwable thrown by the application into a json format.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOG = LoggerFactory.getLogger(GenericExceptionHandler.class);

    /**
     * Map any throwable to a json response responding with
     * {@link javax.ws.rs.core.Response.Status#INTERNAL_SERVER_ERROR}.
     *
     * @param ex the throwable to map to a response.
     * @return a response mapped from the supplied throwable.
     */
    @Override
    public Response toResponse(Throwable ex) {
        LOG.error("Unknown failure", ex);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(RestUtils.toJson(ex))
                .build();
    }
}