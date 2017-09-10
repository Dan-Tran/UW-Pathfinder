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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Map;

import javax.swing.JPanel;

import cli.CoordinatePoint;

public class MapPanel extends JPanel {
    private Image pic;
    private Map<CoordinatePoint, Double> path;
    
    public MapPanel(Image pic) {
        this.pic = pic;
        this.path = null;
    }

    public void setPath(Map<CoordinatePoint, Double> path) {
        this.path = path;
    }

    public void clearPath() {
        this.path = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (path == null) {
            g2.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), 0, 0, pic.getWidth(this), pic.getHeight(this), this);
        } else {
            int xmin = pic.getWidth(this);
            int ymin = pic.getHeight(this);
            int xmax = 0;
            int ymax = 0;
            for (CoordinatePoint curr : path.keySet()) {
                xmin = Math.min(xmin, (int) curr.getX());
                ymin = Math.min(ymin, (int) curr.getY());
                xmax = Math.max(xmax, (int) curr.getX());
                ymax = Math.max(ymax, (int) curr.getY());
            }

            xmin -= this.getWidth() / 25;
            ymin -= this.getWidth() / 25;
            xmax += this.getWidth() / 25;
            ymax += this.getWidth() / 25;
            g2.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), xmin, ymin, xmax, ymax, this);
            
            g2.setColor(Color.RED);
            CoordinatePoint prev = null;
            for (CoordinatePoint curr : path.keySet()) {
                if (prev == null) {
                    g2.fillOval((int) ((curr.getX() - xmin) * this.getWidth() / (xmax - xmin) - this.getWidth() / 50),
                                (int) ((curr.getY() - ymin) * this.getHeight() / (ymax - ymin) - this.getWidth() / 50),
                                this.getWidth() / 25, this.getWidth() / 25);
                    prev = curr;
                } else {
                    g2.drawLine((int) ((prev.getX() - xmin) * this.getWidth() / (xmax - xmin)),
                                (int) ((prev.getY() - ymin) * this.getHeight() / (ymax - ymin)),
                                (int) ((curr.getX() - xmin) * this.getWidth() / (xmax - xmin)),
                                (int) ((curr.getY() - ymin) * this.getHeight() / (ymax - ymin)));
                    prev = curr;
                }
            }
            g2.fillOval((int) ((prev.getX() - xmin) * this.getWidth() / (xmax - xmin) - this.getWidth() / 50),
                        (int) ((prev.getY() - ymin) * this.getHeight() / (ymax - ymin) - this.getWidth() / 50),
                        this.getWidth() / 25, this.getWidth() / 25);
        }
    }
}
