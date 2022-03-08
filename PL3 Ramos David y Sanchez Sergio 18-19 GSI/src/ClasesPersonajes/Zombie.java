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
public abstract class Zombie extends Personaje{
    private int frecuenciaMov;

    /**
     *Constructor
     * @param frecuenciaMov intervalo de turnos donde puede moverse
     * @param resistencia cantidad de daño que es capaz de soportar un personaje
     * @param daño cantidad de resistencia que elimina en cada golpe
     * @param rango distancia maxima a la que puede realizar daño
     */
    public Zombie(int frecuenciaMov, int resistencia, int daño,int rango) {
        super( resistencia, daño, rango);
        this.frecuenciaMov = frecuenciaMov;
    }

    /**
     *
     * @return devulve la frecuenciaMov del zombie
     */
    public int getFrecuenciaMov() {
        return frecuenciaMov;
    }

    /**
     *
     * @param frecuenciaMov intervalo de turnos donde puede moverse
     */
    public void setFrecuenciaMov(int frecuenciaMov) {
        this.frecuenciaMov = frecuenciaMov;
    }
    

    
       
    
}
