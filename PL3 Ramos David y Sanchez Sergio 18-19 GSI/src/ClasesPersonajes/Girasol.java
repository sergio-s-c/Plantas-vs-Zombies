/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesPersonajes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author David Ramos y Sergio Sánchez
 */
public class Girasol extends Planta {

    private int solesGenerado=10;
    private JLabel girasolImg;

    /**
     * Constructor
     */
    public Girasol() {
        // (coste,frecuencia,resistencia,daño,rango)
        super(20, 2, 1, 0, 0);
        girasolImg = new JLabel();
        girasolImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/Girasol.png"));
        
    }

    /**
     *
     * @return devuelve la cantidad de soles generados
     */
    public int getSolesGenerado() {
        return solesGenerado;
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        girasolImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return girasolImg;
    }

    @Override
    public JLabel getImagen() {
        return girasolImg;
    }
}
