package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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

    }

    public void crearVentana(){
        crearLabels();
        this.setSize( 250,430);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void leerArchivo() {
        File archivo = new File("puntajes.txt");
        String texto="";
        int x = 0;
        try {
            Scanner scan = new Scanner(archivo);
            while (scan.hasNextLine()){
                texto=scan.nextLine();
                if(x%2 == 0){
                    nombres.add(texto);
                }
                else{
                    puntajes.add(texto);
                }
                x++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < puntajes.size()-1; i++) {
            for (int j = 0; j < puntajes.size()-i-1; j++) {
                if(Integer.parseInt(puntajes.get(j+1)) > Integer.parseInt(puntajes.get(j))){
                    String aux = puntajes.get(j+1);
                    puntajes.add(j+1,puntajes.get(j));
                    puntajes.remove(j+2);
                    puntajes.add(j,aux);
                    puntajes.remove(j+1);
                }
            }
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

    public void guardarPuntaje(String nombre, int puntaje){
        for (int i = 0; i < puntajes.size(); i++) {
            if(Integer.parseInt(puntajes.get(i)) < puntaje){
                puntajes.add(i,Integer.toString(puntaje));
                puntajes.remove(10);
                nombres.add(i,nombre);
                nombres.remove(10);
                guardarPuntajeArchivo();
                return;
            }
        }
    }

    public void guardarPuntajeArchivo(){
        FileWriter fw = null;
        try {
            fw = new FileWriter("puntajes.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();

        for (int i = 0; i < 10; i++) {
            System.out.println("escribo");
                pw.println(nombres.get(i));
                pw.println(puntajes.get(i));
        }
        pw.close();
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
