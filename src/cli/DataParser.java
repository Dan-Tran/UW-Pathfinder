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
import java.io.*;
import java.util.*;

import graphStructures.Graph;

/**
 * Parser utility to load the campus dataset.
 */
public class DataParser {
    /**
     * A checked exception class for bad data files
     */
    @SuppressWarnings("serial")
    public static class MalformedDataException extends Exception {
        public MalformedDataException() { }

        public MalformedDataException(String message) {
            super(message);
        }

        public MalformedDataException(Throwable cause) {
            super(cause);
        }

        public MalformedDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

  /**
   * Reads the building dataset.
   * Each line of the input file contains a abbreviation, long name, and coordinates.
   * 
   * @requires filename is a valid file path
   * @param filename the file that will be read
   * @throws MalformedDataException if the file is not well-formed:
   *          each line contains exactly two tokens separated by a tab,
   *          or else starting with a # symbol to indicate a comment line.
   */
  public static Map<String, Building> parseBuildings(String filename) throws MalformedDataException {
    Map<String, Building> buildings = new TreeMap<>();
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        // Construct the collections of characters and books, one
        // <character, book> pair at a time.
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }

            // Parse the data, stripping out quotation marks and throwing
            // an exception for malformed lines.
            inputLine = inputLine.replace("\"", "");
            String[] tokens = inputLine.split("\t");
            if (tokens.length != 4) {
                throw new MalformedDataException("Line should contain exactly one tab: "
                                                 + inputLine);
            }

            String shortName = tokens[0];
            String longName = tokens[1];
            double x = Double.parseDouble(tokens[2]);
            double y = Double.parseDouble(tokens[3]);

            buildings.put(shortName, new Building(shortName, longName, x, y));
        }
    } catch (IOException e) {
        System.err.println(e.toString());
        e.printStackTrace(System.err);
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                e.printStackTrace(System.err);
            }
        }
    }
    return buildings;
  }

  /**
   * Reads the path dataset.
   * 
   * @requires filename is a valid file path
   * @param filename the file that will be read
   * @throws MalformedDataException if the file is not well-formed:
   *          each line contains exactly two tokens separated by a tab,
   *          or else starting with a # symbol to indicate a comment line.
   */
  public static Graph<CoordinatePoint, Double> parsePaths(String filename) throws MalformedDataException {
    Graph<CoordinatePoint, Double> graph = new Graph<>();

    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        String inputLine;
        CoordinatePoint coordinatePoint = null;
        while ((inputLine = reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }
            
            if (!inputLine.startsWith("\t")) {
                String[] tokens = inputLine.split(",");
                if (tokens.length != 2) {
                    throw new MalformedDataException("Line should have two points: " + inputLine);
                }
                coordinatePoint = new CoordinatePoint(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
                if (graph.getNode(coordinatePoint) == null) {
                    graph.addNode(coordinatePoint);
                }
            } else {
                // Parse the data, stripping out quotation marks and throwing
                // an exception for malformed lines.
                inputLine.replace("\t", "");
                String[] tokens = inputLine.split(",|: ");
                if (tokens.length != 3) {
                    throw new MalformedDataException("Line should have three data points: "
                                                     + inputLine);
                }

                CoordinatePoint co = new CoordinatePoint(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
                double edge = Double.parseDouble(tokens[2]);

                if (graph.getNode(co) == null) {
                    graph.addNode(co);
                }
                
                graph.addEdge(edge, coordinatePoint, co);
            }
        }
    } catch (IOException e) {
        System.err.println(e.toString());
        e.printStackTrace(System.err);
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                e.printStackTrace(System.err);
            }
        }
    }
    return graph;
  }
}
