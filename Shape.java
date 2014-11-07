package se.molk;

import java.awt.*;

/**
 * Created by henke on 04/11/14.
 */
public abstract class Shape {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;

    public Shape(Color color, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    


    public void paint (Graphics g) {
        g.setColor(this.color);
    }
}
