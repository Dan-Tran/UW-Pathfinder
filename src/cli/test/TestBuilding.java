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

import cli.Building;
import cli.CoordinatePoint;

import static org.junit.Assert.assertTrue;

public class TestBuilding {
    @Test
    public void TestConstructor1() {
        Building b1 = new Building("t", "tt", new CoordinatePoint(1, 2));
        assertTrue(b1.getShortName().equals("t"));
        assertTrue(b1.getLongName().equals("tt"));
        assertTrue(b1.getX() == 1.0);
        assertTrue(b1.getY() == 2.0);
        assertTrue(b1.getCoordinates().equals(new CoordinatePoint(1, 2)));
    }

    @Test
    public void TestConstructor2() {
        Building b1 = new Building("t", "tt", 1, 2);
        assertTrue(b1.getShortName().equals("t"));
        assertTrue(b1.getLongName().equals("tt"));
        assertTrue(b1.getX() == 1.0);
        assertTrue(b1.getY() == 2.0);
        assertTrue(b1.getCoordinates().equals(new CoordinatePoint(1, 2)));
    }
}
