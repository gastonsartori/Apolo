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
    private int movimientosaEscaleras;

    private ArrayList<Observer> observers;

    public Juego(){
        mazo=new Mazo();
        mazo.mezclar();
        pilasJuego=new ArrayList<>();
        crearPilasDeJuego();
        pilasEscalera=new ArrayList<>();
        crearPilasDeEscalera();
        modoDeJuego=new UnaCarta();
        puntaje=new Puntaje();
        cartasaUbicar=new ArrayList<>();
        movimientosaEscaleras=0;
    }

    public Mazo getMazo() { return mazo; }

    public ArrayList<PilaEnZonaDeJuego> getPilasJuego() { return pilasJuego; }

    public ArrayList<PilaEnZonaDeEscalera> getPilasEscalera() { return pilasEscalera; }

    public Puntaje getPuntaje() { return puntaje; }

    public int getMovimientosaEscaleras() { return movimientosaEscaleras; }

    public ModoDeJuego getModoDeJuego() { return modoDeJuego; }

    public ArrayList<Carta> getCartasaUbicar() { return cartasaUbicar; }

    public ArrayList<Observer> getObservers() { return observers; }

    public void crearPilasDeJuego(){
        for (int i = 1; i < 8; i++) {
            PilaEnZonaDeJuego pila=new PilaEnZonaDeJuego();
            ArrayList<Carta> cartas= new ArrayList<>();
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
        int cantidadDeCartas=cartasaUbicar.size();
        for (int i = 0; i < cantidadDeCartas; i++) {
            mazo.getMazo().add(ubicarCarta());
            cartaUbicada();
        }
    }

    //METODOS DE AGREGAR A PILAS

    public void agregaraPila(ArrayList<Carta> cartas, PilaDeCartas pila){
        if(pila.movimientoValido(cartas)){
            pila.agregarCartas(cartas);
            if(pila.getTipo().equals("escalera")) {
                movimientosaEscaleras++;
                if(cartas.get(0).getValor() == Valor.K){
                    if(comprobarWin()){
                        Win();
                    }
                }
            }
        }
    }

    public boolean comprobarWin(){
        int cont=0;
        for (int i = 0; i < 4; i++) {
            if(!pilasEscalera.get(i).getPila().isEmpty()) {
                if (pilasEscalera.get(i).getUltimaCarta().getValor() == Valor.K) {
                    cont++;
                }
            }
        }
        if(cont == 4) return true;
        return false;
    }

    public void Win(){
        System.out.println("ganaste prro");
    }

    //strategy
    public void setModoDeJuego(ModoDeJuego modoDeJuego) { this.modoDeJuego = modoDeJuego; }


    //observer

    @Override
    public void registerObserver(Observer observer) { observers.add(observer); }

    @Override
    public void unregisterObserver(Observer observer) { observers.remove(observer); }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

}
