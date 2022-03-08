/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesPersonajes;

import javax.swing.JLabel;

/**
 *
 * @author David Ramos y Sergio Sánchez
 */
public abstract class Planta extends Personaje{
    
    private int coste;
    private int frecuencia;
    
    /**
     *Constructor
     * @param coste cantidad de soles que vale la planta
     * @param frecuencia intervalo de turnos donde puede realizar una accion
     * @param resistencia cantidad de daño que es capaz de soportar un personaje
     * @param daño cantidad de resistencia que elimina en cada golpe
     * @param rango distancia maxima a la que puede realizar daño
     */
    public Planta(int coste, int frecuencia, int resistencia, int daño,int rango) {
        super(resistencia, daño,rango);
        this.coste = coste;
        this.frecuencia = frecuencia;
        
    }

    /**
     *
     * @return devuelve el coste de la planta
     */
    public int getCoste() {
        return coste;
    }

    /**
     *
     * @param coste cantidad de soles que vale la planta
     */
    public void setCoste(int coste) {
        this.coste = coste;
    }

    /**
     *
     * @return devuelve la frecuencia de la planta
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     *
     * @param frecuencia intervalo de turnos donde puede realizar una accion
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    

    
        
   
    
    
}
