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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Place represents a place of interest or a tansition place in a map.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
@Entity(name = "Place")
@NamedQueries({
    @NamedQuery(
            name = "Place.readList",
            query = "from Place"
    ),
    @NamedQuery(
            name = "Place.readByConnection",
            query = "select p.toPlace from Path p where p.fromPlace.id = :placeId"
    ),
    @NamedQuery(
            name = "Place.readLikeName",
            query = "from Place p where p.name like '%' || :name || '%' order by p.name"
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Place.readNamesInAreaLikeName",
            query = "select pd.name "
            + "from ("
            + "select "
            + "p.name, "
            + "(DEGREES(ACOS((SIN(RADIANS(p.latitude)) * SIN(RADIANS(:latitude)) + COS(RADIANS(p.latitude)) * COS(RADIANS(:latitude)) * COS(RADIANS(p.longitude-(:longitude))))))*60*1.1515*1.609344*1000) as distance "
            + "from place p "
            + ") as pd "
            + "where pd.name like '%' || :name || '%' and pd.distance <= :radius order by pd.distance"
    ),
    @NamedNativeQuery(
            name = "Place.readByPosition",
            query = "select "
            + "pd.id, "
            + "pd.name, "
            + "pd.latitude, "
            + "pd.longitude "
            + "from ("
            + "select p.id, "
            + "p.name, "
            + "p.latitude, "
            + "p.longitude, "
            + "(DEGREES(ACOS((SIN(RADIANS(p.latitude)) * SIN(RADIANS(:latitude)) + COS(RADIANS(p.latitude)) * COS(RADIANS(:latitude)) * COS(RADIANS(p.longitude-(:longitude))))))*60*1.1515*1.609344*1000) as distance "
            + "from place p"
            + ") as pd "
            + "where pd.distance <= 30 order by pd.distance",
            resultClass = Place.class
    )
})
@Table(name = "place", uniqueConstraints = @UniqueConstraint(name = "place_position", columnNames = {"latitude", "longitude"}))
public class Place extends SerializableIdSupport<Long> implements Serializable {

    private static final long serialVersionUID = -3178624604142829172L;

    private String name;
    private GeoPoint position;
    private List<Path> incomingPaths = new ArrayList<>(0);
    private List<Path> outgoingPaths = new ArrayList<>(0);

    // Getters & Setters ///////////////////////////////////////////////////////

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "latitude", column = @Column(name = "latitude", nullable = false, precision = 22, scale = 0)),
        @AttributeOverride(name = "longitude", column = @Column(name = "longitude", nullable = false, precision = 22, scale = 0))
    })
    public GeoPoint getPosition() {
        return position;
    }

    public void setPosition(GeoPoint position) {
        this.position = position;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toPlace")
    public List<Path> getIncomingPaths() {
        return incomingPaths;
    }

    public void setIncomingPaths(List<Path> incomingPaths) {
        this.incomingPaths = incomingPaths;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromPlace")
    public List<Path> getOutgoingPaths() {
        return outgoingPaths;
    }

    public void setOutgoingPaths(List<Path> outgoingPaths) {
        this.outgoingPaths = outgoingPaths;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Place other = (Place)obj;
        if ((getId()  == null) ? (other.getId() != null) : !getId().equals(other.getId())) return false;
        if ((name == null) ? (other.name != null) : !name.equals(other.name)) return false;
        if ((position == null) ? (other.position != null) : !position.equals(other.position)) return false;
        
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Place{id=").append(getId())
                .append(", name=").append(name).append(", position=")
                .append(position).append('}').toString();
    }
}