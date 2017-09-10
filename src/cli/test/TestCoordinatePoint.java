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

package cli.test;

import org.junit.Test;

import cli.CoordinatePoint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCoordinatePoint {
    @Test
    public void TestConstructor() {
        CoordinatePoint c1 = new CoordinatePoint(1, 2);
        assertTrue(c1.getX() == 1.0);
        assertTrue(c1.getY() == 2.0);
    }

    @Test
    public void TestDistance() {
        CoordinatePoint c1 = new CoordinatePoint(1, 2);
        CoordinatePoint c2 = new CoordinatePoint(1, 7);
        assertTrue(c1.distance(c2) == 5.0);
    }

    @Test
    public void TestEqual() {
        CoordinatePoint c1 = new CoordinatePoint(1, 2);
        CoordinatePoint c2 = new CoordinatePoint(1, 7);
        CoordinatePoint c3 = new CoordinatePoint(1, 2);
        
        assertTrue(c1.equals(c3));
        assertFalse(c1.equals(c2));
    }

    @Test
    public void TestCompareTo() {
        CoordinatePoint c1 = new CoordinatePoint(1, 2);
        CoordinatePoint c2 = new CoordinatePoint(1, 7);
        CoordinatePoint c3 = new CoordinatePoint(1, 2);
        CoordinatePoint c4 = new CoordinatePoint(2, 1);

        assertTrue(c1.compareTo(c3) == 0);
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
        assertTrue(c1.compareTo(c4) < 0);
        assertTrue(c4.compareTo(c1) > 0);
    }
}
