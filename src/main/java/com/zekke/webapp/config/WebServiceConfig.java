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
package com.zekke.webapp.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.zekke.webapp.ws.rest.GeocoderRestWebService;
import com.zekke.webapp.ws.rest.RouteFinderRestWebService;
import com.zekke.webapp.ws.rest.handler.ConstraintViolationExceptionHandler;
import com.zekke.webapp.ws.rest.handler.GenericExceptionHandler;
import com.zekke.webapp.ws.rest.handler.ZekkeExceptionHandler;

/**
 * Jersey web service configuration class. Creates web services and providers.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@ApplicationPath("/api/*")
public class WebServiceConfig extends ResourceConfig {

    /**
     * Registers Jersey application components.
     */
    public WebServiceConfig() {
        // Web services implementations
        register(GeocoderRestWebService.class);
        register(RouteFinderRestWebService.class);

        // Exception handlers
        register(ConstraintViolationExceptionHandler.class);
        register(GenericExceptionHandler.class);
        register(ZekkeExceptionHandler.class);

        // Json mapping support
        register(JacksonFeature.class);

        // Bean validation support
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}