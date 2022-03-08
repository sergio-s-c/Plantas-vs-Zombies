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
public class Petacereza extends Planta {
    private JLabel petacerezaImg;
    public Petacereza() {
        // coste,frecuencia,resistencia,daño,rango
        super(50, 2, 2, 10, 1);
        petacerezaImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        petacerezaImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/PetaCereza.png"));
        petacerezaImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return petacerezaImg;
    }

    @Override
    public JLabel getImagen() {
        return petacerezaImg;
    }
    
}
