package se.molk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;

public class MyCanvas extends Canvas implements ActionListener{
   LinkedList<Shape> shapes = new LinkedList<Shape>();
    ControlWindow nyForm;

    public void actionPerformed(ActionEvent e) {
        //Säg till nyForm att den ska skapa den Shape som beskrivs i dess input-fält och returnera den till oss. Lägg formen i listan shapes.
        shapes.add(nyForm.getShapeObj());
        //Måla om denna canvas, så att den nya formen ritas ut på en gång.
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise 7: The Paintening");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MyCanvas canvas = new MyCanvas();
        canvas.setSize(800, 800);
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public MyCanvas() {
        //Skapa fönstret med kontroller för att lägga till en ny form.
        nyForm = new ControlWindow(this);
        nyForm.setVisible(true);
        nyForm.setAlwaysOnTop(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : shapes) {
            shape.paint(g);
        }
    }
}
