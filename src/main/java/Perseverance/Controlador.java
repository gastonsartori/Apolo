package Perseverance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Juego juego;
    private Tablero tablero;

    public Controlador(String nombre) {
        this.juego = new Juego(nombre);
        this.tablero = new Tablero(juego);
        tablero.crearVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
