package Perseverance;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import java.util.ArrayList;

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
        assertTrue(mezclado);
    }

    @Test
    public void testAgregarPZDJVaciaK(){
        PilaEnZonaDeJuego pila = new PilaEnZonaDeJuego();
        Carta carta = new Carta(Palo.Corazones,Valor.K);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        assertTrue(pila.movimientoValido(cartas));
    }

    @Test
    public void testAgregarPZDJVaciaNoK(){
        PilaEnZonaDeJuego pila = new PilaEnZonaDeJuego();
        Carta carta = new Carta(Palo.Corazones,Valor.As);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);


        assertFalse(pila.movimientoValido(cartas));
    }

    @Test
    public void testAgregarPZDJValido(){
        PilaEnZonaDeJuego pila = new PilaEnZonaDeJuego();
        Carta carta = new Carta(Palo.Corazones,Valor.K);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        pila.agregarCartas(cartas);

        Carta carta2 = new Carta(Palo.Picas,Valor.Q);
        ArrayList<Carta> cartas2=new ArrayList<Carta>();
        cartas2.add(carta2);


        assertTrue(pila.movimientoValido(cartas2));
    }

    @Test
    public void testAgregarPZDJNoValido(){
        PilaEnZonaDeJuego pila = new PilaEnZonaDeJuego();
        Carta carta = new Carta(Palo.Corazones,Valor.K);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        pila.agregarCartas(cartas);

        Carta carta2 = new Carta(Palo.Diamantes,Valor.Q);
        ArrayList<Carta> cartas2=new ArrayList<Carta>();
        cartas2.add(carta);

        assertFalse(pila.movimientoValido(cartas2));
    }

    @Test
    public void testAgregarPZDEVaciaAs(){
        PilaEnZonaDeEscalera pila = new PilaEnZonaDeEscalera();
        Carta carta = new Carta(Palo.Corazones,Valor.As);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        assertTrue(pila.movimientoValido(cartas));
    }

    @Test
    public void testAgregarPZDEVaciaNoAs(){
        PilaEnZonaDeEscalera pila = new PilaEnZonaDeEscalera();
        Carta carta = new Carta(Palo.Corazones,Valor.Dos);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);


        assertFalse(pila.movimientoValido(cartas));
    }

    @Test
    public void testAgregarPZDEValido(){
        PilaEnZonaDeEscalera pila = new PilaEnZonaDeEscalera();
        Carta carta = new Carta(Palo.Corazones,Valor.As);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        pila.agregarCartas(cartas);

        Carta carta2 = new Carta(Palo.Corazones,Valor.Dos);
        ArrayList<Carta> cartas2=new ArrayList<Carta>();
        cartas2.add(carta2);

        assertTrue(pila.movimientoValido(cartas2));
    }

    @Test
    public void testAgregarPZDENoValido(){
        PilaEnZonaDeEscalera pila = new PilaEnZonaDeEscalera();
        Carta carta = new Carta(Palo.Corazones,Valor.As);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(carta);

        pila.agregarCartas(cartas);

        Carta carta2 = new Carta(Palo.Diamantes,Valor.Dos);
        ArrayList<Carta> cartas2=new ArrayList<Carta>();
        cartas2.add(carta);

        assertFalse(pila.movimientoValido(cartas2));
    }

    @Test
    public void testCreadoDePilasDeJuego(){
        Juego juego = new Juego();

        assertEquals(7,juego.getPilasJuego().size());
        assertEquals(1,juego.getPilasJuego().get(0).getCartasRestantes());
        assertEquals(2,juego.getPilasJuego().get(1).getCartasRestantes());
        assertEquals(3,juego.getPilasJuego().get(2).getCartasRestantes());
        assertEquals(4,juego.getPilasJuego().get(3).getCartasRestantes());
        assertEquals(5,juego.getPilasJuego().get(4).getCartasRestantes());
        assertEquals(6,juego.getPilasJuego().get(5).getCartasRestantes());
        assertEquals(7,juego.getPilasJuego().get(6).getCartasRestantes());
    }

    @Test
    public void testCreadoDePilasDeEscalera(){
        Juego juego = new Juego();

        assertEquals(4,juego.getPilasEscalera().size());
        assertEquals(0,juego.getPilasEscalera().get(0).getCartasRestantes());
        assertEquals(0,juego.getPilasEscalera().get(1).getCartasRestantes());
        assertEquals(0,juego.getPilasEscalera().get(2).getCartasRestantes());
        assertEquals(0,juego.getPilasEscalera().get(3).getCartasRestantes());
    }

    @Test
    public void testPedirCartas(){
        Juego juego=new Juego();
        juego.pedirCartas();
        //dsp de creadas las pilas de juego, quedan 24 cartas en el mazo
        //si pido 1, deben quedar 23 en el mazo, y 1 en la zona de cartas a ubicar
        assertEquals(23,juego.getMazo().getCartasRestantes());
        assertEquals(1,juego.getCartasaUbicar().size());
    }

    @Test
    public void testReiniciarMazo(){
        Juego juego=new Juego();
        Mazo mazoInicial = new Mazo();
        mazoInicial.setMazo((ArrayList<Carta>) juego.getMazo().getMazo().clone());
        for (int i = 0; i < 25; i++) {
            juego.pedirCartas();
        }
        int coincidencias=0;
        for (int i = 0; i < 24; i++) {
            if(mazoInicial.getMazo().get(i) == juego.getMazo().getMazo().get(i)){
                coincidencias++;
            }
        }
        assertEquals(24,coincidencias);
        assertEquals(24,juego.getMazo().getCartasRestantes());
        assertEquals(0,juego.getCartasaUbicar().size());
    }

    @Test
    public void testAgregaraPila(){
        Juego juego=new Juego();
        Carta carta=new Carta(Palo.Corazones,Valor.As);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(carta);
        juego.agregaraPila(cartas,juego.getPilasEscalera().get(0));

        assertEquals(carta,juego.getPilasEscalera().get(0).getUltimaCarta());

    }

    @Test
    public void testComprobarWin(){
        Juego juego = new Juego();
        for (int i = 0; i < 4; i++) {
            ArrayList<Carta> carta = new ArrayList<>();
            carta.add(new Carta(Palo.getPalo(i),Valor.K));
            juego.getPilasEscalera().get(i).agregarCartas(carta);

        }
        assertTrue(juego.comprobarWin());
    }


}
