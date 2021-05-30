package Perseverance;

import javax.swing.*;

public class Carta {

    private final Valor valor;
    private final Palo palo;
    private boolean visible;
    private ImageIcon imagen;

    public Carta(Palo palo, Valor valor){
        this.valor = valor;
        this.palo = palo;
        this.visible=false;
        imagen = new ImageIcon("images/reversocarta.png");
    }

    public Valor getValor() { return valor; }

    public Palo getPalo() { return palo; }

    public boolean isVisible() { return visible; }

    public ImageIcon getImagen() { return imagen; }

    public void darVuelta(){
        if(!visible) {
            this.visible = true;
            this.imagen = new ImageIcon("images/" + this.toString() + ".png");
        }
    }

    public void seleccionar(){
        System.out.println("selecciono");
        this.imagen = new ImageIcon("images/" + this.toString() + "Seleccionada.png");
    }
    public void deseleccionar(){
        System.out.println("deselecciono");
        this.imagen = new ImageIcon("images/" + this.toString() + ".png");
    }

    @Override
    public String toString() {
        return valor + "de" + palo;
    }
}
