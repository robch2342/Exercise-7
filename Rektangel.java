package se.molk;

import java.awt.*;

/**
 * Created by henke on 04/11/14.
 */
public class Rektangel extends Shape {

    public Rektangel(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
