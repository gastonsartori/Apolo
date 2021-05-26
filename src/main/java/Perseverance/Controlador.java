package Perseverance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Juego juego;
    private Tablero tablero;

    public Controlador(Juego juego, Tablero tablero) {
        this.juego = juego;
        this.tablero = tablero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
