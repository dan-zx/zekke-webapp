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
package com.zekke.webapp.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Data Access Object (DAO) interface. Defines the general DAO behavior.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 * @param <T> an entity type
 * @param <ID> the id type
 */
public interface GenericDao<T extends Serializable, ID extends Serializable> {

    /**
     * Retrieves an entity of type 'E' from the underlying data source by an id.
     *
     * @param id the id of type 'ID'.
     * @return an entity of type 'T'.
     */
    T readById(ID id);

    /**
     * Retrieves the full list of entities of type 'T' from the underlying data
     * source.
     *
     * @return a list of entities of type 'T'.
     */
    List<T> readList();

    /**
     * Inserts an entity of type 'T' into the underlying data source.
     *
     * @param entity an entity of type 'T'.
     */
    void save(T entity);

    /**
     * Updates an entity of type 'T' contained in the underlying data source.
     *
     * @param entity an entity of type 'T'.
     */
    void update(T entity);

    /**
     * Deletes an entity of type 'T' from in the underlying data source.
     *
     * @param entity an entity of type 'T'.
     */
    void delete(T entity);
}