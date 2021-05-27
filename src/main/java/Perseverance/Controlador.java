package Perseverance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Juego juego;
    private Tablero tablero;

    public Controlador(String nombre) {
        System.out.println("creo controlador");
        this.juego = new Juego(nombre);
        this.tablero = new Tablero(juego,this);
        juego.registerObserver((Observer) tablero);
        tablero.crearVentana();
    }

    public void setearModoUnaCarta(){
        ModoDeJuego modo = new UnaCarta();
        juego.setModoDeJuego(modo);
    }
    public void setearModoTresCartas(){
        ModoDeJuego modo = new TresCartas();
        juego.setModoDeJuego(modo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("evento");
        JButton boton = (JButton) e.getSource();
        if(boton.getName().equals("nuevoJuegoTablero")){
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                new VentanaConfig(tablero).crearVentana();
                tablero.dispose();
            }
        }
        else if(boton.getName().equals("reglasTablero")){

        }
        else if(boton.getName().equals("estadisticasTablero")){
        }
        else if(boton.getName().equals("menuPrincipalTablero")){
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                new Menu().crearVentana();
                tablero.dispose();
            }
        }
        else if(boton.getName().equals("unaCartaTablero")){
            setearModoUnaCarta();
        }
        else if(boton.getName().equals("tresCartasTablero")){
            setearModoTresCartas();
        }


    }
}
