package Perseverance;

import javax.swing.*;
import java.util.ArrayList;

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
    }

    //GETTERS
    public Mazo getMazo() { return mazo; }

    public ArrayList<PilaEnZonaDeJuego> getPilasJuego() { return pilasJuego; }

    public ArrayList<PilaEnZonaDeEscalera> getPilasEscalera() { return pilasEscalera; }

    public int getPuntacion() { return puntuacion; }

    public int getPuntacionTiempo(){return bonificacionDeTiempo;};

    public int getPuntacionMovimientos(){return movimientosaEscaleras*10;};

    public int getMovimientosExitosos() { return movimientosExitosos; }

    public int getMovimientosaEscaleras() { return movimientosaEscaleras; }

    public ModoDeJuego getModoDeJuego() { return modoDeJuego; }

    public ArrayList<Carta> getCartasaUbicar() { return cartasaUbicar; }

    public ArrayList<Observer> getObservers() { return observers; }

    public boolean getMazoVacio(){return mazo.getMazo().isEmpty();}

    public String getNombre() { return nombre; }

    public Carta getCartaSeleccionada() { return cartaSeleccionada; }


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
        seleccionCarta(ubicarCarta());
        origen = cartasaUbicar;
        notifyObservers();
    }

    public void seleccionEscalera(int i){
        if(cartaSeleccionada != null && pilasEscalera.get(i).movimientoValido(cartaSeleccionada)){
            agregaraPila(cartaSeleccionada,pilasEscalera.get(i));
            origen.remove(cartaSeleccionada);
            deseleccionCarta();
            notifyObservers();
        }
        else if (cartaSeleccionada == null && !pilasEscalera.get(i).getPila().isEmpty()){
            seleccionCarta(pilasEscalera.get(i).getUltimaCarta());
            origen = pilasEscalera.get(i).getPila();
            notifyObservers();
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
                    cartasSeleccionadas.get(i).deseleccionar();
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
        cartaSeleccionada=null;
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
            movimientosaEscaleras++;
            System.out.println("mov a escalera " + movimientosaEscaleras);
            actPuntacion();
            if(cartas.get(0).getValor() == Valor.K){
                if(comprobarWin()){
                    win();
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

    public void win(){
        JOptionPane.showMessageDialog(null, "Felicidades, has ganado el juego!");
        System.exit(0);
    }

    public void actPuntacion(){ puntuacion=bonificacionDeTiempo + movimientosaEscaleras*10;}

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
