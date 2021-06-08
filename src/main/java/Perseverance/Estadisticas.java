package Perseverance;

import javax.swing.*;
import java.awt.*;

public class Estadisticas extends JFrame implements Observer {

    private JLabel movExitososText;
    private JLabel puntTiempoText;
    private JLabel puntMovText;
    private JLabel puntTotalText;
    private JLabel tipo;
    private JLabel puntaje;
    private JLabel cantidad;
    private JLabel tiempo;
    private JLabel movNumText;

    private JLabel movExitosos;
    private JLabel puntTiempo;
    private JLabel puntMov;
    private JLabel puntTotal;
    private JLabel cartas;
    private JLabel cartasMazoTxt;
    private JLabel cartasEscTxt;
    private JLabel cartasJuegoTxt;
    private JLabel cantCartas;
    private JLabel cartasMazoNum;
    private JLabel cartasEscNum;
    private JLabel cartasJuegoNum;

    private int cantMov;
    private int puntTiempoNum;
    private int puntMovNum;
    private int puntTotalNum;
    private int movNumEsc;
    private int segundos;
    private int minutos;
    private int cartasMazo;
    private int cartasEsc;
    private int cartasJuego;

    private Font fuente;

    private Juego juego;

    public Estadisticas(Juego juego){

        this.juego = juego;
        setLayout(null);
        setTitle("Estadisticas");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        fuente=new Font("Berlin Sans FB", 0,16);

        cantMov = juego.getMovimientosExitosos();
        puntTiempoNum = juego.getPuntacionTiempo();
        puntMovNum = juego.getPuntacionMovimientos();
        puntTotalNum = juego.getPuntacion();
        movNumEsc=juego.getMovimientosaEscaleras();
        segundos=juego.getSegundos();
        minutos=juego.getMinutos();
        cartasMazo=juego.getCartasMazo();
        cartasEsc=juego.getCartasEscalera();
        cartasJuego=juego.getCartasJuego();

        movExitososText = new JLabel("Cantidad de movimientos exitosos: ");
        movExitososText.setFont(fuente);
        movExitososText.setBounds(25,25,300,30);
        add(movExitososText);

        movExitosos = new JLabel(String.valueOf(cantMov));
        movExitosos.setFont(fuente);
        movExitosos.setBounds(300,26,100,30);
        add(movExitosos);

        tipo = new JLabel("Tipo de puntaje");
        tipo.setFont(fuente);
        tipo.setBounds(25,75,190,30);
        tipo.setOpaque(true);
        tipo.setBackground(Color.LIGHT_GRAY);
        add(tipo);

        cantidad = new JLabel("Cantidad");
        cantidad.setFont(fuente);
        cantidad.setBounds(220,75,190,30);
        cantidad.setOpaque(true);
        cantidad.setBackground(Color.LIGHT_GRAY);
        add(cantidad);

        puntaje = new JLabel("Puntaje");
        puntaje.setFont(fuente);
        puntaje.setBounds(415,75,190,30);
        puntaje.setOpaque(true);
        puntaje.setBackground(Color.LIGHT_GRAY);
        add(puntaje);

        puntTiempoText = new JLabel("Tiempo ");
        puntTiempoText.setFont(fuente);
        puntTiempoText.setBounds(25,105,190,30);
        add(puntTiempoText);

        puntMovText = new JLabel("Movimientos a escalera ");
        puntMovText.setFont(fuente);
        puntMovText.setBounds(25,135,190,30);
        add(puntMovText);

        puntTotalText = new JLabel("Puntaje total: ");
        puntTotalText.setFont(fuente);
        puntTotalText.setBounds(25,200,190,30);
        add(puntTotalText);

        puntTiempo = new JLabel(String.valueOf(puntTiempoNum));
        puntTiempo.setFont(fuente);
        puntTiempo.setBounds(415,105,190,30);
        add(puntTiempo);

        tiempo = new JLabel(String.format("%02d",minutos)+ " : " + String.format("%02d",segundos));
        tiempo.setFont(fuente);
        tiempo.setBounds(220,105,190,30);
        add(tiempo);

        puntMov = new JLabel(String.valueOf(puntMovNum));
        puntMov.setFont(fuente);
        puntMov.setBounds(415,135,190,30);
        add(puntMov);

        movNumText = new JLabel(String.valueOf(movNumEsc));
        movNumText.setFont(fuente);
        movNumText.setBounds(220,135,190,30);
        add(movNumText);

        puntTotal = new JLabel(String.valueOf(puntTotalNum));
        puntTotal.setFont(fuente);
        puntTotal.setBounds(150,200,190,30);
        add(puntTotal);

        cartas = new JLabel("Cartas");
        cartas.setFont(fuente);
        cartas.setBounds(25,250,190,30);
        cartas.setOpaque(true);
        cartas.setBackground(Color.LIGHT_GRAY);
        add(cartas);

        cantCartas = new JLabel("Cantidad");
        cantCartas.setFont(fuente);
        cantCartas.setBounds(220,250,190,30);
        cantCartas.setOpaque(true);
        cantCartas.setBackground(Color.LIGHT_GRAY);
        add(cantCartas);

        cartasMazoTxt = new JLabel("En mazo");
        cartasMazoTxt.setFont(fuente);
        cartasMazoTxt.setBounds(25,285,300,30);
        add(cartasMazoTxt);

        cartasMazoNum = new JLabel(String.valueOf(cartasMazo));
        cartasMazoNum.setFont(fuente);
        cartasMazoNum.setBounds(220,285,300,30);
        add(cartasMazoNum);

        cartasJuegoTxt = new JLabel( "En pilas de juego");
        cartasJuegoTxt.setFont(fuente);
        cartasJuegoTxt.setBounds(25,320,300,30);
        add(cartasJuegoTxt);

        cartasJuegoNum = new JLabel( String.valueOf(cartasJuego));
        cartasJuegoNum.setFont(fuente);
        cartasJuegoNum.setBounds(220,320,300,30);
        add(cartasJuegoNum);

        cartasEscTxt = new JLabel("En pilas de escalera");
        cartasEscTxt.setFont(fuente);
        cartasEscTxt.setBounds(25,355,300,30);
        add(cartasEscTxt);

        cartasEscNum = new JLabel(String.valueOf(cartasEsc));
        cartasEscNum.setFont(fuente);
        cartasEscNum.setBounds(220,355,300,30);
        add(cartasEscNum);

    }

    public JLabel getPuntTiempo() {
        return puntTiempo;
    }

    public JLabel getPuntMov() {
        return puntMov;
    }

    public int getPuntTiempoNum() {
        return puntTiempoNum;
    }

    public int getPuntMovNum() {
        return puntMovNum;
    }

    @Override
    public void update() {
        cantMov = juego.getMovimientosExitosos();
        puntTiempoNum = juego.getPuntacionTiempo();
        puntMovNum = juego.getPuntacionMovimientos();
        puntTotalNum = juego.getPuntacion();
        movNumEsc=juego.getMovimientosaEscaleras();
        segundos=juego.getSegundos();
        minutos=juego.getMinutos();
        cartasMazo=juego.getCartasMazo();
        cartasEsc=juego.getCartasEscalera();
        cartasJuego=juego.getCartasJuego();

        movExitosos.setText(String.valueOf(cantMov));
        puntTiempo.setText(String.valueOf(puntTiempoNum));
        puntMov.setText(String.valueOf(puntMovNum));
        puntTotal.setText(String.valueOf(puntTotalNum));
        tiempo.setText(String.format("%02d",minutos)+ " : " + String.format("%02d",segundos));
        movNumText.setText(String.valueOf(movNumEsc));
        cartasEscNum.setText(String.valueOf(cartasEsc));
        cartasMazoNum.setText(String.valueOf(cartasMazo));
        cartasJuegoNum.setText(String.valueOf(cartasJuego));

    }

    public void crearVentana(){
        this.setSize( 640,480);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

}
