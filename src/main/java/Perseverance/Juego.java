package Perseverance;

import java.lang.reflect.Array;
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
        cartaSeleccionada = ubicarCarta();
        origen = cartasaUbicar;
    }

    public void seleccionEscalera(int i){
        if(cartaSeleccionada != null && pilasEscalera.get(i).movimientoValido(cartaSeleccionada)){
            pilasEscalera.get(i).agregarCartas(cartaSeleccionada);
            origen.remove(cartaSeleccionada);
            notifyObservers();
            cartaSeleccionada = null;
        }
        else if (cartaSeleccionada == null){
            cartaSeleccionada = pilasEscalera.get(i).getUltimaCarta();
            origen = pilasEscalera.get(i).getPila();
        }
    }
    public void seleccionJuego(int i,int j){

        ArrayList<Carta> cartas = new ArrayList<>();

        if(!pilasJuego.get(i).getPila().isEmpty()) {
            if (pilasJuego.get(i).getPila().get(j).isVisible()) {
                if (cartaSeleccionada == null && cartasSeleccionadas == null) {  // si no tengo nada seleccionado
                    if (pilasJuego.get(i).getCartasRestantes() - 1 == j) {      //si es la ultima carta, selecciono solo esa
                        cartaSeleccionada = pilasJuego.get(i).getUltimaCarta();
                        cartasSeleccionadas = null;
                        origen = pilasJuego.get(i).getPila();
                        System.out.println(cartaSeleccionada.toString());
                    } else {                                                       //si no, selecciono a partir de esa, hacia abajo
                        for (int k = j; k < pilasJuego.get(i).getCartasRestantes(); k++) {
                            System.out.println(pilasJuego.get(i).getPila().get(k).toString());
                            cartas.add(pilasJuego.get(i).getPila().get(k));
                            cartasSeleccionadas = cartas;
                            cartaSeleccionada = null;
                            origen = pilasJuego.get(i).getPila();
                        }
                    }
                } else if (cartasSeleccionadas == null) {                              // si tenia seleccionada solo una carta
                    if (pilasJuego.get(i).movimientoValido(cartaSeleccionada)) {   //verifico movimiento
                        pilasJuego.get(i).agregarCarta(cartaSeleccionada);
                        origen.remove(cartaSeleccionada);
                        notifyObservers();
                        cartaSeleccionada = null;
                    }else{
                        cartaSeleccionada=null;
                        origen=null;
                    }
                } else {      // si tenia seleccionada varias
                    if (pilasJuego.get(i).movimientoValido(cartasSeleccionadas)) {
                        pilasJuego.get(i).agregarCartas(cartasSeleccionadas);
                        origen.removeAll(cartasSeleccionadas);
                        notifyObservers();
                        cartasSeleccionadas = null;
                    }else{
                        cartasSeleccionadas=null;
                        origen=null;
                    }
                }
            } else {
                if (pilasJuego.get(i).getCartasRestantes() - 1 == j) {      //si es la ultima carta, la doy vuelta
                    pilasJuego.get(i).getPila().get(j).darVuelta();
                    notifyObservers();
                }
            }
        }else{
            if(cartaSeleccionada != null || cartasSeleccionadas != null){
                if(cartasSeleccionadas==null){
                    if (pilasJuego.get(i).movimientoValido(cartaSeleccionada)) {   //verifico movimiento
                        pilasJuego.get(i).agregarCarta(cartaSeleccionada);
                        origen.remove(cartaSeleccionada);
                        notifyObservers();
                        cartaSeleccionada = null;
                    }else{
                        cartaSeleccionada=null;
                        origen=null;
                    }
                }else {      // si tenia seleccionada varias
                    if (pilasJuego.get(i).movimientoValido(cartasSeleccionadas)) {
                        pilasJuego.get(i).agregarCartas(cartasSeleccionadas);
                        origen.removeAll(cartasSeleccionadas);
                        notifyObservers();
                        cartasSeleccionadas = null;
                    }else{
                        cartasSeleccionadas=null;
                        origen=null;
                    }
                }
            }
        }
}



    //METODOS DE AGREGAR A PILAS

    public void agregaraPila(ArrayList<Carta> cartas, PilaDeCartas pila){
        if(pila.movimientoValido(cartas)){
            pila.agregarCartas(cartas);
            movimientosExitosos++;
            if(pila.getTipo().equals("escalera")) {
                movimientosaEscaleras++;
                actPuntacion();
                if(cartas.get(0).getValor() == Valor.K){
                    if(comprobarWin()){
                        win();
                    }
                }
            }
            notifyObservers();
        }
    }

    public void agregaraPila(Carta carta, PilaDeCartas pila){
        ArrayList<Carta> cartas= new ArrayList<>();
        cartas.add(carta);
        agregaraPila(cartas,pila);
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
        System.out.println("ganaste prro");
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
