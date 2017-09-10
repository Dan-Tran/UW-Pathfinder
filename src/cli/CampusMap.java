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

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import cli.DataParser.MalformedDataException;
import graphStructures.Graph;
import graphStructures.GraphEdge;
import graphStructures.GraphNode;

/**
 * This class represents a map of the campus and provides methods to find a path between buildings.
 * 
 * @author Dan Tran
 *
 */
public class CampusMap {
    // This class represents a map of the campus and provides methods to find a path between buildings.
    
    // Representation Invariant:  Graph is non null and is a balid graph.
    
    // Abstraction Function:  The nodes of the graph represents location points and edges are traversable paths
    // between the location points.

    private final Graph<CoordinatePoint, Double> graph;

    /**
     * Constructs a new CampusMap by parsing the datafile named by the given string.
     * @param datafile The name of the file that contains the location data.
     * @requires datafile != null and is a valid filename.
     * @effects Constructs a new CampusMap.
     */
    public CampusMap(String datafile) {
        try {
            graph = DataParser.parsePaths(datafile);
        } catch (MalformedDataException e) {
            System.err.println("Error: Malformed data file: " + datafile);
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns a map representing the shortest path from b1 to b2 and the distance between.
     * The path goes from the starting CoordinatePoint to the next CoordinatePoint,
     * with the associated Double value the distance to get to that point.
     * @param b1 The starting building.
     * @param b2 The destination building.
     * @return A map representing the shortest path from b1 to b2 and the distance between.
     */
    public Map<CoordinatePoint, Double> findPath(Building b1, Building b2) {
        return Dijkstra(b1.getCoordinates(), b2.getCoordinates()).getPath();
    }

    // Returns a WeightedPath representing the shortest path from sstart to sdest as described
    // using Dijkstra's algorithm.
    // param sstart The starting coordinate point.
    // param sdest The destination coordinate point.
    // return A WeightedPath representing the shortest path from sstart to sdest.
    private WeightedPath Dijkstra(CoordinatePoint sstart, CoordinatePoint sdest) {
        GraphNode<CoordinatePoint, Double> start = graph.getNode(sstart);
        GraphNode<CoordinatePoint, Double> dest = graph.getNode(sdest);
        PriorityQueue<WeightedPath> active = new PriorityQueue<>();
        Set<GraphNode<CoordinatePoint, Double>> finished = new HashSet<>();
        
        active.add(new WeightedPath(start));
        
        while (!active.isEmpty()) {
            WeightedPath minPath = active.remove();
            GraphNode<CoordinatePoint, Double> minDest = minPath.dest;
            
            if (minDest.equals(dest)) {
                return minPath;
            }
            
            if (finished.contains(minDest)) {
                continue;
            }
            
            for (GraphEdge<CoordinatePoint, Double> edge : minDest.getChildren()) {
                if (!finished.contains(edge.getNode())) {
                    WeightedPath newPath = new WeightedPath(minPath.start);
                    for (GraphEdge<CoordinatePoint, Double> oEdge : minPath.path) {
                        newPath.push(oEdge);
                    }
                    newPath.push(edge);
                    active.add(newPath);
                }
            }
            
            finished.add(minDest);
        }
        return null;
    }
}
