/**
 * Copyright 2017 Dan Tran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cli;

/**
 * This immutable class represents a building in the UW campus.
 * 
 * @author Dan Tran
 *
 */
public class Building {
    // This immutable class represents a building in the UW campus.
    
    // Representation Invariant: shortName, longName, and coordinates are all valid and not null.
    
    // Abstraction Function: The Building's full name is the longName, its abbreviation the shortName,
    // and location is described the coordinates.

    private final String shortName;
    private final String longName;
    private final CoordinatePoint coordinates;

    /**
     * Constructs a new building described with the given shortName, longName, and coordinate points.
     * @param shortName The abbreviation of the longName
     * @param longName The long name of the building.
     * @param coordinates The location of the building.
     * @requires shortName, longName, and coordinate != null.
     * @effects Creates a new Building object.
     */
    public Building(String shortName, String longName, CoordinatePoint coordinates) {
        this.shortName = shortName;
        this.longName = longName;
        this.coordinates = coordinates;
    }

    /**
     * Constructs a new building described with the given shortName, longName, and coordinate points.
     * @param shortName The abbreviation of the longName
     * @param longName The long name of the building.
     * @param x The x coordinate of the building.
     * @param y The y coodrinate of the building.
     * @requires shortName and longName != null.
     * @effects Creats a new Building object.
     */
    public Building(String shortName, String longName, double x, double y) {
        this(shortName, longName, new CoordinatePoint(x, y));
    }

    /**
     * Returns the shortName of the building.
     * @return The shortName of the building.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns the longName of the building.
     * @return The longName of the building.
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Returns the coordinates of the building.
     * @return The coordinates of the building.
     */
    public CoordinatePoint getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the x coordinate of the building.
     * @return The x coordinate of the building.
     */
    public double getX() {
        return coordinates.getX();
    }

    /**
     * Returns the y coordinate of the building.
     * @return The y coordinate of the building.
     */
    public double getY() {
        return coordinates.getY();
    }
}
