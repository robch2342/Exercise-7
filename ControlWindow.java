package se.molk;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by john on 2014-11-05.
 */
public class ControlWindow extends JFrame implements ChangeListener{
    private JButton knapp;
    private JComboBox<String> form;
    private JColorChooser färg;
    private JSpinner x, y, w, h, hörn;
    private int[] points = {20, 20, 100, 100};

    public void stateChanged(ChangeEvent e) {
        //När värdet på en JSpinner ändras sparar den här metoden det värdet i points.
        if (e.getSource().equals(x)) {
            points[0] = ((SpinnerNumberModel) x.getModel()).getNumber().intValue();
        } else if (e.getSource().equals(y)) {
            points[1] = ((SpinnerNumberModel) y.getModel()).getNumber().intValue();
        } else if (e.getSource().equals(w)) {
            points[2] = ((SpinnerNumberModel) w.getModel()).getNumber().intValue();
        } else if (e.getSource().equals(h)) {
            points[3] = ((SpinnerNumberModel) h.getModel()).getNumber().intValue();
        }
    }

    public Shape getShapeObj() {
        //Skapar en shape från koordinaterna i points, färgen i färg formen i form, och antalet hörn i hörn.
        Shape shape = null;
        if (form.getSelectedItem().toString().equals("Triangel")){
            shape = new Triangel(färg.getColor(), points[0], points[1], points[2], points[3]);
        } else if (form.getSelectedItem().toString().equals("Rektangel")){
            shape = new Rektangel(färg.getColor(), points[0], points[1], points[2], points[3]);
        } else if (form.getSelectedItem().toString().equals("Kvadrat")){
            shape = new Kvadrat(färg.getColor(), points[0], points[1], points[2]);
        } else if (form.getSelectedItem().toString().equals("Cirkel")){
            shape = new Cirkel(färg.getColor(), points[0], points[1], points[2]);
        } else if (form.getSelectedItem().toString().equals("Oval")){
            shape = new Oval(färg.getColor(), points[0], points[1], points[2], points[3]);
        } else if (form.getSelectedItem().toString().equals("Polygon")){
            //Borde säkert använt stateChanged() för att hålla reda på antalet hörn, men det här verkar fungera.
            shape = new Polygon(färg.getColor(), points[0], points[1], points[2], ((SpinnerNumberModel) hörn.getModel()).getNumber().intValue());
        }
        return shape;
    }

    public ControlWindow(ActionListener listener) {
        super("Lägg till ny form");
        //Gör så att X-knappen i det här fönstret inte gör något.
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Ändra layout i det här fönstret till en GridBagLayout.
        this.getContentPane().setLayout(new GridBagLayout());

        //Skapa en knapp som ska lägga till den valda formen.
        knapp = new JButton("Skapa form");
        //Tala om vilket objekt knappen ska ropa på när den klickas på. I det här fallet är det huvudfönstret. När det skapas en ny MyCanvas
        //skickar den sig själv som argument till den här constructorn och sparas i variabeln listener.
        knapp.addActionListener(listener);
        //Skapa en ny drop-down-meny som ska innehålla de olika former man kan skapa.
        form = new JComboBox();
        //Skapa en färgväljare. Sätt default till svart.
        färg = new JColorChooser(Color.black);
        hörn = new JSpinner(new SpinnerNumberModel(5, 5, 10000, 1));
        //Skapa input-fält för formens x-koordinat. Tala om start-, max-, min- och öknings-värden för fältet.
        x = new JSpinner(new SpinnerNumberModel(points[0], -2000, 4000, 1));
        //Tala om vilket objekt som ska meddelas när värdet i input-fältet ändras. I det här fallet är det den ControlWindow som skapas av den här constructorn.
        x.addChangeListener(this);
        //Gör samma sak för y.
        y = new JSpinner(new SpinnerNumberModel(points[1], -2000, 4000, 1));
        y.addChangeListener(this);
        //Och för bredd...
        w = new JSpinner(new SpinnerNumberModel(points[2], 1, 2000, 1));
        w.addChangeListener(this);
        //Och sist för höjd.
        h = new JSpinner(new SpinnerNumberModel(points[3], 1, 2000, 1));
        h.addChangeListener(this);

        //Skapa labels så att användaren vet vilket input-fält som gör vad.
        JLabel labelForm = new JLabel("Form: "), labelX = new JLabel("X-koordinat: "), labelY = new JLabel("Y-koordinat: "),
                labelW = new JLabel("Bredd: "), labelH = new JLabel("Höjd: "), labelHörn = new JLabel("Antal hörn: ");

        //Lägg till alla val som ska finnas i form.
        form.addItem("Triangel");
        form.addItem("Rektangel");
        form.addItem("Kvadrat");
        form.addItem("Cirkel");
        form.addItem("Oval");
        form.addItem("Polygon");

        //Skapa en ny JPanel som allt utom färgväljaren kan ligga i. Detta är för att kunna lägga in alla andra komponenter brevid färgväljaren.
        //Färgväljaren är mycket tjockare än alla andra komponenter. Om jag lägger den på samma rad som något annat objekt kommer den raden bli
        //jättetjock, och det blir fula mellanrum mellan de andra komponenterna.
        JPanel koordinater = new JPanel();
        //Sätt ny layout för min nya JPanel.
        koordinater.setLayout(new GridBagLayout());
        //Skapa en layout manager eller vad fan det här sattyget kallas nu igen.
        GridBagConstraints l = new GridBagConstraints();
        //Hmmm, jag vet ärligt talat inte riktigt vad den här raden gör. Google tyckte att den behövdes. Oh, well.
        l.fill = GridBagConstraints.HORIZONTAL;

        //Tala om vilken rad och kolumn nästa objekt ska läggas till på.
        l.gridx = 0;
        l.gridy = 0;
        //Lägg till labeln för form-fältet i panelen. Lägg den på den plats som beskrivs i layout managern l.
        koordinater.add(labelForm, l);
        //Tala om att nästa objekt kommer hamna i andra kolumnen.
        l.gridx = 1;
        //Lägg till form i panelen, på den position som beskrivs i l.
        koordinater.add(form, l);

        //Fortsätt göra samma sak för alla andra input-fält.
        l.gridx = 0;
        l.gridy = 1;
        koordinater.add(labelX, l);
        l.gridx = 1;
        koordinater.add(x, l);

        l.gridx = 0;
        l.gridy = 2;
        koordinater.add(labelY, l);
        l.gridx = 1;
        koordinater.add(y, l);

        l.gridx = 0;
        l.gridy = 3;
        koordinater.add(labelW, l);
        l.gridx = 1;
        koordinater.add(w, l);

        l.gridx = 0;
        l.gridy = 4;
        koordinater.add(labelH, l);
        l.gridx = 1;
        koordinater.add(h, l);

        l.gridx = 0;
        l.gridy = 5;
        koordinater.add(labelHörn, l);
        l.gridx = 1;
        koordinater.add(hörn, l);



        l.gridx = 0;
        l.gridy = 0;
        //Lägg till färgväljaren i fönstret, enligt beskrivningen i l.
        this.add(färg, l);

        l.gridx = 1;
        //Lägg till panelen koordinater, som innehåller alla input-fält, i fönstret, enligt beskrivningen i l.
        this.add(koordinater, l);
        l.gridy = 2;
        //Lägg till knappen i fönstret, så den hamnar längst ner till höger. Går att göra snyggare, men orka.
        this.add(knapp, l);
        //Om jag förstått det rätt så räknar pack() ut hur stort fönstret ska vara, med tanke på hur stora komponenterna i det är, och hur de ska ligga
        //enligt layout managern.
        this.pack();
    }
}
