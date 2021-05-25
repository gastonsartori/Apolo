package Perseverance;

import java.util.ArrayList;

public class PilaEnZonaDeEscalera extends PilaDeCartas{
    public PilaEnZonaDeEscalera(){
        super();
    }
    @Override
    public void agregarCartas(ArrayList<Carta> cartas) {
        if (cartas.size() == 1){                                //Si viene solo una carta
            if(pila.isEmpty()) {                                // Si la pila en zona de escalera esta vacia
                if(cartas.get(0).getValor().ordinal() == 0){     //si la carta es un As
                    pila.add(cartas.get(0));                     //Agrego la carta a la pila
                }
            }
            else{                                                                 //Si la pila NO esta vacia
                if(comprobarPalo(cartas.get(0)) && comprobarValor(cartas.get(0))){ //El palo y valor de la carta es correcto
                    pila.add(cartas.get(0));                                       //Agrego la carta a la pila
                }
            }
        }

    }

    @Override
    public ArrayList<Carta> obtenerCartas(int index) {
        ArrayList<Carta> ultima=new ArrayList<Carta>();
        ultima.add(pila.get(cartasRestantes-1));
        //VER DE AGREGAR PARA SACAR ULTIMA
        return ultima;
    }

    @Override
    public void sacarCartas(ArrayList<Carta> cartas) {
        pila.remove(cartasRestantes-1);
    }

    @Override
    public boolean comprobarPalo(Carta carta) {
        if(this.getUltimaCarta().getPalo() == carta.getPalo()){
            return true;
        }
        return false;
    }

    @Override
    public boolean comprobarValor(Carta carta) {
        if(this.getUltimaCarta().getValor().ordinal() == carta.getValor().ordinal()-1){
            return true;
        }
        return false;
    }
}
