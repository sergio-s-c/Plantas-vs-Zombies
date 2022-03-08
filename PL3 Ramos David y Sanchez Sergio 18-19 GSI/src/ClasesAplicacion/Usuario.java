/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Usuario implements Serializable {

    private String nombre;
    private int victorias;
    private int derrotas;
    private Partida partida;
    private int partidasTot;
    private ArrayList<Partida> listaPartida;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.listaPartida = new ArrayList<>();
        
        
    }

    public void añadirPartida(Partida partida) {
        listaPartida.add(partida);
    }

    public Partida verDatosPartida(int indice) {
        return listaPartida.get(indice);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getPartidasTot() {
        return partidasTot;
    }

    public void setPartidasTot(int partidasTot) {
        this.partidasTot = partidasTot;
    }

    public ArrayList<Partida> getListaPartida() {
        return listaPartida;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", victorias=" + victorias + ", derrotas=" + derrotas + ", partida=" + partida + ", partidasTot=" + partidasTot + '}';
    }

}
