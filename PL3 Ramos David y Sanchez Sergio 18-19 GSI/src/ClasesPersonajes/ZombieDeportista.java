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
public class ZombieDeportista extends Zombie {

    private JLabel zombieDeportistaImg;

    public ZombieDeportista() {
        //(frecuenciaMov, resistencia, daño,rango)
        super(1, 2, 1, 1);
        zombieDeportistaImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        zombieDeportistaImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ZombieDeportista.png"));
        zombieDeportistaImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return zombieDeportistaImg;
    }

    @Override
    public JLabel getImagen() {
        return zombieDeportistaImg;
    }

}
