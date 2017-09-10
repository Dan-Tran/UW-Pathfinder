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
import cli.WeightedPath;
import graphStructures.GraphEdge;
import graphStructures.GraphNode;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestWeightedPath {
    @Test
    public void TestConstructor() {
        GraphNode<CoordinatePoint, Double> testNode = new GraphNode<>(new CoordinatePoint(1.0, 2.0));
        WeightedPath test = new WeightedPath(testNode);
        assertTrue(test.start.equals(testNode));
        assertTrue(test.dest.equals(testNode));
        assertTrue(test.weight == 0.0);
        assertTrue(test.path.isEmpty());
    }

    @Test
    public void TestPush() {
        GraphNode<CoordinatePoint, Double> testNode = new GraphNode<>(new CoordinatePoint(1.0, 2.0));
        GraphNode<CoordinatePoint, Double> testChild = new GraphNode<>(new CoordinatePoint(1.0, 7.0));
        GraphEdge<CoordinatePoint, Double> testEdge = new GraphEdge<>(testChild, 5.0);
        WeightedPath test = new WeightedPath(testNode);
        assertTrue(test.weight == 0.0);
        test.push(testEdge);
        assertTrue(test.weight == 5.0);
        assertTrue(test.path.size() == 1);
        assertTrue(test.dest.equals(testChild));
        assertTrue(test.path.get(0).equals(testEdge));
    }

    @Test
    public void TestGetPath() {
        GraphNode<CoordinatePoint, Double> testNode = new GraphNode<>(new CoordinatePoint(1.0, 2.0));
        GraphNode<CoordinatePoint, Double> testChild = new GraphNode<>(new CoordinatePoint(1.0, 7.0));
        GraphEdge<CoordinatePoint, Double> testEdge = new GraphEdge<>(testChild, 5.0);
        WeightedPath test = new WeightedPath(testNode);
        test.push(testEdge);
        Map<CoordinatePoint, Double> path = test.getPath();
        Map<CoordinatePoint, Double> testPath = new LinkedHashMap<>();
        testPath.put(new CoordinatePoint(1.0, 2.0), 0.0);
        testPath.put(new CoordinatePoint(1.0, 7.0), 5.0);
        assertEquals(path.size(), testPath.size());
        for (CoordinatePoint point : path.keySet()) {
            assertTrue(testPath.keySet().contains(point));
            assertTrue(path.get(point).equals(testPath.get(point)));
        }
    }

    @Test
    public void TestCompareTo() {
        GraphNode<CoordinatePoint, Double> testNode = new GraphNode<>(new CoordinatePoint(1.0, 2.0));
        WeightedPath test = new WeightedPath(testNode);
        WeightedPath test2 = new WeightedPath(testNode);
        test.weight = 0.0;
        test2.weight = 1.0;
        assertTrue(test.compareTo(test2) < 0);
        assertTrue(test2.compareTo(test) > 0);
        assertTrue(test.compareTo(test) == 0);
    }
}
