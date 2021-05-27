package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tablero extends JFrame implements Observer{

    private JButton nuevoJuego;
    private JButton menuPrincipal;
    private JButton reglas;
    private JButton estadisticas;
    private JButton unaCarta;
    private JButton tresCartas;

    private ArrayList<JButton> cartasDeJuego;
    private ArrayList<JButton> cartasDeEscalera;
    private Font fuente;

    private JLabel tablero;
    private JLabel nombre;
    private JLabel jugador;
    private JLabel tiempo;
    private JLabel movimientos;
    private JLabel puntuacion;

    private ArrayList<PilaEnZonaDeJuego> pilasDeJuego;
    private ArrayList<PilaEnZonaDeEscalera> pilasDeEscalera;
    private ArrayList<Carta> cartasaUbicar;
    private boolean mazoVacio;
    private int puntaje;
    private int movimientosExitosos;
    private String nombreJugador;
    private ModoDeJuego modoDeJuego;

    private Juego juego;
    private Controlador controlador;

    public Tablero(Juego juego, Controlador controlador){

        this.controlador=controlador;
        this.juego=juego;
        registrarFuente();
        update();

        setLayout(null);
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));


        ImageIcon tableropng = new ImageIcon("images/tablero.png");
        tablero = new JLabel(tableropng);
        tablero.setBounds(0,0,1280,720);


        nombre= new JLabel("Nombre: ");
        nombre.setBounds(10,0,200, 30);
        nombre.setForeground(new Color(0,0,0));
        nombre.setFont(fuente);
        add(nombre);

        tiempo= new JLabel("Tiempo: ");
        tiempo.setBounds(10,30,200, 30);
        tiempo.setForeground(new Color(0,0,0));
        tiempo.setFont(fuente);
        add(tiempo);

        movimientos= new JLabel("Movimientos Exitosos: ");
        movimientos.setBounds(10,60,400, 30);
        movimientos.setForeground(new Color(0,0,0));
        movimientos.setFont(fuente);
        add(movimientos);

        puntuacion= new JLabel("Puntuación: ");
        puntuacion.setBounds(410,60,200, 30);
        puntuacion.setForeground(new Color(0,0,0));
        puntuacion.setFont(fuente);
        add(puntuacion);

        jugador= new JLabel(nombreJugador);
        jugador.setBounds(100,0,200, 30);
        jugador.setForeground(new Color(0,0,0));
        jugador.setFont(fuente);
        add(jugador);

        nuevoJuego = new JButton("Nuevo Juego");
        nuevoJuego.setBounds(750, 10,150,35);
        nuevoJuego.setFont(fuente);
        nuevoJuego.setBackground(new Color(230,230,230));
        nuevoJuego.setFocusPainted(false);
        nuevoJuego.setName("nuevoJuegoTablero");
        nuevoJuego.addActionListener(controlador);
        add(nuevoJuego);

        menuPrincipal = new JButton("Menu");
        menuPrincipal.setBounds(920, 10,150,35);
        menuPrincipal.setFont(fuente);
        menuPrincipal.setBackground(new Color(230,230,230));
        menuPrincipal.setFocusPainted(false);
        menuPrincipal.setName("menuPrincipalTablero");
        menuPrincipal.addActionListener(controlador);
        add(menuPrincipal);

        reglas = new JButton("Reglas");
        reglas.setBounds(750, 52,150,35);
        reglas.setFont(fuente);
        reglas.setBackground(new Color(230,230,230));
        reglas.setFocusPainted(false);
        reglas.setName("reglasTablero");
        reglas.addActionListener(controlador);
        add(reglas);

        estadisticas = new JButton("Estadisticas");
        estadisticas.setBounds(920, 52,150,35);
        estadisticas.setFont(fuente);
        estadisticas.setBackground(new Color(230,230,230));
        estadisticas.setFocusPainted(false);
        estadisticas.setName("estadisticasTablero");
        estadisticas.addActionListener(controlador);
        add(estadisticas);

        unaCarta = new JButton("Una carta");
        unaCarta.setBounds(1090, 10,150,35);
        unaCarta.setFont(fuente);
        if(modoDeJuego.getModo().equals("unaCarta")){
            unaCarta.setBackground(new Color(235,113,113));
        }else{
            unaCarta.setBackground(new Color(230,230,230));
        }
        unaCarta.setFocusPainted(false);
        unaCarta.setName("unaCartaTablero");
        unaCarta.addActionListener(controlador);
        add(unaCarta);

        tresCartas = new JButton("Tres cartas");
        tresCartas.setBounds(1090, 52,150,35);
        tresCartas.setFont(fuente);
        if(modoDeJuego.getModo().equals("tresCartas")){
            tresCartas.setBackground(new Color(235,113,113));
        }else{
            tresCartas.setBackground(new Color(230,230,230));
        }
        tresCartas.setFocusPainted(false);
        tresCartas.setName("tresCartasTablero");
        tresCartas.addActionListener(controlador);
        add(tresCartas);


        add(tablero);

    }

    @Override
    public void update(){
        pilasDeJuego=juego.getPilasJuego();
        pilasDeEscalera=juego.getPilasEscalera();
        cartasaUbicar= juego.getCartasaUbicar();
        mazoVacio=juego.getMazoVacio();
        puntaje=juego.getPuntacion();
        movimientosExitosos=juego.getMovimientosExitosos();
        nombreJugador=juego.getNombre();
        modoDeJuego=juego.getModoDeJuego();

        recargarVentana();
    }

    public void recargarVentana(){

        recargarBotonesModo();

    }

    public void crearVentana(){
        this.setBounds(0, 0, 1280,720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void registrarFuente(){
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font\\TitilliumWeb.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void recargarBotonesModo(){
        if(!(unaCarta == null || tresCartas == null)) {
            if (modoDeJuego.getModo().equals("unaCarta")) {
                unaCarta.setBackground(new Color(235, 113, 113));
            } else {
                unaCarta.setBackground(new Color(230, 230, 230));
            }
            if (modoDeJuego.getModo().equals("tresCartas")) {
                tresCartas.setBackground(new Color(235, 113, 113));
            } else {
                tresCartas.setBackground(new Color(230, 230, 230));
            }
        }
    }
}
