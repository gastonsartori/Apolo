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


    private Juego juego;

    public Tablero(Juego juego){

        this.juego=juego;

        update();

        setLayout(null);
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        fuente = new Font("font/TitilliumWeb.ttf", Font.TRUETYPE_FONT, 20);

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
        jugador.setBounds(150,0,200, 30);
        jugador.setForeground(new Color(0,0,0));
        jugador.setFont(fuente);
        add(jugador);



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
