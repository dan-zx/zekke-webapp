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
package com.zekke.webapp.data.dao.impl;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import org.jodah.typetools.TypeResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.data.dao.GenericDao;

/**
 * Hibernate implementation of GenericDAO interface. Defines the main methods
 * and variables of any Hibernate DAO implementation.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 * @param <E> the entity type.
 * @param <ID> the id type.
 */
public abstract class GenericHibernateDao<E extends Serializable, ID extends Serializable> implements GenericDao<E, ID>, Serializable {

    private static final long serialVersionUID = -7319865481332186561L;
    private static final Logger LOG = LoggerFactory.getLogger(GenericHibernateDao.class);

    private SessionFactory sessionFactory;
    private final Class<E> entityType;

    /**
     * Default constructor. Infers the entity type
     */
    @SuppressWarnings("unchecked")
    protected GenericHibernateDao() {
        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(GenericHibernateDao.class, getClass());
        entityType = (Class<E>) typeArguments[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public E readById(ID id) {
        LOG.debug("Get {} by --> id={}", getEntityType().getSimpleName(), id);
        return (E) getSessionFactory().getCurrentSession().get(getEntityType(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(E entity) {
        LOG.debug("Save {}={}", getEntityType().getSimpleName(), entity);
        getSessionFactory().getCurrentSession().save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(E entity) {
        LOG.debug("Update {}={}", getEntityType().getSimpleName(), entity);
        getSessionFactory().getCurrentSession().update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(E entity) {
        LOG.debug("Delete {}={}", getEntityType().getSimpleName(), entity);
        getSessionFactory().getCurrentSession().delete(entity);
    }

    /**
     * Sets a Hiberante SessionFactory. It's currently used by Spring.
     *
     * @param sessionFactory a Hiberante SessionFactory.
     */
    @Inject
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets the current Hiberante SessionFactory.
     *
     * @return a Hiberante SessionFactory.
     */
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Gets the entity type used in this implementation.
     *
     * @return the entity type.
     */
    protected Class<E> getEntityType() {
        return entityType;
    }
}