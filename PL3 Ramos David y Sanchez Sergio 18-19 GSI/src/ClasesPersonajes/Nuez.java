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
 * @author david
 */
public class Nuez extends Planta {
    private JLabel nuezImg;
    public Nuez() {
        // coste,frecuencia,resistencia,daño, rango
        super(50, 0, 10, 0, 0);
        nuezImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        nuezImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/Nuez.png"));
        nuezImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return nuezImg;
    }

    @Override
    public JLabel getImagen() {
        return nuezImg;
    }

}
