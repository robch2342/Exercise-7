package se.molk;

import java.awt.*;

/**
 * Created by john on 2014-11-05.
 */
public class Oval extends Shape {

    public Oval(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, width, height);
    }
}
