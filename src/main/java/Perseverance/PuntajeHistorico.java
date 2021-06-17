package Perseverance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PuntajeHistorico extends JFrame implements ActionListener {

    private ArrayList<String> nombres;
    private ArrayList<String> puntajes;
    private Tablero tablero;

    public PuntajeHistorico(Tablero tablero){
        setLayout(null);
        setTitle("Puntajes Historicos");
        ImageIcon icono = new ImageIcon("images/icono.png");
        setIconImage(icono.getImage());
        this.tablero=tablero;

        nombres = new ArrayList<>();
        puntajes = new ArrayList<>();

        leerArchivo();

    }

    public void crearVentana(int i){
        crearLabels();
        this.setSize( 250,i);
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

    public void ventanaWin(){

        JLabel label = new JLabel("FELICITACIONES!");
        label.setFont(new Font("Berlin Sans FB", 0,16));
        label.setBounds(45,370,300,25);
        add(label);

        JLabel label2 = new JLabel("Has ganado la partida!");
        label2.setFont(new Font("Berlin Sans FB", 0,16));
        label2.setBounds(35,400,300,25);
        add(label2);

        JButton ok = new JButton("Menu");
        ok.setHorizontalTextPosition(ok.CENTER);
        ok.setVerticalTextPosition(ok.CENTER);
        ok.setBounds(30, 440, 80,40);
        ok.setBackground(new Color(230,230,230));
        ok.setFocusPainted(false);
        ok.addActionListener(this);
        ok.setFont(new Font("Berlin Sans FB", 0,16));
        add(ok);

        JButton salir = new JButton("Salir");
        salir.setHorizontalTextPosition(salir.CENTER);
        salir.setVerticalTextPosition(salir.CENTER);
        salir.setBounds(125, 440, 80,40);
        salir.setBackground(new Color(230,230,230));
        salir.setFocusPainted(false);
        salir.addActionListener(this);
        salir.setFont(new Font("Berlin Sans FB", 0,16));
        add(salir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if(boton.getText().equals("Menu")){
            tablero.dispose();
            dispose();
            new Menu().crearVentana();
        }
        else if(boton.getText().equals("Salir")){
            System.exit(0);
        }

    }
}
