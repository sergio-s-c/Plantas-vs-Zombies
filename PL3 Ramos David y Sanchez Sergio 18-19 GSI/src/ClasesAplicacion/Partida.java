/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAplicacion;

import java.io.Serializable;

/**
 *
 * @author david
 */
public class Partida implements Serializable {

    double puntuacion = 0;
    String dificultad;
    String usuario;

    public Partida(String dificultad, String usuario) {
        this.dificultad = dificultad;
        this.usuario = usuario;
    }

    public void calcularPuntuacionTot(int plantas, int soles) {
        double mult = 0;
        if (dificultad.equals("Baja")) {
            mult = 0.5;
        }else if (dificultad.equals("Media")) {
            mult = 1;
        }if (dificultad.equals("Alta")) {
            mult = 1.5;
        }if (dificultad.equals("Imposible")) {
            mult = 2;
        }
        this.puntuacion= (plantas+soles)*mult;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

 

   
    
}
