package Perseverance;

import java.util.ArrayList;

public class UnaCarta implements ModoDeJuego{

    public void pedirCarta(Mazo mazo, ArrayList<Carta> cartasaUbicar){
        if (!mazo.getMazo().isEmpty()) {
            cartasaUbicar.add(mazo.getUltimaCarta());
            mazo.quitarUltimaCarta();
        }
    }

}
