/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package novelainteractiva;
/**
 *
 * @author luisf
 */
/*GLOSARIO DE VARIABLES:

GLOSARIO DE METODOS:


*/
import java.awt.*; //IMPORT PARA GUI
import java.awt.event.*;//IMPORT PARA USAR ACTION LISTENER
import javax.swing.*; //IMPORT PARA EL GUI (FRAME, PANELES, LABELS, BOTONES, SWING.UTILITIES)
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class NovelaInteractiva {//INICIALIZA LOS VARIABLES IMPORTANTES
    static Scanner entrada = new Scanner(System.in);
    private int paciencia;
    private int maxpac;
    private ArrayList<Escena> escenas;
    private int acto;
    private boolean fin;
    private Jugador jugador;
    private JFrame gui;
    private JLabel dialogoLabel;
    private JButton[] opcionBoton;
    private JLabel pacienciaLabel;
    
    
    public static void main(String[] args) {//METODO MAIN
        String nombre = JOptionPane.showInputDialog("Por favor, introduce tu nombre:");
        Jugador jugador = new Jugador(nombre);
        SwingUtilities.invokeLater(() -> new NovelaInteractiva().iniciarvideojogo(jugador));
    }//FIN MAIN

    public NovelaInteractiva() {//INICIO CREACION GUI
        escenas = new ArrayList<>();
        //CREACION DE LA WINDOW FRAME
        gui = new JFrame("Novela Interactiva");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//POR SI EL USUARIO CIERRA LA VENTANA
        gui.setSize(400, 300);
        
        //CREACION DEL LABEL DE DIALOGO
        dialogoLabel = new JLabel(" ");
        gui.add(dialogoLabel, BorderLayout.NORTH);
        
        //CREACION DEL PANEL DE OPCIONES
        JPanel opcionesPanel = new JPanel();
        
        //CREACION DE LOS BOTONES DE OPCIONES
        opcionesPanel.setLayout(new GridLayout(3, 1));
        opcionBoton = new JButton[3];
        for (int i = 0; i < 3; i++) {
            opcionBoton[i] = new JButton();
            opcionBoton[i].addActionListener(new boton());
            opcionesPanel.add(opcionBoton[i]);
        }
        gui.add(opcionesPanel, BorderLayout.CENTER);
        //FIN CREACION DE OPCIONES
        
        //CREACION DEL LABEL DE PACIENCIA
        pacienciaLabel = new JLabel("Paciencia: 0");
        gui.add(pacienciaLabel, BorderLayout.SOUTH);
        gui.setVisible(true);
        inicio();//YA SE INICIA EL JUEGO CON TODO SU GUI
    }

    public void iniciarvideojogo(Jugador jugador) {//METODO PARA INICIAR EL JUEGO
        this.jugador = jugador;
        reiniciarJuego();
        actualizarEscena();
    }//FIN METODO DE INICIAR EL JUEGO

    public static int generarMaxPac(int min, int max) {//METODO PARA GENERAL LA PACIENCIA
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }//FIN METODO GENERAR PACIENCIA MAXIMA

    public void inicio() {//METODO QUE AGREGA LAS ESCENAS AL ARRAY LIST DE ESCENAS
        escenas.add(new Escena("Ayudame a comprar leche", new String[]{"No quiero", "Vaya pue", "..."}));
        escenas.add(new Escena("Ya estan adentro de la tienda, por alguna razon el niño se quedo paralizado frente al cajero...No se mueve y hay gente en la fila esperando.", new String[]{"Tranquilo, dale el dinero", "Tan inutil eres?", "La gente espera, apurate"}));
        escenas.add(new Escena("*Nos cuenta un poco de su historia*.", new String[]{"Sos un rarito", "*escuchar*", "No quiero saberlo"}));
        escenas.add(new Escena("Ayudame a comprar leche", new String[]{"No quiero", "Vaya pue", "..."}));
        escenas.add(new Escena("Ya estan adentro de la tienda, por alguna razon el niño se quedo paralizado frente al cajero...No se mueve y hay gente en la fila esperando.", new String[]{"Tranquilo, dale el dinero", "Tan inutil eres?", "La gente espera, apurate"}));
        escenas.add(new Escena("*Nos cuenta un poco de su historia*.", new String[]{"Sos un rarito", "*escuchar*", "No quiero saberlo"}));
    }//FIN METODO INICIO

    public void actualizarEscena() {//INICIO METODO PARA MOVERSE ATRAVES DEL JUEGO
        
        if (paciencia > maxpac) {//CONDICION DE SALIDA #1: PACIENCIA GAME OVER 
            JOptionPane.showMessageDialog(gui, "Has perdido demasiada paciencia....");
            JOptionPane.showMessageDialog(gui, "Parece que no me sirves...probemos algo mas...");
            finJuego();
            return;
        } else if (acto >= escenas.size() && paciencia <= maxpac) {//CONDICION DE SALIDA #2: FIN DEL JUEGO
            JOptionPane.showMessageDialog(gui, "Compraste la leche..?");
            
                    //MATRIZ DE CARITA FELIZ YIPPEEEE
                    int row = 7;
                    int col = 7;
                    char[][] smiley = new char[row][col];
                     for (int i = 0; i < row; i++) {
                     for (int j = 0; j < col; j++) {
                        smiley[i][j] = ' ';
                    }
                }

                    smiley[2][2] = 'O';
                    smiley[2][4] = 'O';
                    smiley[3][1] = '-';
                    smiley[4][2] = '-';
                    smiley[4][3] = '-';
                    smiley[4][4] = '-';
                    smiley[3][5] = '-';

                for (int i = 1; i < col - 1; i++) {
                    smiley[0][i] = '*';
                    smiley[row - 1][i] = '*';
                }
                for (int i = 1; i < row - 1; i++) {
                    smiley[i][0] = '*';
                    smiley[i][col - 1] = '*';
                }

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        System.out.print(smiley[i][j] + " ");
                    }
                System.out.println();
                }
                
            System.out.println("Gracias por jugar " + jugador.toString());
            System.out.println("---------------------------");
            System.out.printf("Terminaste el juego con una cantidad de paciencia de %d/%d%n", paciencia, maxpac);
            if (paciencia == maxpac) {
                System.out.println("A nada va? se mas amable a la proxima...");
            }
            System.out.println("---------------------------");
            System.out.println("Te gusto el juego?");
            String resp = entrada.next();
            if (resp.equalsIgnoreCase("no")) {
                //MATRIZ CARITA TRISTE :(
                for (int i = 0; i < row; i++) {
                     for (int j = 0; j < col; j++) {
                        smiley[i][j] = ' ';
                    }
                }

                    smiley[2][2] = 'O';
                    smiley[2][4] = 'O';
                    smiley[5][1] = '-';
                    smiley[4][2] = '-';
                    smiley[4][3] = '-';
                    smiley[4][4] = '-';
                    smiley[5][5] = '-';

                for (int i = 1; i < col - 1; i++) {
                    smiley[0][i] = '*';
                    smiley[row - 1][i] = '*';
                }
                for (int i = 1; i < row - 1; i++) {
                    smiley[i][0] = '*';
                    smiley[i][col - 1] = '*';
                }

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        System.out.print(smiley[i][j] + " ");
                    }
                System.out.println();
                }
            }
            finJuego();
            return;
        }//FIN DEL JUEGO

        Escena escena = escenas.get(acto);
        dialogoLabel.setText(escena.getDialogo());

        String[] opciones = escena.getOpciones();
        for (int i = 0; i < opcionBoton.length; i++) {
            if (i < opciones.length) {
                opcionBoton[i].setText(opciones[i]);
                opcionBoton[i].setVisible(true);
            } else {
                opcionBoton[i].setVisible(false);
            }
        }

        pacienciaLabel.setText("Paciencia: " + paciencia + "/" + maxpac);
    }
    
    public void reiniciarJuego() {//METODO PARA INICIALIZAR LOS VARIABLES USADOS DURANTE EL JUEGO Y REINICIARLOS AL TERMINAR
        paciencia = 0;
        maxpac = generarMaxPac(1, 3);
        acto = 0;
        fin = false;
    }//FIN METODO RESTART
    
    public void finJuego() {//METODO PARA PEDIRLE AL USUARIO SI QUIERE JUGAR OTRA VEZ
        fin = true;
        gui.dispose();//AL LLEGAR AQUI EL GUI SE CIERRA
        System.out.println("Deseas jugar otra vez? si/no");
        String resp2 = entrada.next();
        if (resp2.equalsIgnoreCase("si")) {
            SwingUtilities.invokeLater(() -> new NovelaInteractiva().iniciarvideojogo(jugador));
        } else {
            System.exit(0);
        }
    }//FIN METODO JUEGO
    
    //ESTA CLASE ES LA QUE HACE QUE LOS BOTONES HAGAN SUS ACCIONES
    private class boton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            choose(source.getText());
            actualizarEscena();
        }
    }//FIN CLASE BOTON
    
  
    public void choose(String opcion) { //METODO DE TODAS LAS OPCIONES
        Escena escena = escenas.get(acto);
        switch (opcion) {
            case "No quiero":
                JOptionPane.showMessageDialog(gui, "Se supone que me ayudes.");
                paciencia++;
                break;
            case "Vaya pue":
                JOptionPane.showMessageDialog(gui, "Bueno pues vamos.");
                break;
            case "...":
                JOptionPane.showMessageDialog(gui, "Por favor dime algo, no te quedes callado... ayudame...");
                paciencia++;
                break;
            case "Tranquilo, dale el dinero":
                JOptionPane.showMessageDialog(gui, "Ah, gracias, que haria sin ti.");
                break;
            case "Tan inutil eres?":
                JOptionPane.showMessageDialog(gui, "El niño escucho tu insulto y pago, funciono pero no creo que este feliz.");
                paciencia++;
                break;
            case "La gente espera, apurate":
                JOptionPane.showMessageDialog(gui, "Ah, si, ya voy.");
                break;
            case "Sos un rarito":
                JOptionPane.showMessageDialog(gui, "...");
                paciencia = 99;
                break;
            case "*escuchar*":
                JOptionPane.showMessageDialog(gui, "El niño sigue contando su historia.");
                break;
            case "No quiero saberlo":
                JOptionPane.showMessageDialog(gui, "Perdon...");
                paciencia++;
                break;
            default:
                JOptionPane.showMessageDialog(gui, "Opción no válida.");
                break;
        }
        acto++;
    }//FIN METODO OPCIONES
}




