package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private JButton jugar;
    private JButton salir;
    private JButton reglas;
    private JButton puntajes;
    private JLabel fondo;
    private Font fuente;

    public Menu(){
        setLayout(null);
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

        fuente=new Font("Berlin Sans FB", 0,16);

        jugar = new JButton("Jugar");
        jugar.setHorizontalTextPosition(jugar.CENTER);
        jugar.setVerticalTextPosition(jugar.CENTER);
        jugar.setBounds(640-75, 360-75, 150,40);
        jugar.setBackground(new Color(230,230,230));
        jugar.setFocusPainted(false);
        jugar.addActionListener(this);
        jugar.setFont(fuente);
        add(jugar);

        salir = new JButton("Salir");
        salir.setHorizontalTextPosition(salir.CENTER);
        salir.setVerticalTextPosition(salir.CENTER);
        salir.setBounds(640-75, 360+75, 150,40);
        salir.setBackground(new Color(230,230,230));
        salir.setFocusPainted(false);
        salir.addActionListener(this);
        salir.setFont(fuente);
        add(salir);

        reglas = new JButton("Reglas");
        reglas.setHorizontalTextPosition(reglas.CENTER);
        reglas.setVerticalTextPosition(reglas.CENTER);
        reglas.setBounds(640-75, 360+25, 150,40);
        reglas.setBackground(new Color(230,230,230));
        reglas.setFocusPainted(false);
        reglas.addActionListener(this);
        reglas.setFont(fuente);
        add(reglas);

        puntajes = new JButton("Puntajes");
        puntajes.setHorizontalTextPosition(puntajes.CENTER);
        puntajes.setVerticalTextPosition(puntajes.CENTER);
        puntajes.setBounds(640-75, 360-25, 150,40);
        puntajes.setBackground(new Color(230,230,230));
        puntajes.setFocusPainted(false);
        puntajes.addActionListener(this);
        puntajes.setFont(fuente);
        add(puntajes);

        ImageIcon fondopng = new ImageIcon("images/fondo.png");
        fondo = new JLabel(fondopng);
        fondo.setBounds(0,0,1280,720);
        add(fondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if(boton.equals(jugar)){
            new VentanaConfig(this).crearVentana();
        }
        else if(boton.equals(reglas)){
            new Reglas().crearVentana();
        }
        else if(boton.equals(puntajes)){
            new PuntajeHistorico(null).crearVentana(430);
        }
        else if(boton.equals(salir)){
            System.exit(0);
        }

    }

    public void crearVentana(){
        this.setBounds(0, 0, 1280,720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }
}
