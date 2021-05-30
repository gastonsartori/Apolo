package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VentanaConfig extends JFrame implements ActionListener {
    private JFrame menuPrincipal;
    private JRadioButton unaCarta;
    private JRadioButton tresCartas;
    private ButtonGroup modoJuego;
    private JTextField nombre;
    private JLabel unaCartaText;
    private JLabel tresCartasText;
    private JLabel nombreText;
    private JButton aceptar;
    private JButton atras;
    private Font fuente;


    public VentanaConfig(JFrame menu){

        menuPrincipal = menu;

        setLayout(null);
        setTitle("Jugar");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());

        registrarFuente();

        nombre = new JTextField();
        nombre.setBounds(150,30,250,25);
        nombre.setFont(fuente);
        add(nombre);

        nombreText = new JLabel("Nombre:");
        nombreText.setBounds(60,27,65,25);
        nombreText.setFont(fuente);
        add(nombreText);

        unaCarta = new JRadioButton();
        tresCartas = new JRadioButton();
        modoJuego = new ButtonGroup();
        unaCartaText = new JLabel("Una carta");
        tresCartasText = new JLabel("Tres cartas");
        unaCarta.setBounds(70, 100, 20, 20);
        tresCartas.setBounds(70,125,20,20);
        unaCartaText.setBounds(95,98,80,25);
        tresCartasText.setBounds(95,123,100,25);
        unaCarta.setFont(fuente);
        tresCartas.setFont(fuente);
        unaCartaText.setFont(fuente);
        tresCartasText.setFont(fuente);

        modoJuego.add(unaCarta);
        modoJuego.add(tresCartas);
        add(unaCarta);
        add(tresCartas);
        add(unaCartaText);
        add(tresCartasText);

        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(this);
        aceptar.setBounds(110,230,110,30);
        aceptar.setBackground(new Color(230,230,230));
        aceptar.setFont(fuente);
        add(aceptar);

        atras = new JButton("Atras");
        atras.addActionListener(this);
        atras.setBounds(290,230,110,30);
        atras.setBackground(new Color(230,230,230));
        atras.setFont(fuente);
        add(atras);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if(boton.equals(aceptar)){
            //ACEPTAR
            if(unaCarta.isSelected()){
                new Controlador(nombre.getText()).setearModoUnaCarta();
            }
            if(tresCartas.isSelected()){
                new Controlador(nombre.getText()).setearModoTresCartas();
            }

            menuPrincipal.dispose();
            dispose();
        }

        else if(boton.equals(atras)){
            dispose();
        }
    }
    public void crearVentana(){
        this.setBounds(0, 0, 512,320);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    public void registrarFuente(){
        try {
            //create the font to use. Specify the size!
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font\\TitilliumWeb.ttf")).deriveFont(18f);
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
