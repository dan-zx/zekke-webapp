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
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationConfig;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import com.zekke.webapp.Messages;

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
        // Web service packages
        packages("com.zekke.webapp.ws.rest",
                 "com.zekke.webapp.ws.rest.provider");

        // JSON mapping support
        register(JacksonFeature.class);

        // Bean validation support
        register(ValidationConfigurationContextResolver.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }

    /**
     * Custom configuration of validation. This configuration defines custom:
     * <ul>
     *     <li>MessageInterpolator - changes the default validation resource
     *     bundle to {@link com.zekke.webapp.Messages#RESOURCE_BUNDLE_BASE_NAME}
     *     </li>
     * </ul>
     */
    private static class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {

        /**
         * {@inheritDoc}
         */
        @Override
        public ValidationConfig getContext(Class<?> type) {
            return new ValidationConfig()
                    .messageInterpolator(
                            // TODO: ResourceBundleMessageInterpolator always return default messages
                            new ResourceBundleMessageInterpolator(
                                    new PlatformResourceBundleLocator(Messages.RESOURCE_BUNDLE_BASE_NAME)));
        }
    }
}