/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAplicacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author david
 */
public class ListaUsuarios implements Serializable {
    private static HashMap<String,Usuario> lista;

    public ListaUsuarios() {
        this.lista = new HashMap<>();
    }
    public static void guardarDatos() {
        try {
            if (!lista.isEmpty()) {
                try (FileOutputStream fos = new FileOutputStream("ListaUsuarios.txt")) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(lista);
                }
            } else {
                System.out.println("Error, lista vacia");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }
        public static void cargarDatos() {
        try {
            try (FileInputStream fis = new FileInputStream("ListaUsuarios.txt")) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                lista = (HashMap) ois.readObject();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase" + cnfe.getMessage());
        } 
    }

    public static HashMap<String, Usuario> getLista() {
        return lista;
    }

    public void setLista(HashMap<String, Usuario> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ListaUsuarios{" + "lista=" + lista + '}';
    }
    
    
}
