package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfig extends JFrame implements ActionListener {
    private Menu menuPrincipal;
    private JRadioButton unaCarta;
    private JRadioButton tresCartas;
    private ButtonGroup modoJuego;
    private JTextField nombre;
    private JLabel unaCartaText;
    private JLabel tresCartasText;
    private JLabel nombreText;
    private JButton aceptar;
    private JButton atras;


    public VentanaConfig(Menu menu){

        menuPrincipal=menu;

        setLayout(null);
        setResizable(false);
        setTitle("Jugar");

        nombre = new JTextField();
        nombre.setBounds(150,30,250,25);
        add(nombre);

        nombreText = new JLabel("Nombre:");
        nombreText.setBounds(70,27,50,25);
        add(nombreText);

        unaCarta = new JRadioButton();
        tresCartas = new JRadioButton();
        modoJuego = new ButtonGroup();
        unaCartaText = new JLabel("Una carta");
        tresCartasText = new JLabel("Tres cartas");
        unaCarta.setBounds(70, 100, 20, 20);
        tresCartas.setBounds(70,125,20,20);
        unaCartaText.setBounds(95,98,60,25);
        tresCartasText.setBounds(95,123,80,25);

        modoJuego.add(unaCarta);
        modoJuego.add(tresCartas);
        add(unaCarta);
        add(tresCartas);
        add(unaCartaText);
        add(tresCartasText);

        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(this);
        aceptar.setBounds(200,230,90,30);
        aceptar.setBackground(new Color(230,230,230));
        add(aceptar);

        atras = new JButton("Atras");
        atras.addActionListener(this);
        atras.setBounds(300,230,90,30);
        atras.setBackground(new Color(230,230,230));
        add(atras);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if(boton.equals(aceptar)){
            //ACEPTAR
            new Controlador(nombreText.getText());
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
}
