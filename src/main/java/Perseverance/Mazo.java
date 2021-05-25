package Perseverance;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> mazo;
    private int cartasRestantes;

    public Mazo() {
        this.mazo = new ArrayList<Carta>();
        this.crearMazo();
        cartasRestantes=mazo.size();
        this.mezclar();
    }

    public void crearMazo(){

        Palo[] palos = Palo.values();
        Valor[] valores = Valor.values();

        for (int i = 0; i < palos.length; i++) {
            for (int j = 0; j < valores.length; j++) {
                mazo.add(new Carta(palos[i], valores[j]));
            }
        }
    }

    public void mezclar(){
        Collections.shuffle(mazo);
    }

    public int getCartasRestantes() { return mazo.size(); }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public Carta getUltimaCarta(){
        if(mazo.isEmpty()){
            return null;
        }
        Carta ultima=mazo.get(cartasRestantes-1);
        //VER DE AGREGAR PARA SACAR ULTIMA
        return ultima;
    }


    public void quitarUltimaCarta(){
        mazo.remove(cartasRestantes-1);
    }
}
