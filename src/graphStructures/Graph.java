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

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents a mutable directed graph, consisting of nodes connected via edges.
 *
 * @author Dan Tran
 *
 */
public class Graph <N extends Comparable<N>, E extends Comparable<E>> {
    // This class represents a mutable directed graph, consisting of nodes connected via edges.

    // Representation Invariant: nodes != null, there are no duplicates in nodes, and the key in the
    // map nodes is equal to the identifier in its associated value.

    // Abstraction Function: The List nodes contain the nodes that are in the graph. The edges
    // are represented by the data of the nodes themselves, with each nodes containing a map of GraphNodes,
    // representing its children, associated with the attached edge label.

    private static final boolean DEBUG = false;

    private final Map<N, GraphNode<N, E>> nodes;

    /**
     * Constructs an empty graph with no nodes or edges.
     * @effects Creates an empty graph with no nodes or edges.
     */
    public Graph() {
        nodes = new TreeMap<N, GraphNode<N, E>>();
        checkRep();
    }

    /**
     * Adds a new node with the given identifier into this graph.
     * @param identifier The identifier that will identify the new node.
     * @requires identifier != null and the node with the identifier is not already in this.
     * @modifies this
     * @effects A new node with the given identifier is added into this.
     */
    public void addNode (N identifier) {
        checkRep();
        if(!nodes.containsKey(identifier)) {
            nodes.put(identifier, new GraphNode<N, E>(identifier));
        }
        checkRep();
    }

    /**
     * Adds an edge between nodes identified with parent and child with the given label.
     * If one already existed, replace its label with the given label.
     * @param edgeLabel The label to be associated with the created edge.
     * @param parent Identifies one of the nodes to be connected with the edge.
     * @param child Identifies the other node to be connected with the edge.
     * @requires The nodes labeled parent and child are in the graph.
     * @modifies this
     * @effects Adds an edge between the nodes labeled parent and child with the given label.
     * If one already existed, replace its label with the given label.
     */
    public void addEdge (E edgeLabel, N parent, N child) {
        checkRep();
        GraphNode<N, E> na = nodes.get(parent);
        GraphNode<N, E> nb = nodes.get(child);
        if (na == null || nb == null) {
            throw new IllegalArgumentException();
        }
        na.addEdge(new GraphEdge<N, E>(nb, edgeLabel));
        checkRep();
    }

    /**
     * Removes the edge between nodes identified by the parent and child with the given
     * label.
     * @param edgeLabel The label of the edge to be removed.
     * @param parent The parent of the edge to be removed.
     * @param child The child of the edge to be removed.
     * @requires The edge to be in the graph.
     * @modifies this
     * @effects Removes the edge between nodes identified by the parent and child with
     * the given label.
     */
    public void deleteEdge(E edgeLabel, N parent, N child) {
        checkRep();
        GraphNode<N, E> na = nodes.get(parent);
        GraphEdge<N, E> nb = na.getChild(child);
        if (na == null || nb == null) {
            throw new IllegalArgumentException();
        }
        na.deleteEdge(nb);
    }

    /**
     * Gets an edge label from the edge between the parent and the node.
     * @param parent The parent of the edge to get the edge label.
     * @param child The child of the edge to get the edge label.
     * @return An edge label from the edge between the parent and the node.
     */
    public E getEdgeLabel(N parent, N child) {
        checkRep();
        GraphNode<N, E> na = nodes.get(parent);
        GraphEdge<N, E> nb = na.getChild(child);
        if (na == null || nb == null) {
            throw new IllegalArgumentException();
        }
        return nb.getLabel();
    }
    /**
     * Returns a string representation of the list of nodes in the graph.
     * @return A string representation of the list of nodes in the graph.
     */
    public String nodesToString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        for (GraphNode<N, E> node : nodes.values()) {
            sb.append(node.getIdentifier().toString() + " ");
        }
        checkRep();
        if (sb.length() < 1) {
            return "";
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Returns a string representation of the children of the node associated
     * with the given identifier.
     * @param identifier The node that we want the string representation of its children.
     * @requires identifier != null.
     * @return A string representation of the children of the node associated with the
     * given identifier, with the associated edge labels.
     */
    public String childrenToString(N identifier) {
        checkRep();
        return getNode(identifier).childrenToString();
    }

    /**
     * Returns a string representation of the list of edges in the graph.
     * @return A string representation of the list of edges in the graph.
     */
    @Override
    public String toString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        for (N node : nodes.keySet()) {
            sb.append(node.toString() + ": " + childrenToString(node) + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if and only if nodes labeled parent is the parent of nodes labeled child.
     * @param parent The supposed parent node.
     * @param child The supposed child node.
     * @requires Nodes labeled as parent and child are in the graph.
     * @return True if and only if the node labeled as parent is the parent node
     * of the one labeled child.
     */
    public boolean isDirectlyConnected(N parent, N child) {
        checkRep();
        GraphNode<N, E> p = getNode(parent);
        GraphNode<N, E> c = getNode(child);
        if (p == null || c == null) {
            return false;
        }
        checkRep();
        return c.isChild(p);
    }

    /**
     * Returns true if and only if nodes labeled ia and ib are connected via an edge.
     * @param ia One of the nodes to see if there is an edge between.
     * @param ib The other node to see if there is an edge between.
     * @requires Nodes labeled as ia and ib are in the graph.
     * @returns True if and only if ia and ib are directly connected.
     */
    public boolean isAdjacent(N ia, N ib) {
        checkRep();
        return isDirectlyConnected(ia, ib) || isDirectlyConnected(ib, ia);
    }

    /**
     * Returns the number of nodes in the graph.
     * @return The number of nodes in the graph.
     */
    public int numNodes() {
        checkRep();
        return nodes.size();
    }

    /**
     * Returns the number of edges in the graph.
     * @return The number of edges in the graph.
     */
    public int numEdges() {
        checkRep();
        int count = 0;
        for (GraphNode<N, E> node : nodes.values()) {
            count += node.numChildren();
        }
        return count;
    }

    /**
     *  Returns the GraphNode with the given identifier.  If none is found, returns null.
     * @param identifier The identifer to retrieve the GraphNode associated.
     * @return The GraphNode with the given identifier.  If none is found, returns null.
     */

    public GraphNode<N, E> getNode(N identifier) {
        return nodes.get(identifier);
    }
    
    /**
     * Returns the set of children associated with the node with the given identifier.
     * @param identifier The node to get the its children.
     * @return The set of children associated with the node with the given identifier.
     */
    public Set<GraphEdge<N, E>> getChildren(N identifier) {
        return getNode(identifier).getChildren();
    }

    // Checks the object on the representation invariant to ensure correctness.
    private void checkRep() {
        assert nodes != null : "[Graph] nodes is null.";
        if (DEBUG) {
            for (N id : nodes.keySet()) {
                assert id.equals(nodes.get(id).getIdentifier());
            }
        }
    }
}
