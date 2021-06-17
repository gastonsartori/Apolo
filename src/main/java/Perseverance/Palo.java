package Perseverance;

public enum Palo {
    Diamantes, Corazones, Treboles, Picas;

    private static final Palo[] palos = Palo.values();
    public static Palo getPalo(int i){ return Palo.palos[i]; }
}
