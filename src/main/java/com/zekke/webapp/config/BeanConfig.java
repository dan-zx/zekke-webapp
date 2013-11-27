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

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zekke.webapp.data.Path;
import com.zekke.webapp.data.Place;

/**
 * Spring beans configuration class. Creates DAO's, services and web services
 * and it's dependencies used in the whole application.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.zekke.webapp.data.dao.impl",
        "com.zekke.webapp.service.impl",
        "com.zekke.webapp.ws.rest"
    },
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Named.class))
public class BeanConfig {

    @Inject @Named("configProperties") private Properties configProperties;
    @Inject                            private DataSource dataSource;

    /**
     * Creates a new Hiberante SessionFactory.
     *
     * @param resourceLoader any ResourceLoader.
     * @return a Hiberante SessionFactory.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(ResourceLoader resourceLoader) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setAnnotatedClasses(new Class<?>[]{Place.class, Path.class});
        factoryBean.setHibernateProperties(configProperties);
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

    /**
     * Creates a new HibernateTransactionManager.
     *
     * @param resourceLoader any ResourceLoader.
     * @return a HibernateTransactionManager.
     */
    @Bean
    public HibernateTransactionManager transactionManager(ResourceLoader resourceLoader) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory(resourceLoader).getObject());
        return txManager;
    }

    /**
     * Creates a new HibernateExceptionTranslator.
     *
     * @return a HibernateExceptionTranslator.
     */
    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
}