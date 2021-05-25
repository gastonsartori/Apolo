package Perseverance;

import javax.swing.*;

public class Carta {
    public enum Palo{
        Diamantes, Corazones, Treboles, Picas;

        private static final Palo[] palos = Palo.values();
        public static Palo getPalo(int i){
            return Palo.palos[i];
        }

    }

    public enum Valor{
        As, Dos, Tres, Cuatro, Cinco, Seis, Siete, Ocho, Nueve, Diez, J, Q, K;

        private static final Valor[] valores = Valor.values();
        public static Valor getValor(int i){ return Valor.valores[i]; }

    }

    private final Valor valor;
    private final Palo palo;
    private boolean visible;
    private ImageIcon imagen;



    public Carta(Palo palo, Valor valor){
        this.valor = valor;
        this.palo = palo;
        this.visible=false;
        imagen = new ImageIcon("imagenbocaabajo");
    }

    public Valor getValor() { return valor; }

    public Palo getPalo() { return palo; }

    public boolean isVisible() { return visible; }

    public ImageIcon getImagen() { return imagen; }

    public void darVuelta(){
        this.visible=true;
        this.imagen=new ImageIcon("imagenbocaarriba");
    }

    @Override
    public String toString() {
        return valor + "de" + palo;
    }
}
