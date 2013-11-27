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

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zekke.webapp.data.Path;
import com.zekke.webapp.data.dao.PathDao;

/**
 * Hibernate implementation of PathDAO interface.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Named("pathDao")
public class PathHibernateDao extends GenericHibernateDao<Path, Long> implements PathDao {

    private static final long serialVersionUID = 1719962973962922904L;
    private static final Logger LOG = LoggerFactory.getLogger(PathHibernateDao.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Path> readList() {
        LOG.debug("Read list of Paths");
        return getSessionFactory().getCurrentSession()
                .getNamedQuery("Path.readList")
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double readDistance(Long fromPlaceId, Long toPlaceId) {
        LOG.debug("Read distance --> fromPlaceId={}, toPlaceId={}", fromPlaceId, toPlaceId);
        return (Double) getSessionFactory().getCurrentSession()
                .getNamedQuery("Path.readDistance")
                .setLong("fromPlaceId", fromPlaceId)
                .setLong("toPlaceId", toPlaceId)
                .uniqueResult();
    }
}