package Perseverance;

public class TresCartas implements ModoDeJuego{

    int cantidad;

    public TresCartas() {
        cantidad=3;
    }

    @Override
    public int getCantidad() { return cantidad; }
}
