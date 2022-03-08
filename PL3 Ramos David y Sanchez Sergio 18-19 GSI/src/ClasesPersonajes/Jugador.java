/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesPersonajes;

/**
 *
 * @author David Ramos y Sergio Sánchez
 */
public class Jugador {
    private int soles;
    
    /**
     * Constructor
     */
    public Jugador(){
        this.soles=50;
        
    }

    /**
     *
     * @return cantidad de soles que tiene el jugador
     */
    public int getSoles() {
        return soles;
    }

    /**
     *
     * @param soles recurso utilizado para comprar plantas
     */
    public void setSoles(int soles) {
        this.soles = soles;
    }

}

