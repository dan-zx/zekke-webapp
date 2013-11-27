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
package com.zekke.webapp.ws.rest;

import java.io.Serializable;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseRestWebService class serves as a base class that contains several common
 * methods for RESTful web services.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public abstract class BaseRestWebService implements Serializable {

    protected static final String VER_1_URL_PREFIX = "v1/";

    private static final long serialVersionUID = -5505817900908270945L;
    private static final Logger LOG = LoggerFactory.getLogger(BaseRestWebService.class);

    protected HttpHeaders headers;

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