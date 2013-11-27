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

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

@Configuration
@Import({TestDataSourceConfig.class, BeanConfig.class})
public class TestConfig {

    private static final String CONFIG_PROPERTIES_URI = "classpath:config/test-config.properties";

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(ResourceLoader resourceLoader) throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setProperties(configProperties(resourceLoader).getObject());
        return ppc;
    }

    @Bean
    public static PropertiesFactoryBean configProperties(ResourceLoader resourceLoader) throws IOException {
        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(resourceLoader.getResource(CONFIG_PROPERTIES_URI));
        props.afterPropertiesSet();
        return props;
    }
}