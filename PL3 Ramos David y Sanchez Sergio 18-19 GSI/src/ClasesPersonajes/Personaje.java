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
public abstract class Personaje {

    private int resistencia;
    private int daño;
    private int rango;
    private int contador;
   
    /**
     *
     * @param resistencia cantidad de daño que es capaz de soportar un personaje
     * @param daño cantidad de resistencia que elimina en cada golpe
     * @param rango distancia maxima a la que puede realizar daño
     */
    public Personaje( int resistencia, int daño,int rango) {
        this.resistencia = resistencia;
        this.daño = daño;
        this.contador= 1;
        this.rango=rango;
    }

    /**
     *
     * @return devuelve la resistencia del personaje
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     *
     * @return devuelve el numero de turnos que han pasado desde que se ha generado
     */
    public int getContador() {
        return contador;
    }

    /**
     *
     * @param contador numero de turnos dedes la aparicion del personaje
     */
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    /**
     *
     * @param resistencia cantidad de daño que es capaz de soportar un personaje
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     *
     * @return devuelve el daño que hace la entidad
     */
    public int getDaño() {
        return daño;
    }

    /**
     *
     * @param danyo cantidad de resistencia que elimina en cada golpe
     */
    public void setDaño(int danyo) {
        this.daño = danyo;
    }

    /**
     *
     * @return devuelve el rango que tiene el personaje
     */
    public int getRango() {
        return rango;
    }

    /**
     *
     * @param objetivo es el personaje que va a recibir daño
     */
    public void atacar(Personaje objetivo) {
        int i = objetivo.getResistencia() - this.getDaño();
        objetivo.setResistencia(i);
    }
    public abstract JLabel crearImagen(int i, int j);
    public abstract JLabel getImagen();
    /**
     *
     * @return devuelve true si el personaje tiene resistencia 0
     */
    public boolean muerto() {
        return this.getResistencia() <= 0;
    }

   

}
