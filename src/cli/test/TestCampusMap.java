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
import cli.CampusMap;
import cli.CoordinatePoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestCampusMap {
    @Test
    public void TestFindPath() {
        CampusMap map = new CampusMap("src/cli/data/test.dat");
        Map<CoordinatePoint, Double> path = map.findPath(new Building("0", "00", 0, 0), new Building("2", "21", 2, 1));
        Map<CoordinatePoint, Double> checker = new LinkedHashMap<>();
        checker.put(new CoordinatePoint(0, 0), 0.0);
        checker.put(new CoordinatePoint(0, 1), 1.0);
        checker.put(new CoordinatePoint(1.0, 1.0), 1.0);
        checker.put(new CoordinatePoint(2.0, 1.0), 1.0);
        assertEquals(path.size(), checker.size());
        for (CoordinatePoint point : path.keySet()) {
            assertTrue(checker.keySet().contains(point));
            assertTrue(path.get(point).equals(checker.get(point)));
        }
    }
}
