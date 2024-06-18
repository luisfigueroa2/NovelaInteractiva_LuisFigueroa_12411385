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
        continuar();
    }//FIN METODO DE INICIAR EL JUEGO

    public static int generarMaxPac(int min, int max) {//METODO PARA GENERAL LA PACIENCIA
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }//FIN METODO GENERAR PACIENCIA MAXIMA

    public void inicio() {//METODO QUE AGREGA LAS ESCENAS AL ARRAY LIST DE ESCENAS
        escenas.add(new Escena("Ayudame a comprar leche", new String[]{"No quiero", "Vaya pue", "..."}));
        escenas.add(new Escena("Mi mama dijo que comprara un tipo de leche, pero no me acuerdo cual, meh, no importa agarrare esta *agarro leche entera*", new String[]{"Si no te acordas fijo no es importante", "Mandale un mensaje y preguntale, estupido", "Sos un pedazo de papo"}));
        escenas.add(new Escena("Ya estan adentro de la tienda, por alguna razon el niño se quedo paralizado frente al cajero...No se mueve y hay gente en la fila esperando.", new String[]{"Tranquilo, dale el dinero", "Tan inutil eres?", "Sos un imbecil"}));
        escenas.add(new Escena("*En el camino de regreso se escucha un fuerte sonido detras de el*", new String[]{"*no decir nada*", "CUIDADO", "*NO DIGAS NADA*"}));
        escenas.add(new Escena("Que grosero ese oso corriendo por la calle.", new String[]{"Era un camion, casi mueres", "Si, que grosero", "*Quedarte callado*"}));
        escenas.add(new Escena("*Mientras que regresan el niño aparenta estarle hablando a alguien", new String[]{"A quien le hablas?", "A quien le hablas?", "A quien le hablas?"}));
        escenas.add(new Escena("*El niño continua hablando con su papa*", new String[]{"Pero tu papa esta muerto", "Dale", "No murio hace años??"}));
        escenas.add(new Escena("Tengo sed y mi papa dijo que podia tomar de la leche, probemosla!", new String[]{"Dale pues probala", "No creo que sea buena idea", "Fondo! Fondo! Fondo!"}));
        escenas.add(new Escena("*El niño entra a su apartamento y su mama le pregunta* cOmPrAStE lA lEcHE?", new String[]{"Si mama", "Si mama", "Si mama"}));
        escenas.add(new Escena("sIrViO La mEDiCinA?¿?¿", new String[]{"Si", "Si", "Si"}));
    }//FIN METODO INICIO

    public void continuar() {//INICIO METODO PARA MOVERSE ATRAVES DEL JUEGO
        
        if (paciencia > maxpac) {//CONDICION DE SALIDA #1: PACIENCIA GAME OVER 
            JOptionPane.showMessageDialog(gui, "Has perdido demasiada paciencia....");
            JOptionPane.showMessageDialog(gui, "Parece que esta no funciono...probemos algo mas...");
            finJ();
            return;
        } else if (acto >= escenas.size() && paciencia <= maxpac) {//CONDICION DE SALIDA #2: FIN DEL JUEGO
            JOptionPane.showMessageDialog(gui, "Andate a tu cuarto...");
            
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
            System.out.println("Te gusto el juego? s/n");
            char resp = entrada.next().charAt(0);
            if (resp == 'n') {
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
            finJ();
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
        maxpac = generarMaxPac(2, 4);
        acto = 0;
        fin = false;
    }//FIN METODO RESTART
    
    public void finJ() {//METODO PARA PEDIRLE AL USUARIO SI QUIERE JUGAR OTRA VEZ
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
            continuar();
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
            case "Si no te acordas fijo no es importante":
                JOptionPane.showMessageDialog(gui, "Si fijo que si");
                break;
            case "Mandale un mensaje y preguntale, estupido":
                JOptionPane.showMessageDialog(gui, "No traje mi telefono");
                paciencia++;
                break;
            case "Sos un pedazo de papo":
                JOptionPane.showMessageDialog(gui, "Dejame en paz");
                paciencia++;
                break;
            case "Tranquilo, dale el dinero":
                JOptionPane.showMessageDialog(gui, "Ah, gracias, que haria sin ti.");
                break;
            case "Tan inutil eres?":
                JOptionPane.showMessageDialog(gui, "El niño escucho tu insulto y pago, funciono pero no creo que este feliz.");
                paciencia++;
                break;
            case "Sos un imbecil":
                JOptionPane.showMessageDialog(gui, "...");
                paciencia= 9999;
                break;
            case "*no decir nada*":
                JOptionPane.showMessageDialog(gui, "-----------------------------");
                paciencia = 99999;
                break;
            case "*NO DIGAS NADA*":
                JOptionPane.showMessageDialog(gui, "-----------------------------");
                paciencia = 99999;
                break;
            case "CUIDADO":
                JOptionPane.showMessageDialog(gui, "*El niño se mueve rapidamente de lado* Whoah");
                break;
            case "Era un camion, casi mueres.":
                JOptionPane.showMessageDialog(gui, "De que estas hablando? Claramente era un oso");
                paciencia++;
                break;
            case "Si, que grosero":
                JOptionPane.showMessageDialog(gui, "Va? hoy en dia no respetan");
                break;
            case "*Quedarte callado*":
                JOptionPane.showMessageDialog(gui, "Bueno continuemos");
                break;
            case "A quien le hablas?":
                JOptionPane.showMessageDialog(gui, "Ah, a mi papa");
                break;
            case "Pero tu papa esta muerto":
                JOptionPane.showMessageDialog(gui, "...");
                paciencia = 9999;
                break;
            case "Dale":
                JOptionPane.showMessageDialog(gui, "*Continua caminando*");
                break;
            case "No murio hace años?":
                JOptionPane.showMessageDialog(gui, "De que hablas?");
                paciencia = 9999;
                break;
            case "Dale pues probala":
                JOptionPane.showMessageDialog(gui, "----------------");
                paciencia = 9999;
                break;
            case "Fondo! Fondo! Fondo!":
                JOptionPane.showMessageDialog(gui, "----------------");
                paciencia = 9999;
                break;
            case "No creo que sea buena idea":
                JOptionPane.showMessageDialog(gui, "Estas seguro? Bueno me has ayudado hasta aqui, te hare caso");
                break;
            case "Si mama":
                JOptionPane.showMessageDialog(gui, "...");
                break;
            case "Si":
                JOptionPane.showMessageDialog(gui, "...");
                break;
            default:
                JOptionPane.showMessageDialog(gui, "Opción no válida.");
                break;
        }
        acto++;
    }//FIN METODO OPCIONES
}//FIN DEL CODIGO




