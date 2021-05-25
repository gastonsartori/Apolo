package Perseverance;

import java.util.ArrayList;

public abstract class PilaDeCartas{
    protected ArrayList<Carta> pila;
    protected int cartasRestantes;

    public PilaDeCartas() {
        this.pila = new ArrayList<Carta>();
        this.cartasRestantes = pila.size();
    }

    public ArrayList<Carta> getPila() {
        return pila;
    }

    public int getCartasRestantes() {
        return cartasRestantes;
    }
    public Carta getUltimaCarta(){
        return pila.get(cartasRestantes-1);
    }

    public abstract void agregarCartas(ArrayList<Carta> cartas);
    public abstract ArrayList<Carta> obtenerCartas(int index);
    public abstract void sacarCartas(ArrayList<Carta> cartas);
    public abstract boolean comprobarPalo(Carta carta);
    public abstract boolean comprobarValor(Carta carta);
}
