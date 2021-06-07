package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tablero extends JFrame implements Observer {

    private JButton nuevoJuego;
    private JButton menuPrincipal;
    private JButton reglas;
    private JButton estadisticas;
    private JButton unaCarta;
    private JButton tresCartas;

    private ArrayList<ArrayList<JButton>> cartasDeJuego;
    private ArrayList<JButton> escaleras;
    private JButton mazo;
    private JButton cartaUbicar;
    private JLabel cartaUbicar2;
    private JLabel cartaUbicar3;
    private Font fuente;

    private JLabel tablero;
    private JLabel nombre;
    private JLabel jugador;
    private JLabel tiempo;
    private JLabel movimientos;
    private JLabel puntuacion;
    private JLabel movimientosNum;
    private JLabel puntuacionNum;
    private JLabel tiempoNum;

    private ArrayList<PilaEnZonaDeJuego> pilasDeJuego;
    private ArrayList<PilaEnZonaDeEscalera> pilasDeEscalera;
    private ArrayList<Carta> cartasaUbicar;
    private boolean mazoVacio;
    private int puntaje;
    private int movimientosExitosos;
    private String nombreJugador;
    private ModoDeJuego modoDeJuego;
    private boolean primeraVez;
    private int segundos;
    private int minutos;
    private boolean win;


    private Juego juego;
    private Controlador controlador;

    public Tablero(Juego juego, Controlador controlador) {

        this.controlador = controlador;

        this.juego = juego;

        registrarFuente();

        escaleras = new ArrayList<>();
        cartasDeJuego = new ArrayList<>();
        primeraVez=true;
        win=false;

        puntaje = juego.getPuntacion();
        movimientosExitosos = 0;

        update();

        setLayout(null);
        setTitle("Solitario Apolo");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));


        ImageIcon tableropng = new ImageIcon("images/tablero.png");
        tablero = new JLabel(tableropng);
        tablero.setBounds(0, 0, 1280, 960);


        crearBotones();
        crearMazoyCartasUbicar();
        crearEscaleras();
        crearZonaDeJuego();

        add(tablero);

        recargarMazoyCartasUbicar();
        recargarEscalera();

    }

    @Override
    public void update() {
        pilasDeJuego = juego.getPilasJuego();
        pilasDeEscalera = juego.getPilasEscalera();
        cartasaUbicar = juego.getCartasaUbicar();
        System.out.println(cartasaUbicar.size());
        mazoVacio = juego.getMazoVacio();
        puntaje = juego.getPuntacion();
        movimientosExitosos = juego.getMovimientosExitosos();
        nombreJugador = juego.getNombre();
        modoDeJuego = juego.getModoDeJuego();
        segundos=juego.getSegundos();
        minutos= juego.getMinutos();
        win=juego.isWin();


        if(!primeraVez){
            recargarVentana();
        }
        primeraVez=false;

        if(win){
            juego.getTask().cancel();
            int confirmed = JOptionPane.showConfirmDialog(null,"¿Desea volver al menu principal?"
                    , "Felicidades, has ganado el juego!",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                new Menu().crearVentana();
                dispose();
            }else{
                System.exit(0);
            }
        }
    }

    public void recargarVentana() {

        recargarBotonesModo();
        recargarMazoyCartasUbicar();
        recargarEscalera();
        recargarZonaDeJuego();
        movimientosNum.setText(String.valueOf(movimientosExitosos));
        puntuacionNum.setText(String.valueOf(puntaje));
        tiempoNum.setText(String.format("%02d",minutos)+ " : " + String.format("%02d",segundos));

    }


    public void crearVentana() {
        this.setBounds(0, 0, 1280, 830);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void crearBotones(){
        nombre = new JLabel("Nombre: ");
        nombre.setBounds(10, 0, 200, 30);
        nombre.setForeground(new Color(0, 0, 0));
        nombre.setFont(fuente);
        add(nombre);

        tiempo = new JLabel("Tiempo: ");
        tiempo.setBounds(10, 30, 100, 30);
        tiempo.setForeground(new Color(0, 0, 0));
        tiempo.setFont(fuente);
        add(tiempo);

        tiempoNum = new JLabel(String.format("%02d",minutos)+ " : " + String.format("%02d",segundos));
        tiempoNum.setBounds(110, 30, 100, 30);
        tiempoNum.setForeground(new Color(0, 0, 0));
        tiempoNum.setFont(fuente);
        add(tiempoNum);

        movimientos = new JLabel("Movimientos Exitosos: ");
        movimientos.setBounds(10, 60, 200, 30);
        movimientos.setForeground(new Color(0, 0, 0));
        movimientos.setFont(fuente);
        add(movimientos);

        movimientosNum = new JLabel(String.valueOf(movimientosExitosos));
        movimientosNum.setBounds(210, 60, 100, 30);
        movimientosNum.setForeground(new Color(0, 0, 0));
        movimientosNum.setFont(fuente);
        add(movimientosNum);

        puntuacion = new JLabel("Puntuación: ");
        puntuacion.setBounds(410, 60, 100, 30);
        puntuacion.setForeground(new Color(0, 0, 0));
        puntuacion.setFont(fuente);
        add(puntuacion);

        puntuacionNum = new JLabel(String.valueOf(juego.getPuntacion()));
        puntuacionNum.setBounds(520, 60, 100, 30);
        puntuacionNum.setForeground(new Color(0, 0, 0));
        puntuacionNum.setFont(fuente);
        add(puntuacionNum);

        jugador = new JLabel(nombreJugador);
        jugador.setBounds(100, 0, 200, 30);
        jugador.setForeground(new Color(0, 0, 0));
        jugador.setFont(fuente);
        add(jugador);

        nuevoJuego = new JButton("Nuevo Juego");
        nuevoJuego.setBounds(750, 10, 150, 35);
        nuevoJuego.setFont(fuente);
        nuevoJuego.setBackground(new Color(230, 230, 230));
        nuevoJuego.setFocusPainted(false);
        nuevoJuego.setName("nuevoJuegoTablero");
        nuevoJuego.addActionListener(controlador);
        add(nuevoJuego);

        menuPrincipal = new JButton("Menu");
        menuPrincipal.setBounds(920, 10, 150, 35);
        menuPrincipal.setFont(fuente);
        menuPrincipal.setBackground(new Color(230, 230, 230));
        menuPrincipal.setFocusPainted(false);
        menuPrincipal.setName("menuPrincipalTablero");
        menuPrincipal.addActionListener(controlador);
        add(menuPrincipal);

        reglas = new JButton("Reglas");
        reglas.setBounds(750, 52, 150, 35);
        reglas.setFont(fuente);
        reglas.setBackground(new Color(230, 230, 230));
        reglas.setFocusPainted(false);
        reglas.setName("reglasTablero");
        reglas.addActionListener(controlador);
        add(reglas);

        estadisticas = new JButton("Estadisticas");
        estadisticas.setBounds(920, 52, 150, 35);
        estadisticas.setFont(fuente);
        estadisticas.setBackground(new Color(230, 230, 230));
        estadisticas.setFocusPainted(false);
        estadisticas.setName("estadisticasTablero");
        estadisticas.addActionListener(controlador);
        add(estadisticas);

        unaCarta = new JButton("Una carta");
        unaCarta.setBounds(1090, 10, 150, 35);
        unaCarta.setFont(fuente);
        if (modoDeJuego.getModo().equals("unaCarta")) {
            unaCarta.setBackground(new Color(235, 113, 113));
        } else {
            unaCarta.setBackground(new Color(230, 230, 230));
        }
        unaCarta.setFocusPainted(false);
        unaCarta.setName("unaCartaTablero");
        unaCarta.addActionListener(controlador);
        add(unaCarta);

        tresCartas = new JButton("Tres cartas");
        tresCartas.setBounds(1090, 52, 150, 35);
        tresCartas.setFont(fuente);
        if (modoDeJuego.getModo().equals("tresCartas")) {
            tresCartas.setBackground(new Color(235, 113, 113));
        } else {
            tresCartas.setBackground(new Color(230, 230, 230));
        }
        tresCartas.setFocusPainted(false);
        tresCartas.setName("tresCartasTablero");
        tresCartas.addActionListener(controlador);
        add(tresCartas);
    }

    public void crearMazoyCartasUbicar(){
        mazo = new JButton();
        mazo.setBounds(90, 103, 98, 117);
        mazo.setIcon(new ImageIcon("images/reversocarta.png"));
        mazo.setBorderPainted(false);
        mazo.setContentAreaFilled(false);
        mazo.setName("mazoTablero");
        mazo.addActionListener(controlador);
        add(mazo);

        cartaUbicar = new JButton();
        cartaUbicar.setBounds(300, 103, 98, 117);
        cartaUbicar.setBorderPainted(false);
        cartaUbicar.setContentAreaFilled(false);
        cartaUbicar.setName("cartaUbicar");
        cartaUbicar.addActionListener(controlador);
        cartaUbicar.setVisible(false);
        add(cartaUbicar);

        cartaUbicar3 = new JLabel();
        cartaUbicar3.setBounds(270, 103, 98, 117);
        cartaUbicar3.setVisible(false);
        add(cartaUbicar3);

        cartaUbicar2 = new JLabel();
        cartaUbicar2.setBounds(240, 103, 98, 117);
        cartaUbicar2.setVisible(false);
        add(cartaUbicar2);

    }

    public void crearEscaleras(){
        for (int i = 0; i < 4; i++) {
            JButton boton = new JButton();
            boton.setBounds(590 + (168 * i), 108, 98, 117);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setName("escalera" + i);
            boton.addActionListener(controlador);
            add(boton);
            escaleras.add(boton);
        }
    }

    public void crearZonaDeJuego(){
        for (int i = 0; i < pilasDeJuego.size(); i++) {
            ArrayList<JButton> cartas = new ArrayList<>();
            for (int j = 18; j >= 0; j--) {
                JButton boton = new JButton();
                boton.setBounds(89 + (168 * i), 245 + (22 * j), 98, 117);
                boton.setIcon(new ImageIcon("images/reversocarta.png"));
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                boton.setName("juego" + i + j);
                boton.addActionListener(controlador);
                add(boton);
                cartas.add(boton);
            }
            cartasDeJuego.add(cartas);
        }
    }

    public void registrarFuente() {
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font\\TitilliumWeb.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void recargarBotonesModo() {
        if (!(unaCarta == null || tresCartas == null)) {
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

    public void recargarMazoyCartasUbicar() {

        if (mazo != null) {
            if (!mazoVacio) {
                mazo.setIcon(new ImageIcon("images/reversocarta.png"));
            } else {
                mazo.setIcon(new ImageIcon("images/transparente.png"));
            }
        }

        if (cartasaUbicar!=null){
            if(cartasaUbicar.size()>=1){
                cartaUbicar.setIcon(cartasaUbicar.get(cartasaUbicar.size()-1).getImagen());
                cartaUbicar.setVisible(true);
            }else{
                cartaUbicar.setVisible(false);
            }
            if(cartasaUbicar.size()>=2){
                cartaUbicar3.setIcon(cartasaUbicar.get(cartasaUbicar.size()-2).getImagen());
                cartaUbicar3.setVisible(true);
            }else{
                cartaUbicar3.setVisible(false);
            }
            if(cartasaUbicar.size()>=3){
                cartaUbicar2.setIcon(cartasaUbicar.get(cartasaUbicar.size()-3).getImagen());
                cartaUbicar2.setVisible(true);
            }else{
                cartaUbicar2.setVisible(false);
            }
        }

    }

    public void recargarEscalera() {
        if (!escaleras.isEmpty()){
            for (int i = 0; i < 4; i++) {
                if (pilasDeEscalera.get(i).getPila().isEmpty()) {
                    escaleras.get(i).setIcon(new ImageIcon("images/transparenteEscalera.png"));
                } else {
                    escaleras.get(i).setIcon(pilasDeEscalera.get(i).getUltimaCarta().getImagen());
                }
            }
        }
    }

    public void recargarZonaDeJuego() {
        for (int i = 0; i < 7; i++) { //pila por pila
            for (int j = 0; j < 19-pilasDeJuego.get(i).getPila().size(); j++) {
                cartasDeJuego.get(i).get(j).setVisible(false);
            }
            for (int j = 19-pilasDeJuego.get(i).getPila().size(); j < 19; j++) {
                cartasDeJuego.get(i).get(j).setVisible(true);
                cartasDeJuego.get(i).get(j).setIcon(pilasDeJuego.get(i).getPila().get(18-j).getImagen());
            }
            if(pilasDeJuego.get(i).getPila().isEmpty()){
                cartasDeJuego.get(i).get(18).setVisible(true);
                cartasDeJuego.get(i).get(18).setIcon(new ImageIcon("images/transparenteEscalera.png"));
            }
        }

    }

    /*
    for (int i = 0; i < pilasDeJuego.size(); i++) {
        ArrayList<JButton> visibles = new ArrayList<>();
        ArrayList<JLabel> noVisibles = new ArrayList<>();
        if(pilasDeJuego.get(i).getPila().isEmpty()) {
            JButton cartaVisible = new JButton();
            cartaVisible.setBounds(89 + (168 * i), 245 , 98, 117);
            cartaVisible.setIcon(new ImageIcon("images/transparenteEscalera.png"));
            cartaVisible.setBorderPainted(false);
            cartaVisible.setContentAreaFilled(false);
            cartaVisible.setName("vacia" + i);
            cartaVisible.addActionListener(controlador);
            add(cartaVisible);
            visibles.add(cartaVisible);
        }else{
            for (int j = pilasDeJuego.get(i).getPila().size()-1; j >= 0; j--) {
                if(pilasDeJuego.get(i).getPila().get(j).isVisible()) {
                    JButton cartaVisible = new JButton();
                    cartaVisible.setBounds(89 + (168 * i), 245 + (22 * j), 98, 117);
                    cartaVisible.setIcon(pilasDeJuego.get(i).getPila().get(j).getImagen());
                    cartaVisible.setBorderPainted(false);
                    cartaVisible.setContentAreaFilled(false);
                    cartaVisible.setName("juego" + i + j );
                    cartaVisible.addActionListener(controlador);
                    add(cartaVisible);
                    visibles.add(cartaVisible);
                }else{
                    JLabel cartaNoVisible = new JLabel();
                    cartaNoVisible.setBounds(89 + (168 * i), 245 + (22 * j), 98, 117);
                    cartaNoVisible.setIcon(pilasDeJuego.get(i).getPila().get(j).getImagen());
                    add(cartaNoVisible);
                    noVisibles.add(cartaNoVisible);
                }
            }
        }
        cartasVisibles.add(visibles);
        cartasNoVisibles.add(noVisibles);
    }
    revalidate();
    repaint();

    */

}
