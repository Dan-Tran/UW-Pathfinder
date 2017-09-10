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

package graphStructures.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import graphStructures.Graph;

public class GraphTest {

    @Test
    public void TestConstructor() {
        Graph<String, String> graph = new Graph<String, String>();
        assertEquals(0, graph.numNodes());
        assertEquals(0, graph.numEdges());
    }
    
    @Test
    public void TestAddNode() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("test1");
        assertEquals(1, graph.numNodes());
        assertEquals(0, graph.numEdges());
        assertTrue(graph.nodesToString().equals("test1"));
        graph.addNode("test2");
        assertEquals(2, graph.numNodes());
        assertTrue("test1 test2".equals(graph.nodesToString()));
        graph.addNode("test3");
        assertEquals(3, graph.numNodes());
        assertTrue("test1 test2 test3".equals(graph.nodesToString()));
    }
    
    @Test
    public void TestAddEdge() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("test4");
        graph.addNode("test5");
        graph.addNode("test6");
        graph.addEdge("edge1", "test4", "test5");
        assertEquals(1, graph.numEdges());
        assertTrue("test4: test5(edge1)\ntest5: \ntest6: \n".equals(graph.toString()));
        graph.addEdge("edge2", "test6", "test4");
        assertTrue("test4: test5(edge1)\ntest5: \ntest6: test4(edge2)\n".equals(graph.toString()));
    }
    
    @Test
    public void TestIsAdjacent() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("test7");
        graph.addNode("test8");
        graph.addNode("test9");
        assertFalse(graph.isAdjacent("test7", "test9"));
        graph.addEdge("edge3", "test7", "test8");
        graph.addEdge("edge4", "test7", "test9");
        assertTrue(graph.isAdjacent("test7", "test8"));
        assertFalse(graph.isAdjacent("test8", "test9"));
    }
    
    @Test
    public void TestIsDirectlyConnected() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("test10");
        graph.addNode("test11");
        graph.addNode("test12");
        assertFalse(graph.isDirectlyConnected("test10", "test11"));
        graph.addEdge("edge5", "test10", "test12");
        graph.addEdge("edge6", "test11", "test12");
        assertTrue(graph.isDirectlyConnected("test10", "test12"));
        assertFalse(graph.isDirectlyConnected("test12", "test10"));
        assertTrue(graph.isDirectlyConnected("test11", "test12"));
        assertFalse(graph.isDirectlyConnected("test12", "test11"));
        assertFalse(graph.isDirectlyConnected("test10", "test11"));
    }
    
}
