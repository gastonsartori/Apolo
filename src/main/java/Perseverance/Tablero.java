package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tablero extends JFrame implements Observer {

    private JButton nuevoJuego;
    private JButton menuPrincipal;
    private JButton Reglas;
    private JButton Estadisticas;
    private ArrayList<JButton> cartasDeJuego;
    private ArrayList<JButton> cartasDeEscalera;
    private Font fuente;
    private JLabel tablero;

    private ArrayList<PilaEnZonaDeJuego> pilasDeJuego;
    private ArrayList<PilaEnZonaDeEscalera> pilasDeEscalera;
    private ArrayList<Carta> cartasaUbicar;
    private boolean mazoVacio;
    private int puntaje;
    private int movimientosExitosos;

    private Juego juego;

    public Tablero(){
        setLayout(null);
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        fuente = new Font("font/TitilliumWeb.ttf", Font.TRUETYPE_FONT, 20);

        ImageIcon tableropng = new ImageIcon("images/tablero.png");
        tablero = new JLabel(tableropng);
        tablero.setBounds(0,0,1280,720);
        add(tablero);





    }

    @Override
    public void update(){
        pilasDeJuego=juego.getPilasJuego();
        pilasDeEscalera=juego.getPilasEscalera();
        cartasaUbicar= juego.getCartasaUbicar();
        mazoVacio=juego.getMazoVacio();
        puntaje=juego.getPuntaje();
        movimientosExitosos=juego.getMovimientosExitosos();
        recargar();
    }

    public void recargar(){

    }

    public void crearVentana(){
        this.setBounds(0, 0, 1280,720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
