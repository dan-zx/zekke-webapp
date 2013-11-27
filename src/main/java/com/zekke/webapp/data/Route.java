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

/**
 * Rout represents the optimal route where people can travel.
 *
 * @author Daniel Pedraza
 * @since version 1.0
 */
public class Route implements Serializable {

    private static final long serialVersionUID = -2680714702892382586L;

    private Double distance;
    private List<Place> path = new ArrayList<>(0);

    // Getters & Setters ///////////////////////////////////////////////////////

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Place> getPath() {
        return path;
    }

    public void setPath(List<Place> path) {
        this.path = path;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(distance, path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Route other = (Route)obj;
        if ((distance == null) ? (other.distance != null) : !distance.equals(other.distance)) return false;
        if ((path == null) ? (other.path != null) : !path.equals(other.path)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Route{distance=").append(distance)
                .append(", path=").append(path).append('}').toString();
    }
}