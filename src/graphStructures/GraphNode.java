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

import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a mutable node in a graph.
 * 
 * @author Dan Tran
 *
 */
public class GraphNode<N extends Comparable<N>, E extends Comparable<E>> implements Comparable<GraphNode<N, E>> {
    // This class represents a node in a graph.

    // Representation Invariant: identifier and children != null
    
    // Abstraction Function: This object represents a node in a graph with data being identifier
    // and it is the parent of the graph nodes listed in children, with edge labels paired with
    // those children.

    private final N identifier;
    private final Set<GraphEdge<N, E>> children;

    /**
     * Constructs a new GraphNode with the given identifier.
     * @param identifier The "key" or "name" that labels this given GraphNode.
     * @requires identifier != null.
     * @effects Creates a new GraphNode with the given identifier.
     */
    public GraphNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException();
        }
        this.identifier = identifier;
        this.children = new TreeSet<GraphEdge<N, E>>();
        checkRep();
    }

    /**
     * Returns true if this is a parent of the given GraphNode
     * @param n The given GraphNode to see if this is a parent of.
     * @requires n != null
     * @return true if and only if this is a parent of the given GraphNode.
     */
    public boolean isParent(GraphNode<N, E> n) {
        checkRep();
        for (GraphEdge<N, E> child : children) {
            if (child.getNode().equals(n)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this is a child of the given GraphNode
     * @param n The given GraphNode to see if this is a child of.
     * @requires n != null
     * @return true if and only if this is a child of the given GraphNode.
     */
    public boolean isChild(GraphNode<N, E> n) {
        checkRep();
        return n.isParent(this);
    }

    /**
     * Returns true if and only if this has child as one of its children associated with
     * the given edge label.
     * @param child The node to see if this is a parent of.
     * @param label The edge label to see if child is associated with in this.
     * @return True if and only if this has child as one of its children associated with
     * the given edge label.
     */
    public boolean hasEdge(GraphNode<N, E> child, E label) {
        for (GraphEdge<N, E> edge : children) {
            if (edge.getNode().equals(child) && edge.getLabel().equals(label)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an edge between this and the node in the given GraphEdge edge if it did not exist before with the label
     * in edge, with this as the parent and the node in edge as the child.
     * @param edge The GraphEdge containing the node to be this's child and its associated label.
     * @requires edge != null and edge not already in children.
     * @modifies this
     * @effects Adds an edge between this and the node in the given GraphEdge edge if it did not exist before
     * with the label in edge, with this as the parent and the node in edge as the child.
     */
    public void addEdge (GraphEdge<N, E> edge) {
        checkRep();
        children.add(edge);
        checkRep();
    }

    /**
     * Deletes the edge between this and the node in the given GraphEdge edge if it exists.
     * @param edge The GraphEdge to be deleted.
     * @requires edge != null and edge is in children.
     * @modifies this
     * @effects Deletes the edge between this and the node in the given GraphEdge edge if it exists.
     */
    public void deleteEdge (GraphEdge<N, E> edge) {
        checkRep();
        children.remove(edge);
        checkRep();
    }

    /**
     * Returns true if and only if this has any edges connecting it to other nodes.
     * @return true if and only if this has any edges connecting it to other nodes.
     */
    public boolean hasChildren() {
        checkRep();
        return !children.isEmpty();
    }

    /**
     * Returns the number of edges this has connecting it to other nodes.
     * @return The number of edges this has connecting it to other nodes.
     */
    public int numChildren() {
        checkRep();
        return children.size();
    }

    /**
     * Returns the indentifier that labels this.
     * @return The indentifier that labels this.
     */
    public N getIdentifier() {
        checkRep();
        return identifier;
    }

    /**
     * Returns the set of children of this.
     * @return The set of children of this.
     */
    public Set<GraphEdge<N, E>> getChildren() {
        return children;
    }

    /**
     * Returns the GraphEdge that contains the child that this node is a parent of.
     * @param child The child that determines the edge returned.
     * @requires An edge containing child to be in children.
     * @return The GraphEdge that contains the child that this node is a parent of.
     */
    public GraphEdge<N, E> getChild(N child) {
        for (GraphEdge<N, E> edge : children) {
            if (child.equals(edge.getNode().getIdentifier())) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Lists the nodes adjacent to this.
     * @effects Lists the nodes that have edges connecting to this.
     */
    public String childrenToString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        for (GraphEdge<N, E> edge : children) {
            sb.append(edge.getNode().getIdentifier().toString() + "(" + edge.getLabel().toString() + ") ");
        }
        checkRep();
        if (sb.length() < 1) {
            return "";
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Returns a string representation of the node.
     * @retun A string representation of the node.
     */
    @Override
    public String toString() {
        checkRep();
        return identifier.toString() + ": " + childrenToString();
    }

    /**
     * Returns true if o is equal to this where "equals" means that
     * they have the same identifier.
     * @param o The object to see if it is equal to this.
     * @return True if o is equal to this.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GraphNode<?, ?>)) {
            return false;
        }
        return identifier.equals(((GraphNode<?, ?>) o).identifier);
    }

    /**
     * Returns the hashcode associated with this.
     * @return The hashcode associated with this.
     */
    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    // Checks the object on the representation invariant to ensure correctness.
    private void checkRep() {
        assert identifier != null : "[GraphNode] Identifier is null.";
        assert children != null : "[GraphNode] ConnectedNodes is null.";
    }

    /**
     * Compares this node with the specified node for order.
     * Returns a negative integer, zero, or a positive integer as this
     * node is less than, equal to, or greater than the specified node.
     * An node is "less than" another edge node based on whether its
     * node's identifier is less than the other.
     * @param o The node to be compared to.
     * @requires o != null
     * @return A negative integer, zero, or positive integer when this is
     * less than, equal to, or greater than o respectively.
     */
    @Override
    public int compareTo(GraphNode<N, E> o) {
        return identifier.compareTo(o.identifier);
    }
}
