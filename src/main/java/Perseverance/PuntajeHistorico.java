package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PuntajeHistorico extends JFrame {

    private ArrayList<String> nombres;
    private ArrayList<String> puntajes;
    private ArrayList<JLabel> nombresLabel;
    private ArrayList<JLabel> puntajesLabel;
    private Font fuente;

    public PuntajeHistorico(){
        setLayout(null);
        setTitle("Puntajes Historicos");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());

        nombres = new ArrayList<>();
        puntajes = new ArrayList<>();

        /*area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setSize(800,600);
        registrarFuente();
        area.setFont(fuente);
        area.setBackground(new Color(255,255,255));
        add(area);
        leerArchivo();*/

        leerArchivo();
        for (int i = 0; i < 10; i++) {
            System.out.println(nombres.get(i));
            System.out.println(puntajes.get(i));
        }

        crearLabels();

    }

    public void crearVentana(){
        this.setSize( 250,430);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void leerArchivo() {
        File archivo = new File("puntajes.txt");
        String texto="";
        int i = 0;
        try {
            Scanner scan = new Scanner(archivo);
            while (scan.hasNextLine()){
                texto=scan.nextLine();
                if(i%2 == 0){
                    nombres.add(texto);
                }
                else{
                    puntajes.add(texto);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void crearLabels(){

        JLabel nombre = new JLabel("  Jugador");
        nombre.setFont(new Font("Berlin Sans FB", 0,16));
        nombre.setBounds(45,15,68,25);
        nombre.setOpaque(true);
        nombre.setBackground(Color.LIGHT_GRAY);
        add(nombre);

        JLabel puntos = new JLabel("  Puntaje");
        puntos.setFont(new Font("Berlin Sans FB", 0,16));
        puntos.setBounds(145,15,68,25);
        puntos.setOpaque(true);
        puntos.setBackground(Color.LIGHT_GRAY);
        add(puntos);


        for (int i = 0; i < nombres.size(); i++) {
            JLabel label = new JLabel(nombres.get(i));
            label.setFont(new Font("Berlin Sans FB", 0,16));
            label.setBounds(50,60+i*30,150,25);
            add(label);
        }
        for (int i = 0; i < puntajes.size(); i++) {
            JLabel label = new JLabel(puntajes.get(i));
            label.setFont(new Font("Berlin Sans FB", 0,16));
            label.setBounds(150,60+i*30,150,25);
            add(label);
        }

    }


}
