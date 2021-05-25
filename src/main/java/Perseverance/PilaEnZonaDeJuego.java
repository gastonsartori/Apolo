package Perseverance;

import java.util.ArrayList;

public class PilaEnZonaDeJuego extends PilaDeCartas{

    @Override
    public void agregarCartas(ArrayList<Carta> cartas) {
        if(pila.isEmpty()) {                                    // Si la pila en zona de juego esta vacia
            if(cartas.get(0).getValor().ordinal() == 12){       //si la carta es una K
                pila.addAll(cartas);                            //Agrego las cartas a la pila
            }
        }
        else{                                                                   //Si la pila NO esta vacia
            if(comprobarPalo(cartas.get(0)) && comprobarValor(cartas.get(0))){  //El palo y valor de la carta es correcto
                pila.addAll(cartas);                                            //Agrego las cartas a la pila
            }
        }
    }

    @Override
    public ArrayList<Carta> obtenerCartas(int index) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        for (int i = index; i < pila.size()-1; i++) {
            cartas.add(pila.get(i));
        }
        return cartas;
    }

    @Override
    public void sacarCartas(ArrayList<Carta> cartas) {
        for (int i = 0; i < cartas.size()-1; i++) {
            pila.remove(cartas.get(i));
        }
    }

    @Override
    public boolean comprobarPalo(Carta carta) {
        if(this.getUltimaCarta().getPalo().ordinal() < 2){ //Si la carta donde se desea mover es roja
            if(carta.getPalo().ordinal() >= 2){            //Si la carta a mover es negra
                return true;                               //Palo correcto
            }
        }
        else{                                              //Si la carta donde se desea mover es negra
            if(carta.getPalo().ordinal() < 2){             //Si la carta a mover es roja
                return true;                               //Palo correcto
            }
        }
        return false;                                      //Ninguna condicion, palo incorrecto
    }

    @Override
    public boolean comprobarValor(Carta carta) {
        if(this.getUltimaCarta().getValor().ordinal() == carta.getValor().ordinal()+1){
            return true;
        }
        return false;
    }


}
