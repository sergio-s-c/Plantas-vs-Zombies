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
public class ZombieGrandullon extends Zombie {
    private JLabel zombieGrandullonImg;
    public ZombieGrandullon() {
        //(frecuenciaMov, resistencia, daño,rango)
        super(5, 20, 3, 1);
        zombieGrandullonImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {

        zombieGrandullonImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ZombieGrandullon.png"));
        zombieGrandullonImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return zombieGrandullonImg;
    }

    @Override
    public JLabel getImagen() {
        return zombieGrandullonImg;
    }

}
