package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Menu extends JFrame implements ActionListener {

    private JButton jugar;
    private JButton salir;
    private JButton reglas;
    private JButton puntajes;
    private Font fuente;

    public Menu(){
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

        jugar = new JButton("Jugar");
        add(jugar);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setearFuente(){
        try {
            this.fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font/TitilliumWeb.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearVentana(){
        Menu menu = new Menu();
        menu.setBounds(0, 0, 1280,720);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);

    }
}
