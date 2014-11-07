package se.molk;

import java.awt.*;

/**
 * Created by john on 2014-11-05.
 */
public class Triangel extends Shape {
    public Triangel(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int[] xPoints = {x, x + width / 2, x + width};
        int[] yPoints = {y, y + height, y};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
