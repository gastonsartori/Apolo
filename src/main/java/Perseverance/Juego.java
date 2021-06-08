package Perseverance;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Juego implements Subject{

    private Mazo mazo;
    private ArrayList<PilaEnZonaDeJuego> pilasJuego;
    private ArrayList<PilaEnZonaDeEscalera> pilasEscalera;
    private int puntuacion;
    private ModoDeJuego modoDeJuego;
    private ArrayList<Carta> cartasaUbicar;
    private int movimientosaEscaleras;
    private int movimientosExitosos;
    private String nombre;
    private int bonificacionDeTiempo;
    private Carta cartaSeleccionada;
    private ArrayList<Carta> origen;
    private ArrayList<Carta> cartasSeleccionadas;
    private int segundos;
    private int minutos;
    private Timer timer;
    private TimerTask task;
    private boolean win;

    private ArrayList<Observer> observers;

    public Juego(String nombre){
        mazo=new Mazo();
        mazo.mezclar();
        pilasJuego=new ArrayList<>();
        crearPilasDeJuego();
        pilasEscalera=new ArrayList<>();
        crearPilasDeEscalera();
        modoDeJuego=new UnaCarta();
        cartasaUbicar=new ArrayList<>();
        movimientosaEscaleras=0;
        movimientosExitosos=0;
        this.nombre=nombre;
        bonificacionDeTiempo=1000;
        actPuntacion();
        observers=new ArrayList<>();
        cartaSeleccionada=null;
        origen = new ArrayList<>();
        segundos=0;
        minutos=0;
        timer=new Timer();
        task= new TimerTask() {
            @Override
            public void run() {
                incrementarTiempo();
            }
        };
        timer.schedule(task,10,1000);
        win=false;
    }

    //GETTERS
    public Mazo getMazo() { return mazo; }

    public ArrayList<PilaEnZonaDeJuego> getPilasJuego() { return pilasJuego; }

    public ArrayList<PilaEnZonaDeEscalera> getPilasEscalera() { return pilasEscalera; }

    public int getPuntacion() { return puntuacion; }

    public int getPuntacionTiempo(){return bonificacionDeTiempo;};

    public int getPuntacionMovimientos(){return movimientosaEscaleras*100;};

    public int getMovimientosExitosos() { return movimientosExitosos; }

    public int getMovimientosaEscaleras() { return movimientosaEscaleras; }

    public ModoDeJuego getModoDeJuego() { return modoDeJuego; }

    public ArrayList<Carta> getCartasaUbicar() { return cartasaUbicar; }

    public ArrayList<Observer> getObservers() { return observers; }

    public boolean getMazoVacio(){return mazo.getMazo().isEmpty();}

    public String getNombre() { return nombre; }

    public Carta getCartaSeleccionada() { return cartaSeleccionada; }

    public int getSegundos() { return segundos; }

    public int getMinutos() { return minutos; }

    public boolean isWin() { return win; }

    public Timer getTimer() { return timer; }

    public TimerTask getTask() { return task; }

    public void noWin(){ win=false; }

    public int getCartasMazo(){
        return mazo.getCartasRestantes() + cartasaUbicar.size();
    }
    public int getCartasJuego(){
        int cont=0;
        for (int i = 0; i < 7; i++) {
            cont+=pilasJuego.get(i).getPila().size();
        }
        return cont;
    }
    public int getCartasEscalera(){
        int cont=0;
        for (int i = 0; i < 4; i++) {
            cont+=pilasEscalera.get(i).getPila().size();
        }
        return cont;
    }

    //

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
        if(cartaSeleccionada!=null){
            deseleccionCarta();
        }else if(cartasSeleccionadas!=null){
            for (int j = 0; j < cartasSeleccionadas.size(); j++) {
                cartasSeleccionadas.get(j).deseleccionar();
            }
            cartasSeleccionadas=null;
            origen=null;
            notifyObservers();
        }
        if(mazo.getMazo().isEmpty()){
            reiniciarMazo();
        }
        else {
            modoDeJuego.pedirCarta(mazo, cartasaUbicar);
        }
        notifyObservers();
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

    public void seleccionCartaUbicar(){
        if(cartaSeleccionada==null){
            seleccionCarta(ubicarCarta());
            origen = cartasaUbicar;
            notifyObservers();
        }else{
            deseleccionCarta();
            origen=null;
            notifyObservers();
        }
    }

    public void seleccionEscalera(int i){
        if(cartaSeleccionada != null){
            if(pilasEscalera.get(i).movimientoValido(cartaSeleccionada)) {
                agregaraPila(cartaSeleccionada, pilasEscalera.get(i));
                origen.remove(cartaSeleccionada);
            }
            deseleccionCarta();
            notifyObservers();
        }
        else{
            if(cartasSeleccionadas != null){
                for (int j = 0; j < cartasSeleccionadas.size(); j++) {
                    cartasSeleccionadas.get(j).deseleccionar();
                }
                cartasSeleccionadas=null;
                origen=null;
                notifyObservers();
            } else if (!pilasEscalera.get(i).getPila().isEmpty()){
                seleccionCarta(pilasEscalera.get(i).getUltimaCarta());
                origen = pilasEscalera.get(i).getPila();
                notifyObservers();
            }
        }
    }

    public void seleccionJuego(int i,int j){

        ArrayList<Carta> cartas = new ArrayList<>();

        if(!pilasJuego.get(i).getPila().isEmpty()) {
            if (pilasJuego.get(i).getPila().get(j).isVisible()) {
                if (cartaSeleccionada == null && cartasSeleccionadas == null) {  // si no tengo nada seleccionado
                    if (pilasJuego.get(i).getCartasRestantes() - 1 == j) {      //si es la ultima carta, selecciono solo esa
                        seleccionarCarta(i);
                        notifyObservers();
                    } else {                                                       //si no, selecciono a partir de esa, hacia abajo
                        for (int k = j; k < pilasJuego.get(i).getCartasRestantes(); k++) {
                            seleccionarCartas(i,k,cartas);
                            notifyObservers();
                        }
                    }
                } else{                              // si tenia seleccionada solo una carta
                    intentarMovimiento(i);
                }
            } else {
                if (pilasJuego.get(i).getCartasRestantes() - 1 == j) {      //si es la ultima carta, la doy vuelta
                    darVueltaUltima(i,j);
                    if(cartaSeleccionada!=null){
                        deseleccionCarta();
                    }
                }
            }
        }else{
            if(cartaSeleccionada != null || cartasSeleccionadas != null){
                intentarMovimiento(i);
            }
        }
}

    public void seleccionarCarta(int i){
        cartaSeleccionada = pilasJuego.get(i).getUltimaCarta();
        cartasSeleccionadas = null;
        cartaSeleccionada.seleccionar();
        origen = pilasJuego.get(i).getPila();
    }

    public void seleccionarCartas(int i, int k, ArrayList<Carta> cartas){
        pilasJuego.get(i).getPila().get(k).seleccionar();
        cartas.add(pilasJuego.get(i).getPila().get(k));
        cartasSeleccionadas = cartas;
        cartaSeleccionada = null;
        origen = pilasJuego.get(i).getPila();
    }

    public void intentarMovimiento(int i){
        if(cartasSeleccionadas==null){
            if (pilasJuego.get(i).movimientoValido(cartaSeleccionada)) {   //verifico movimiento
                agregaraPila(cartaSeleccionada,pilasJuego.get(i));
                origen.remove(cartaSeleccionada);
                deseleccionCarta();
                origen=null;
                notifyObservers();
            }else{
                deseleccionCarta();
                origen=null;
                notifyObservers();
            }
        }else {      // si tenia seleccionada varias
            if (pilasJuego.get(i).movimientoValido(cartasSeleccionadas)) {
                agregaraPila(cartasSeleccionadas,pilasJuego.get(i));
                origen.removeAll(cartasSeleccionadas);
                for (int j = 0; j < cartasSeleccionadas.size(); j++) {
                    cartasSeleccionadas.get(j).deseleccionar();
                }
                cartasSeleccionadas = null;
                origen=null;
                notifyObservers();
            }else{
                for (int j = 0; j < cartasSeleccionadas.size(); j++) {
                    cartasSeleccionadas.get(j).deseleccionar();
                }
                cartasSeleccionadas=null;
                origen=null;
                notifyObservers();
            }
        }
    }

    public void darVueltaUltima(int i,int j){
        pilasJuego.get(i).getPila().get(j).darVuelta();
        notifyObservers();
    }

    public void seleccionCarta(Carta carta){
        cartaSeleccionada=carta;
        cartaSeleccionada.seleccionar();
    }

    public void deseleccionCarta(){
        cartaSeleccionada.deseleccionar();
        cartaSeleccionada = null;

    }

    public void incrementarTiempo(){
        segundos++;
        if(segundos==60){
            segundos=0;
            minutos++;
        }
        actPuntacion();
        notifyObservers();
    }

    //METODOS DE AGREGAR A PILAS

    public void agregaraPila(ArrayList<Carta> cartas, PilaDeCartas pila){
        if(pila.movimientoValido(cartas)){
            pila.agregarCartas(cartas);
            movimientosExitosos++;
            notifyObservers();
        }
    }

    public void agregaraPila(Carta carta, PilaDeCartas pila){
        ArrayList<Carta> cartas= new ArrayList<>();
        cartas.add(carta);
        agregaraPila(cartas,pila);
        if(pila.getTipo().equals("escalera")) {
            if(!carta.isMovidaEscalera()) {
                System.out.println("movi a escalera");
                movimientosaEscaleras++;
                System.out.println(movimientosaEscaleras);
                carta.moveraEscalera();

            }
            actPuntacion();
            if(cartas.get(0).getValor() == Valor.K){
                if(comprobarWin()){
                    win=true;
                }
            }
        }
        notifyObservers();
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



    public void actPuntacion(){
        bonificacionDeTiempo=5000-(segundos+minutos*60)*8;

        puntuacion=bonificacionDeTiempo + getPuntacionMovimientos();
    }

    public void detenerTiempo(){
        task.cancel();
    }

    //strategy
    public void setModoDeJuego(ModoDeJuego modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
        notifyObservers();
    }

    //observer

    @Override
    public void registerObserver(Observer observer) { observers.add(observer);
        System.out.println("añadi observer");}

    @Override
    public void unregisterObserver(Observer observer) { observers.remove(observer); }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

}
