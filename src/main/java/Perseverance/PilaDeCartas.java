package Perseverance;

import java.util.ArrayList;

public abstract class PilaDeCartas{
    protected ArrayList<Carta> pila;
    protected String tipo;

    public PilaDeCartas() {
        this.pila = new ArrayList<Carta>();
    }

    public String getTipo() { return tipo; }
    public ArrayList<Carta> getPila() {
        return pila;
    }
    public int getCartasRestantes() { return pila.size(); }

    public Carta getUltimaCarta(){
        if(pila.isEmpty()){
            return null;
        }
        return pila.get(getCartasRestantes()-1);
    }

    public abstract boolean movimientoValido(ArrayList<Carta> cartas);
    public abstract void agregarCartas(ArrayList<Carta> cartas);
    public abstract ArrayList<Carta> obtenerCartas(int index);
    public abstract void sacarCartas(ArrayList<Carta> cartas);
    public abstract boolean comprobarPalo(Carta carta);
    public abstract boolean comprobarValor(Carta carta);
}
