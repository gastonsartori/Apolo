package Perseverance;

import java.util.ArrayList;

public class UnaCarta implements ModoDeJuego{

    public String modo = "unaCarta";

    public String getModo() { return modo; }

    public void pedirCarta(Mazo mazo, ArrayList<Carta> cartasaUbicar){
        if (!mazo.getMazo().isEmpty()) {
            cartasaUbicar.add(mazo.getUltimaCarta());
            mazo.getUltimaCarta().darVuelta();
            mazo.quitarUltimaCarta();
        }
    }

}
