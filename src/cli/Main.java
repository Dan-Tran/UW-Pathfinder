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

import java.util.Map;
import java.util.Scanner;

import cli.DataParser.MalformedDataException;

public class Main {
    public static void main(String[] args) {
        try {
            CampusMap campusMap = new CampusMap("src/cli/data/campus_paths.dat");
            Map<String, Building> buildings = DataParser.parseBuildings("src/cli/data/campus_buildings.dat");
            Scanner input = new Scanner(System.in);
            
            printMenu();

            System.out.print("Enter an option ('m' to see the menu): ");
            while(true) {
                String option = input.nextLine();
                if (option.startsWith("#") || option.isEmpty()) {
                    System.out.println(option);
                    continue;
                }

                if (option.equals("b")) {
                    displayBuildings(buildings);
                } else if (option.equals("r")) {
                    System.out.print("Abbreviated name of starting building: ");
                    String sstart = input.nextLine();
                    Building start = buildings.get(sstart);

                    System.out.print("Abbreviated name of ending building: ");
                    String sdest = input.nextLine();
                    Building dest = buildings.get(sdest);

                    if (start == null || dest == null) {
                        printUnknown(start, sstart, dest, sdest);
                    } else {
                        System.out.println("Path from " + start.getLongName()
                                           + " to " + dest.getLongName() + ":");
                        printPath(campusMap.findPath(start, dest));
                    }
                } else if (option.equals("q")) {
                    input.close();
                    return;
                } else if (option.equals("m")) {
                    printMenu();
                } else {
                    System.out.println("Unknown option");
                    System.out.println();
                }
                System.out.print("Enter an option ('m' to see the menu): ");
            }
        } catch (MalformedDataException e) {
            System.err.println("Error: Invalid filename");
        }
    }

    private static void printUnknown(Building start, String sstart, Building dest, String sdest) {
        if (start == null) {
            System.out.println("Unknown building: " + sstart);
        }
        if (dest == null) {
            System.out.println("Unknown building: " + sdest);
        }
        System.out.println();
    }

    public static void displayBuildings(Map<String, Building> buildings) {
        System.out.println("Buildings:");
        for (String abbr : buildings.keySet()) {
            System.out.println("\t" + abbr + ": " + buildings.get(abbr).getLongName());
        }
        System.out.println();
    }
    
    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("\tr to find a route");
        System.out.println("\tb to see a list of all buildings");
        System.out.println("\tq to quit");
        System.out.println();
    }
    
    public static void printPath(Map<CoordinatePoint, Double> path) {
        double total = 0;
        CoordinatePoint prev = null;
        for (CoordinatePoint point : path.keySet()) {
            if (prev == null) {
                prev = point;
            } else {
                String cardinal = getCardinal(point.getX() - prev.getX(), prev.getY() - point.getY());
                prev = point;
                total += path.get(point);
                System.out.println(String.format("\tWalk %.0f feet %s to (%.0f, %.0f)", path.get(point), cardinal, point.getX(), point.getY()));
            }
        }
        System.out.println(String.format("Total distance: %.0f feet", total));
        System.out.println();
    }
    
    private static String getCardinal(double x, double y) {
        double theta = Math.atan2(y, x);
        if (theta < 7.0 * Math.PI / 8.0 && theta > 5.0 * Math.PI / 8.0) {
            return "NW";
        } else if (theta <= 5.0 * Math.PI / 8.0 && theta >= 3.0 * Math.PI / 8.0) {
            return "N";
        } else if (theta < 3.0 * Math.PI / 8.0 && theta > Math.PI / 8.0) {
            return "NE";
        } else if (theta <= Math.PI / 8.0 && theta >= -1.0 * Math.PI / 8.0) {
            return "E";
        } else if (theta < -1.0 * Math.PI / 8.0 && theta > -3.0 * Math.PI / 8.0) {
            return "SE";
        } else if (theta <= -3.0 * Math.PI / 8.0 && theta >= -5.0 * Math.PI / 8.0) {
            return "S";
        } else if (theta < -5.0 * Math.PI / 8.0 && theta > -7.0 * Math.PI / 8.0) {
            return "SW";
        }
        return "W";
    }
}
