package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfig extends JFrame implements ActionListener {
    private JFrame menuPrincipal;
    private JRadioButton unaCarta;
    private JRadioButton tresCartas;
    private ButtonGroup modoJuego;
    private JTextField nombre;
    private JLabel unaCartaText;
    private JLabel tresCartasText;
    private JLabel nombreText;
    private JLabel ayuda1;
    private JLabel ayuda2;
    private JLabel modo;
    private JButton aceptar;
    private JButton atras;
    private Font fuente;


    public VentanaConfig(JFrame menu){

        menuPrincipal = menu;

        setLayout(null);
        setTitle("Jugar");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());

        fuente=new Font("Berlin Sans FB", 0,16);

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

        ayuda1 = new JLabel("Para jugar, clickee la carta a mover para seleccionarla.");
        ayuda1.setBounds(50,170,450,25);
        ayuda1.setFont(fuente);
        add(ayuda1);

        ayuda2 = new JLabel("Luego, clickee en la pila a la cual desea moverla");
        ayuda2.setBounds(75,195,450,25);
        ayuda2.setFont(fuente);
        add(ayuda2);

        modo = new JLabel("Modo de juego: (cartas entregadas por el mazo)");
        modo.setBounds(30,70,450,25);
        modo.setFont(fuente);
        add(modo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if(boton.equals(aceptar)){
            //ACEPTAR
            if(nombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No olvide ingresar su nombre","Aviso",JOptionPane.INFORMATION_MESSAGE);
            } else if(!unaCarta.isSelected() && !tresCartas.isSelected()){
                JOptionPane.showMessageDialog(null,"No olvide seleccionar un modo de juego","Aviso",JOptionPane.INFORMATION_MESSAGE);
            } else{
                if(unaCarta.isSelected()){
                    Controlador controlador=new Controlador(nombre.getText());
                    controlador.setearModoUnaCarta();
                    controlador.abrirTablero();

                }
                if(tresCartas.isSelected()){
                    Controlador controlador=new Controlador(nombre.getText());
                    controlador.setearModoTresCartas();
                    controlador.abrirTablero();
                }
                menuPrincipal.dispose();
                dispose();
            }

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
