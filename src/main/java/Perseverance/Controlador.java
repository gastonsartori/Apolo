package Perseverance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Juego juego;
    private Tablero tablero;
    private Estadisticas estadisticas;
    private Reglas reglas;

    public Controlador(String nombre) {
        this.juego = new Juego(nombre);
        this.tablero = new Tablero(juego,this);
        juego.registerObserver((Observer) tablero);
    }

    public Juego getJuego() {
        return juego;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void abrirTablero(){
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

        JButton boton = (JButton) e.getSource();
        if(boton.getName().equals("nuevoJuegoTablero")){
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                new VentanaConfig(tablero).crearVentana();
                if(estadisticas != null){
                    estadisticas.dispose();
                }
                if(reglas != null){
                    reglas.dispose();
                }
                tablero.dispose();
            }
        }else if(boton.getName().equals("reglasTablero")){
            reglas=new Reglas();
            reglas.crearVentana();
        }else if(boton.getName().equals("estadisticasTablero")){
            estadisticas = new Estadisticas(juego);
            estadisticas.crearVentana();
            juego.registerObserver((Observer) estadisticas);
        }else if(boton.getName().equals("menuPrincipalTablero")){
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                new Menu().crearVentana();
                tablero.dispose();
            }
        }else if(boton.getName().equals("unaCartaTablero")){
            setearModoUnaCarta();
        }else if(boton.getName().equals("tresCartasTablero")){
            setearModoTresCartas();
        }else if(boton.getName().equals("mazoTablero")){
            juego.pedirCartas();
        }else if(boton.getName().equals("cartaUbicar")){
            juego.seleccionCartaUbicar();
        }
        for (int i = 0; i < 4; i++) {
            if(boton.getName().equals("escalera"+i)){
                juego.seleccionEscalera(i);
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 19; j++) {
                if(boton.getName().equals("juego"+i+j)){
                    juego.seleccionJuego(i,j);
                }
            }
        }
    }
}
