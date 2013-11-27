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
package com.zekke.webapp.data;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Path represents a road where people can walk on.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Entity(name = "Path")
@NamedQueries({
    @NamedQuery(
            name = "Path.readList",
            query = "from Path"
    ),
    @NamedQuery(
            name = "Path.readDistance",
            query = "select p.distance from Path p where p.fromPlace.id = :fromPlaceId and p.toPlace.id = :toPlaceId"
    )
})
@Table(name = "path", uniqueConstraints = @UniqueConstraint(name = "path_uq", columnNames = {"from_place_id", "to_place_id"}))
public class Path extends SerializableIdSupport<Long> implements Serializable {

    private static final long serialVersionUID = 7251495687147664173L;

    private Place fromPlace;
    private Place toPlace;
    private Double distance;

    // Getters & Setters ///////////////////////////////////////////////////////

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_place_id", nullable = false)
    public Place getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(Place fromPlace) {
        this.fromPlace = fromPlace;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_place_id", nullable = false)
    public Place getToPlace() {
        return toPlace;
    }

    public void setToPlace(Place toPlace) {
        this.toPlace = toPlace;
    }

    @Column(name = "distance", nullable = false, precision = 22, scale = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), fromPlace, toPlace, distance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Path other = (Path)obj;
        if ((getId()  == null) ? (other.getId() != null) : !getId().equals(other.getId())) return false;
        if ((fromPlace == null) ? (other.fromPlace != null) : !fromPlace.equals(other.fromPlace)) return false;
        if ((toPlace == null) ? (other.toPlace != null) : !toPlace.equals(other.toPlace)) return false;
        if ((distance == null) ? (other.distance != null) : !distance.equals(other.distance)) return false;
        
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Path{id=").append(getId())
                .append(", fromPlace=").append(fromPlace).append(", toPlace=")
                .append(toPlace).append(", distance=").append(distance)
                .append('}').toString();
    }
}