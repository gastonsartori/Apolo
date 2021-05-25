package Perseverance;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Juego implements Subject{

    private Mazo mazo;
    private ArrayList<PilaEnZonaDeJuego> pilasJuego;
    private ArrayList<PilaEnZonaDeEscalera> pilasEscalera;
    private Puntaje puntaje;
    private ModoDeJuego modoDeJuego;
    private ArrayList<Carta> cartasaUbicar;

    private ArrayList<Observer> observers;

    public Juego(ModoDeJuego modo){
        mazo=new Mazo();
        mazo.mezclar();
        pilasJuego=new ArrayList<PilaEnZonaDeJuego>();
        crearPilasDeJuego();
        pilasEscalera=new ArrayList<PilaEnZonaDeEscalera>();
        crearPilasDeEscalera();
        modoDeJuego=modo;
        puntaje=new Puntaje();
        cartasaUbicar=new ArrayList<Carta>();
    }



    public void crearPilasDeJuego(){
        for (int i = 1; i < 8; i++) {
            PilaEnZonaDeJuego pila=new PilaEnZonaDeJuego();
            ArrayList<Carta> cartas= new ArrayList<Carta>();
            for (int j = 0; j < i; j++) {
                cartas.add(mazo.getUltimaCarta());
                mazo.quitarUltimaCarta();
            }
            pila.agregarCartas(cartas);
            pila.voltearUltimaCarta();
            pilasJuego.add(pila);
        }
    }

    public void crearPilasDeEscalera(){
        for (int i = 0; i < 4; i++) {
            pilasEscalera.add(new PilaEnZonaDeEscalera());
        }
    }

    //METODOS DE CARTAS A UBICAR, PEDIR NUEVAS, O UBICARLAS

    public void pedirCartas(){
        if(mazo.getMazo().isEmpty()){
            reiniciarMazo();
        }
        else {
            for (int i = 0; i < modoDeJuego.cantidad(); i++) {
                if (!mazo.getMazo().isEmpty()) {
                    cartasaUbicar.add(mazo.getUltimaCarta());
                    mazo.quitarUltimaCarta();
                }
            }
        }
    }

    public Carta ubicarCarta(){
        return cartasaUbicar.get(cartasaUbicar.size()-1);
    }

    public void cartaUbicada(){
        cartasaUbicar.remove(cartasaUbicar.size()-1);
    }

    public void reiniciarMazo(){
        for (int i = 0; i < cartasaUbicar.size(); i++) {
            mazo.getMazo().add(ubicarCarta());
            cartaUbicada();
        }
    }

    //METODOS DE AGREGAR A PILAS

    public void agregaraPila(ArrayList<Carta> cartas, PilaDeCartas pila){
        if(pila.movimientoValido(cartas)){
            pila.agregarCartas(cartas);
        }
        if(pila.getTipo()=="escalera" && cartas.get(0).getValor() == Valor.K) {
            comprobarWin();
        }
    }

    public void comprobarWin(){
        int cont=0;
        for (int i = 0; i < 4; i++) {
            if(pilasEscalera.get(i).getUltimaCarta().getValor() == Valor.K){
                cont++;
            }
        }
        if(cont == 4){
            Win();
        }
    }

    public void Win(){

    }

    public void setModoDeJuego(ModoDeJuego modoDeJuego) { this.modoDeJuego = modoDeJuego; }



}
