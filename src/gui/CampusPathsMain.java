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

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cli.Building;
import cli.CampusMap;
import cli.DataParser;
import cli.DataParser.MalformedDataException;

public class CampusPathsMain {

    public static void main(String[] args) {
        CampusMap campusMap = new CampusMap("src/cli/data/campus_paths.dat");
        Map<String, Building> buildings = getBuildings("src/cli/data/campus_buildings.dat");
        String[] buildinglist = new String[buildings.size()];
        buildings.keySet().toArray(buildinglist);

        JFrame frame = new JFrame("Campus Paths");

        Image pic = Toolkit.getDefaultToolkit().getImage("src/cli/data/campus_map.jpg");

        MapPanel mapPanel = new MapPanel(pic);
        //mapPanel.setPreferredSize(new Dimension(935,640));
        mapPanel.revalidate();
        mapPanel.setVisible(true);
        mapPanel.repaint();

        JLabel sourceLabel = new JLabel("Starting Building:");
        JComboBox<String> source = new JComboBox<>(buildinglist);

        JPanel sourcePanel = new JPanel();
        sourcePanel.add(sourceLabel);
        sourcePanel.add(source);

        JLabel destLabel = new JLabel("Destination Building:");
        JComboBox<String> dest = new JComboBox<>(buildinglist);
        
        JPanel destPanel = new JPanel();
        destPanel.add(destLabel);
        destPanel.add(dest);

        JButton find = new JButton("Find Path");
        find.setActionCommand("find");
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Building b1 = buildings.get(source.getSelectedItem());
                Building b2 = buildings.get(dest.getSelectedItem());
                mapPanel.setPath(campusMap.findPath(b1, b2));
                mapPanel.repaint();
            }
        });

        JButton clear = new JButton("Clear");
        clear.setActionCommand("clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapPanel.clearPath();
                mapPanel.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(find);
        buttonPanel.add(clear);

        JPanel controlPanel = new JPanel();
        controlPanel.add(sourcePanel, BorderLayout.WEST);
        controlPanel.add(destPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(mapPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.pack();
        frame.setVisible(true);
    }

    private static Map<String, Building> getBuildings(String filename) {
        try {
            return DataParser.parseBuildings(filename);
        } catch (MalformedDataException e1) {
            e1.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
