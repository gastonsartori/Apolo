package Perseverance;

public enum Valor {
    As, Dos, Tres, Cuatro, Cinco, Seis, Siete, Ocho, Nueve, Diez, J, Q, K;

    private static final Valor[] valores = Valor.values();
    public static Valor getValor(int i){ return Valor.valores[i]; }
}
