package Perseverance;

import java.util.ArrayList;

public class TresCartas implements ModoDeJuego{

    @Override
    public void pedirCarta(Mazo mazo, ArrayList<Carta> cartasaUbicar) {
        for (int i = 0; i < 3; i++) {
            if (!mazo.getMazo().isEmpty()) {
                cartasaUbicar.add(mazo.getUltimaCarta());
                mazo.quitarUltimaCarta();
            }
        }
    }
}
