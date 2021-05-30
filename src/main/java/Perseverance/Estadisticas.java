package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Estadisticas extends JFrame implements Observer {

    private JLabel movExitososText;
    private JLabel puntTiempoText;
    private JLabel puntMovText;
    private JLabel puntTotalText;

    private JLabel movExitosos;
    private JLabel puntTiempo;
    private JLabel puntMov;
    private JLabel puntTotal;

    private int cantMov;
    private int puntTiempoNum;
    private int puntMovNum;
    private int puntTotalNum;

    private Font fuente;

    private Juego juego;

    public Estadisticas(Juego juego){

        this.juego = juego;
        setLayout(null);
        setTitle("Estadisticas");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        registrarFuente();

        cantMov = juego.getMovimientosExitosos();
        puntTiempoNum = juego.getPuntacionTiempo();
        puntMovNum = juego.getPuntacionMovimientos();
        puntTotalNum = juego.getPuntacion();

        movExitososText = new JLabel("Cantidad de movimientos exitosos: ");
        movExitososText.setFont(fuente);
        movExitososText.setBounds(25,25,300,30);
        add(movExitososText);

        puntTiempoText = new JLabel("Puntaje por tiempo: ");
        puntTiempoText.setFont(fuente);
        puntTiempoText.setBounds(25,75,250,30);
        add(puntTiempoText);

        puntMovText = new JLabel("Puntaje por movimientos: ");
        puntMovText.setFont(fuente);
        puntMovText.setBounds(25,105,250,30);
        add(puntMovText);

        puntTotalText = new JLabel("Puntaje total: ");
        puntTotalText.setFont(fuente);
        puntTotalText.setBounds(25,135,250,30);
        add(puntTotalText);

        movExitosos = new JLabel(String.valueOf(cantMov));
        movExitosos.setFont(fuente);
        movExitosos.setBounds(300,26,100,30);
        add(movExitosos);

        puntTiempo = new JLabel(String.valueOf(puntTiempoNum));
        puntTiempo.setFont(fuente);
        puntTiempo.setBounds(300,76,100,30);
        add(puntTiempo);

        puntMov = new JLabel(String.valueOf(puntMovNum));
        puntMov.setFont(fuente);
        puntMov.setBounds(300,105,100,30);
        add(puntMov);

        puntTotal = new JLabel(String.valueOf(puntTotalNum));
        puntTotal.setFont(fuente);
        puntTotal.setBounds(300,135,100,30);
        add(puntTotal);



    }



    @Override
    public void update() {
        cantMov = juego.getMovimientosExitosos();
        puntTiempoNum = juego.getPuntacionTiempo();
        puntMovNum = juego.getPuntacionMovimientos();
        puntTotalNum = juego.getPuntacion();

        movExitosos.setText(String.valueOf(cantMov));
        puntTiempo.setText(String.valueOf(puntTiempoNum));
        puntMov.setText(String.valueOf(puntMovNum));
        puntTotal.setText(String.valueOf(puntTotalNum));

    }

    public void crearVentana(){
        this.setSize( 640,480);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void registrarFuente(){
        try {
            //create the font to use. Specify the size!
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("font\\TitilliumWeb.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(fuente);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
