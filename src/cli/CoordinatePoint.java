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
 * This class represents a coordinate point.
 * 
 * @author Dan Tran
 *
 */
public class CoordinatePoint implements Comparable<CoordinatePoint> {
    // This class represents a coordinate point.
    
    // Representation Invariant: x and y are valid real numbers.
    
    // Abstraction Function: The fields x and y represent the coordinate point (x, y).

    private final double x;
    private final double y;

    /**
     * Constructs a new CoordinatePoint objects with the given coordinates x and y.
     * @param x The x coordinate for this CoordinatePoint
     * @param y The y coordinate for this CoordinatePoint
     */
    public CoordinatePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of this CoordinatePoint
     * @return The x coordinate of this CoordinatePoint
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this CoordinatePoint
     * @return The y coordinate of this CoordinatePoint
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the distance between this and the other given CoordinatePoint.
     * @param o The other CoordinatePoint to find the distance from this
     * @requires o != null
     * @return The Distance between this and the other given CoordinatePoint.
     */
    public double distance(CoordinatePoint o) {
        return Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2));
    }

    /**
     * Returns true if this is equal to the given Object.  An object is equal to this
     * if and only if the object is an instance of a CoordinatePoint with its x field
     * equal to this x field and its y field equal to this y field.
     * @param o The Object to determine if it is equal to this.
     * @return True if and only if this is equal to the given object.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CoordinatePoint)) {
            return false;
        }
        CoordinatePoint other = (CoordinatePoint) o;
        return x == other.getX() && y == other.getY();
    }

    /**
     * Returns this object's hashcode.
     * @return this object's hashcode.
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    /**
     * Compares this CoordinatePoint to another CoordinatePoint for order.
     * Returns a negative integer, zero, or a positive integer when this
     * is less than, equal to, or greater than the given Coordinate point.
     * A Coordinate is "less than" another by first comparing its x coodinate point,
     * and then the y coordinate point.
     * @param o The CoordinatePoint to be compared to
     * @requires o != null
     * @return A negative integer, zero, or positive integer when this is
     * less than, equal to, or greater than o respectively.
     */
    @Override
    public int compareTo(CoordinatePoint o) {
        if (x != o.getX()) {
            return Double.compare(x, o.getX());
        }
        return Double.compare(y, o.getY());
    }
}
