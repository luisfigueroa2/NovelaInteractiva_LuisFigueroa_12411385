/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novelainteractiva;

/**
 *
 * @author luisf
 */
    class Escena {
    private String dialogo;
    private String[] opciones;

    public Escena(String dialogo, String[] opciones) {
        this.dialogo = dialogo;
        this.opciones = opciones;
    }

    public String getDialogo() {
        return dialogo;
    }

    public String[] getOpciones() {
        return opciones;
    }

}

