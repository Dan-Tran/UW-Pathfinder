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

package graphStructures;

/**
 * Represents an Edge in a Graph that will be contained in a parent node.
 * 
 * @author Dan Tran
 *
 */
public class GraphEdge<N extends Comparable<N>, E extends Comparable<E>> implements Comparable<GraphEdge<N, E>> {
    // This class represents an edge in a graph.

    // Representation Invariant: node != null and label != null

    // Abstraction Invariant: This object represents and edge to a child node.  The
    // node that contains this object in its children set is the parent.

    private GraphNode<N, E> node;
    private E label;

    /**
     * Constructs a new GraphEdge with the given node and label.
     * @param node The given node that this edge will point to.
     * @param label The given label that this edge will be associated with.
     * @requires node and label != null.
     * @effects Creates a new GraphEdge with the given node and label.
     */
    public GraphEdge(GraphNode<N, E> node, E label) {
        this.node = node;
        this.label = label;
    }

    /**
     * Returns the node associated with this edge.
     * @return The node this edge points to.
     */
    public GraphNode<N, E> getNode() {
        return node;
    }

    /**
     * Returns the edge label associated with this edge.
     * @return The edge label associated with this edge.
     */
    public E getLabel() {
        return label;
    }

    /**
     * Compares this object with the specified edge for order.
     * Returns a negative integer, zero, or a positive integer as this
     * edge is less than, equal to, or greater than the specified edge.
     * An edge is "less than" another edge based first on whether its
     * node's identifier is less than the other, then by whether its
     * label is "less than" the other.
     * @param o The edge to be compared to.
     * @requires o != null
     * @return A negative integer, zero, or positive integer when this is
     * less than, equal to, or greater than o respectively.
     */
    @Override
    public int compareTo(GraphEdge<N, E> o) {
        int comp = node.getIdentifier().compareTo(o.node.getIdentifier());
        if (comp == 0) {
            return label.compareTo(o.label);
        }
        return comp;
    }

    /**
     * Returns true if this is equal to the given o, where equals means that
     * the edge's node and label are equal.
     * @param o The object to test equality.
     * @return True if and only if o is equal to this.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GraphEdge<?, ?>)) {
            return false;
        }
        GraphEdge<?, ?> edge = (GraphEdge<?, ?>) o;
        return label.equals(edge.label) && node.equals(edge.node);
    }

    /**
     * Returns the hash code associated with this.
     * @return The hash code associated with this.
     */
    @Override
    public int hashCode() {
        return node.hashCode() ^ label.hashCode();
    }

    /**
     * Returns a string representation of this.
     * @return A string representation of this.
     */
    @Override
    public String toString() {
        return node.getIdentifier().toString() + "(" + label + ")";
    }
}
