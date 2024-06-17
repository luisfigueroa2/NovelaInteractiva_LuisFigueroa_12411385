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
-entrada: Scanner
-paciencia: variable int que define la pacienca (siempre empieza como 0)
-maxpac: variable int que define la paciencia maxima (si sobrepase durante el juego se acaba)
-escenas: variable ArrayList que guarda las escenas del juego (avanza con un contador)
-acto: variable int que guarda que escena se esta jugando
-rand: variable random para los parametros de la paciencia
-continuar: variable boolean que lee cuando se acaba el juego por medio de paciencia
-nombre: el nombre del usuario
-opcion: variable tipo int usada para visualizar las opciones que tiene el usuario en el JOptionPane
-fin: boolean que controla cuando el juego se acaba por medio de llegar al maximo de escenas

GLOSARIO DE METODOS:
-generarmaxpac: usa el random y dos parametros para generar la paciencia maxima de la partida
-inicio: este metodo crea las escenas agregandolas al arraylist
-iniciarvideojogo: metodo que inicia el juego pidiendole al usuario su nombre y terminando el juego al llegar al fin
-mostrarops: usa JOptionPane para mostrarle las opciones de dialogo al usuario y regresar su respuesta
-choose!: este metodo es el que tiene todos los switches que controlan como avanza el juego segun el dialogo
-gameu: este metodo recursivo es el que controla la paciencia del usuario, repitiendose hasta que se acaba la paciencia, el usuario decide no continuar o responde mal

-EL USO DE GUI ES BASTANTE BASICO EN ESTE AVANCE, CONTINUARE ESTUDIANDO COMO HACER UNA INTERFAZ COMPLETA PARA LA ENTREGA FINAL

HERRAMIENTAS USADAS EN ESTE AVANCE:
Estructuras de control: secuenciales, de decision, de repeticion.
Tres clases
Metodos que retornen y recibanenteros, reales y booleanos.
Metodos que reciban cadenas
Metodos estaticos y no estaticos
RANDOM
Constructor
Arreglos unidimensionales
Arraylist
GUI (INCOMPLETO)
TRES FORMAS DE IMPRIMIR (Use ln, sencilla y f%)
JOptionPane
Scanner
Diferentes tipos de variables (locales y de referencia)
Getters y Setters
*/
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

class NovelaInteractiva {
    static Scanner entrada = new Scanner(System.in);
    private int paciencia;
    private int maxpac;
    private ArrayList<Escena> escenas;
    private int acto;
    private boolean fin;
    
    public static void main(String[] args) {
        jogo hola = new jogo();
        hola.setTitle("sexo");
        hola.setVisible(true);
        NovelaInteractiva jogo = new NovelaInteractiva();
        jogo.iniciarvideojogo();
    }
    // Constructor que inicializa la paciencia en 0 y asigna la paciencia maxima aleatoria
    public NovelaInteractiva() {
        paciencia = 0;
        maxpac = generarmaxpac(1, 3);
        escenas = new ArrayList<>();
        acto = 0;
        fin = false;
        inicio();
    }

    private int generarmaxpac(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private void inicio() {
        escenas.add(new Escena("Ayudame a comprar leche", new String[]{"No quiero", "Vaya pue", "..."}));
        escenas.add(new Escena("Ya estan adentro de la tienda, por alguna razon el niño se quedo paralizado frente al cajero...No se mueve y hay gente en la fila esperando.", new String[]{"Tranquilo, dale el dinero", "Tan inutil eres?", "La gente espera, apurate"}));
        escenas.add(new Escena("*Nos cuenta un poco de su historia*.", new String[]{"Sos un rarito", "*escuchar*", "No quiero saberlo"}));
        //la ultima escena esta si va a llevar toda su backstory
        //la historia ya esta completa, solo ocupo ver todos los temas para seguir implementandola para la entrega final
    }

    public void iniciarvideojogo() {
        String nombre = JOptionPane.showInputDialog("Por favor, introduce tu nombre:");
        Jugador jugador = new Jugador(nombre);
        gameu();

        if (fin) {
            System.out.println("Si mama...");
        } else {
            System.out.println("Parece que " + nombre + " no me sirve... probemos algo mas...");
        }
    }
    private void gameu(){
            if (paciencia > maxpac) {
                System.out.println("Has perdido demasiada paciencia....");
                return;
            }else if (acto >= escenas.size() && paciencia <= maxpac) {
                System.out.println("Compraste la leche..?");
                fin = true;
                return;       
            }
            String opcion = mostrarops();
            choose(opcion);
            
            if (paciencia > maxpac){
                System.out.println("Has perdido demasiada paciencia....");
                return;
            }
             System.out.printf("Cuidado con la paciencia, no dejes que suba... Paciencia actual: %d/%d%n", paciencia, maxpac);
             boolean continuar = false;
                while (!continuar) {
                    System.out.print("Deseas continuar? (s/n): ");
                    char resp = entrada.next().charAt(0);
                    if (resp == 'n' || resp == 'N') {
                        return;
                    } else if (resp == 's' || resp == 'S') {
                        continuar = true;
                    } else {
                        System.out.println("Oye, tomate esto en serio, ingresa lo que te dicen");
                    }
            }//fin while continuar
            gameu();
        }//fin recursiva
        
        
    
    public String mostrarops() {
        Escena escena = escenas.get(acto);
        String[] opciones = escena.getOpciones();
        int opcion = JOptionPane.showOptionDialog(null, escena.getDialogo(),"Novela Interactiva", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        return opciones[opcion];
    }

    public void choose(String opcion) {
        Escena escena = escenas.get(acto);
        switch (opcion) {
            case "No quiero":
                System.out.println("Se supone que me ayudes.");
                paciencia++;
                break;
            case "Vaya pue":
                System.out.println("Bueno pues vamos.");
                break;
            case "...":
                System.out.println("Por favor dime algo, no te quedes callado... ayudame...");
                paciencia++;
                break;
            case "Tranquilo, dale el dinero":
                System.out.println("Ah, gracias, que haria sin ti.");
                break;
            case "Tan inutil eres?":
                System.out.println("El niño escucho tu insulto y pago, funciono pero no creo que este feliz.");
                paciencia++;
                break;
            case "La gente espera, apurate":
                System.out.println("Ah, si, ya voy.");
                break;
            case "Sos un rarito":
                System.out.println("...");
                paciencia = 99;
                break;
            case "*escuchar*":
                System.out.println("El niño sigue contando su historia.");
                break;
            case "No quiero saberlo":
                System.out.println("Perdon...");
                paciencia++;
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
        acto++;
    }
}



