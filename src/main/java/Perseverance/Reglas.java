package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Reglas extends JFrame {

    private JTextArea area;
    private String reglas;
    private Font fuente;

    public Reglas(){
        setLayout(new GridBagLayout());
        setTitle("Reglas");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());

        area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setSize(800,600);
        registrarFuente();
        area.setFont(fuente);
        area.setBackground(new Color(255,255,255));
        add(area);
        leerArchivo();

    }

    public void crearVentana(){
        this.setSize( 1024,720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }
    public void leerArchivo() {
        File archivo = new File("reglas.txt");
        reglas="";
        try {
            Scanner scan = new Scanner(archivo);
            while (scan.hasNextLine()){
                area.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registrarFuente(){
        try {
            //create the font to use. Specify the size!
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font\\TitilliumWeb.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(fuente);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
