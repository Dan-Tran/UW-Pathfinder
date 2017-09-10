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

import graphStructures.GraphEdge;
import graphStructures.GraphNode;

public class GraphNodeTest {

    @Test
    public void TestConstructor() {
        assertTrue("Test1: ".equals(new GraphNode<String, String>("Test1").toString()));
        assertTrue("".equals(new GraphNode<String, String>("Test2").childrenToString()));
    }

    @Test
    public void TestAddEdge() {
        GraphNode<String, String> test3 = new GraphNode<String, String>("Test3");
        GraphNode<String, String> test4 = new GraphNode<String, String>("Test4");
        GraphNode<String, String> test5 = new GraphNode<String, String>("Test5");
        test3.addEdge(new GraphEdge<String, String>(test4, "edge1"));
        assertTrue("Test4(edge1)".equals(test3.childrenToString()));
        assertFalse("Test3(edge1)".equals(test4.childrenToString()));
        test4.addEdge(new GraphEdge<String, String>(test3, "edge2"));
        test4.addEdge(new GraphEdge<String, String>(test5, "edge3"));
        test3.addEdge(new GraphEdge<String, String>(test4, "edge3a"));
        assertTrue("Test3(edge2) Test5(edge3)".equals(test4.childrenToString()));
        assertTrue("Test4(edge1) Test4(edge3a)".equals(test3.childrenToString()));
    }

    @Test
    public void TestIsParent() {
        GraphNode<String, String> test6 = new GraphNode<String, String>("Test6");
        GraphNode<String, String> test7 = new GraphNode<String, String>("Test7");
        GraphNode<String, String> test8 = new GraphNode<String, String>("Test8");
        assertFalse(test6.isParent(test7));
        assertFalse(test7.isParent(test8));
        assertFalse(test8.isParent(test6));
        test6.addEdge(new GraphEdge<String, String>(test7, "edge4"));
        assertTrue(test6.isParent(test7));
        assertFalse(test7.isParent(test6));
        test6.addEdge(new GraphEdge<String, String>(test8, "edge5"));
        assertTrue(test6.isParent(test8));
        assertFalse(test8.isParent(test6));
        assertFalse(test8.isParent(test7));
        assertFalse(test7.isParent(test8));
    }

    @Test
    public void TestDeleteEdge() {
        GraphNode<String, String> test9 = new GraphNode<String, String>("Test9");
        GraphNode<String, String> test10 = new GraphNode<String, String>("Test10");
        GraphNode<String, String> test11 = new GraphNode<String, String>("Test11");
        GraphEdge<String, String> edge6 = new GraphEdge<String, String>(test10, "edge6");
        GraphEdge<String, String> edge7 = new GraphEdge<String, String>(test11, "edge7");
        test9.addEdge(edge6);
        test9.addEdge(edge7);
        test11.deleteEdge(edge7);
        test9.deleteEdge(edge6);
        assertTrue(test9.isParent(test11));
        assertFalse(test11.isParent(test9));
        assertFalse(test9.isParent(test10));
        assertFalse(test10.isParent(test9));
    }

    @Test
    public void TestHasChildren() {
        GraphNode<String, String> test12 = new GraphNode<String, String>("Test12");
        GraphNode<String, String> test13 = new GraphNode<String, String>("Test13");
        GraphNode<String, String> test14 = new GraphNode<String, String>("Test14");
        assertFalse(test12.hasChildren());
        GraphEdge<String, String> edge8 = new GraphEdge<String, String>(test13, "edge8");
        test12.addEdge(edge8);
        assertTrue(test12.hasChildren());
        assertFalse(test13.hasChildren());
        test12.addEdge(new GraphEdge<String, String>(test14, "edge9"));
        test12.deleteEdge(edge8);
        assertFalse(test13.hasChildren());
        assertTrue(test12.hasChildren());
        assertFalse(test14.hasChildren());
    }

    @Test
    public void TestNumEdges() {
        GraphNode<String, String> test15 = new GraphNode<String, String>("Test15");
        GraphNode<String, String> test16 = new GraphNode<String, String>("Test16");
        GraphNode<String, String> test17 = new GraphNode<String, String>("Test17");
        assertEquals(0, test15.numChildren());
        GraphEdge<String, String> edge10 = new GraphEdge<String, String>(test16, "edge10");
        test15.addEdge(edge10);
        assertEquals(1, test15.numChildren());
        assertEquals(0, test16.numChildren());
        test17.addEdge(new GraphEdge<String, String>(test15, "edge11"));
        test15.addEdge(new GraphEdge<String, String>(test17, "edge12"));
        assertEquals(2, test15.numChildren());
        assertEquals(1, test17.numChildren());
        test16.deleteEdge(edge10);
        assertEquals(0, test16.numChildren());
        assertEquals(2, test15.numChildren());
        assertEquals(1, test17.numChildren());
    }

    @Test
    public void TestGetIdentifier() {
        GraphNode<String, String> test18 = new GraphNode<String, String>("Test18");
        assertEquals("Test18", test18.getIdentifier());
    }
}
