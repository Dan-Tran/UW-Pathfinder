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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import graphStructures.GraphEdge;
import graphStructures.GraphNode;

/**
 * This class represents a path from one node to another and its associated weight.
 *
 * @author Dan Tran
 *
 */
public class WeightedPath implements Comparable<WeightedPath> {
    // This class represents a path from one node to another and its associated weight.

    // Representation Invariant: start != null, dest != null, weight >= 0, path != null

    // Abstraction Function: The path contains the edges that are in the path.  From start,
    // for each node in the GraphEdges, the path goes through that to the dest, with the
    // associated weight.

    public GraphNode<CoordinatePoint, Double> start;
    public GraphNode<CoordinatePoint, Double> dest;
    public double weight;
    public List<GraphEdge<CoordinatePoint, Double>> path;

    /**
     * Returns the weighted path as an ordered map from the start to the dest.
     * @return The weighted path as an ordered map from the start to the dest.
     */
    public Map<CoordinatePoint, Double> getPath() {
        Map<CoordinatePoint, Double> output = new LinkedHashMap<>();
        output.put(start.getIdentifier(), 0.0);
        for (GraphEdge<CoordinatePoint, Double> p : path) {
            output.put(p.getNode().getIdentifier(), p.getLabel());
        }
        return output;
    }

    /**
     * Constructs a path that contains only the starting node start.
     * @param start The starting node for this path.
     * @requires start != null.
     * @effects Creates a path that contains only the starting node start.
     */
    public WeightedPath(GraphNode<CoordinatePoint, Double> start) {
        this.start = start;
        this.dest = start;
        this.weight = 0;
        this.path = new ArrayList<>();
    }

    /**
     * Adds the graph edge to the path, updating the destination to the node in the edge.
     * @param addition The graph edge to be added.
     * @requires start != null.
     * @modifies this
     * @effects Increases weight by the label of the edge, add the edge to the path, and
     * update the dest.
     */
    public void push(GraphEdge<CoordinatePoint, Double> addition) {
        dest = addition.getNode();
        weight += addition.getLabel();
        path.add(addition);
    }

    /**
     * Compares this path with the specified path for order.
     * Returns a negative integer, zero, or a positive integer as this
     * path is less than, equal to, or greater than the specified path.
     * An path is "less than" another path based on their weight.
     * @param o The path to be compared to.
     * @requires o != null
     * @return A negative integer, zero, or positive integer when this is
     * less than, equal to, or greater than o respectively.
     */
    @Override
    public int compareTo(WeightedPath o) {
        if (weight < o.weight) {
            return -1;
        } else if (weight > o.weight) {
            return 1;
        }
        return 0;
    }
    
}
