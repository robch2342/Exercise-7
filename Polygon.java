package se.molk;

import java.awt.*;

/**
 * En klass som ritar polygoner med valfritt antal hörn. Polygonen kommer ritas ut som om den vore inskriven i en cirkel med diametern width.
 * Created by robin on 2014-11-07.
 */
public class Polygon extends Shape {
    private int[] pointsX, pointsY;

    public Polygon(Color color, int x, int y, int width, int edges) {
        super(color, x, y, width, width);
        //Skapa arrays med x- och y-koordinater för polygonens alla hörn. Huzzah för trigonometri!
        pointsX = new int[edges];
        pointsY = new int[edges];
        double a = -Math.PI/2, inc = 2 * Math.PI / ((double) edges), d = ((double) width) / 2;
        for (int i = 0; i < edges; i++) {
            pointsX[i] = (int) Math.round(d * Math.cos(a)) + x + width / 2;
            pointsY[i] = (int) Math.round(d * Math.sin(a)) + y + height / 2;
            a += inc;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillPolygon(pointsX, pointsY, pointsX.length);
    }
}
