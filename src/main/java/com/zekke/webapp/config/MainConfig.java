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

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import com.zekke.webapp.Messages;

/**
 * Spring main configuration class. Creates all of the objects used in the whole
 * application.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Configuration
@Import({DataSourceConfig.class, BeanConfig.class})
public class MainConfig {

    private static final String CONFIG_PROPERTIES_URI = "/WEB-INF/config/app-config.properties";

    static {
        Locale.setDefault(Messages.DEFAULT_LOCALE);
    }

    /**
     * Creates a new PropertyPlaceholderConfigurer.
     *
     * @param resourceLoader any ResourceLoader.
     * @return a PropertyPlaceholderConfigurer.
     * @throws IOException if the properties file is not found in
     * {@link #CONFIG_PROPERTIES_URI}.
     */
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(ResourceLoader resourceLoader) throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setProperties(configProperties(resourceLoader).getObject());
        return ppc;
    }

    /**
     * Creates a new Properties.
     *
     * @param resourceLoader any ResourceLoader.
     * @return a Properties.
     * @throws IOException if the properties file is not found in
     * {@link #CONFIG_PROPERTIES_URI}.
     */
    @Bean
    public static PropertiesFactoryBean configProperties(ResourceLoader resourceLoader) throws IOException {
        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(resourceLoader.getResource(CONFIG_PROPERTIES_URI));
        props.afterPropertiesSet();
        return props;
    }
}