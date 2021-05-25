package Perseverance;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testCrearMazo() {
        Mazo mazo=new Mazo();
        int repetida=0;
        for (int i = 0; i < mazo.getCartasRestantes()-1; i++) {
            for (int j = 0; j < mazo.getCartasRestantes()-1; j++) {
                if(mazo.getMazo().get(i) == mazo.getMazo().get(j)){
                    repetida++;
                }
            }
        }
        assertEquals(51,repetida);
    }

    @Test
    public void testMezclarMazo() {
        Mazo mazo=new Mazo();
        Mazo mazoMezclado=new Mazo();
        mazoMezclado.mezclar();
        int coincidencias=0;
        boolean mezclado=false;
        for (int i = 0; i < mazo.getCartasRestantes()-1; i++) {
            if(mazo.getMazo().get(i).getPalo() == mazoMezclado.getMazo().get(i).getPalo() && mazo.getMazo().get(i).getValor() == mazoMezclado.getMazo().get(i).getValor()){
                coincidencias++;
            }
        }
        if(coincidencias<5){
            mezclado=true;
        }
        assertEquals(true,mezclado);
    }

}
