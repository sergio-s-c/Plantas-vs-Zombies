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
public class ZombiePistolero extends Zombie {

    private JLabel zombiePistoleroImg;

    public ZombiePistolero() {
        //(frecuenciaMov, resistencia, daño,rango)
        super(1, 3, 1, 3);
        zombiePistoleroImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        zombiePistoleroImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ZombiePistolero.png"));
        zombiePistoleroImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return zombiePistoleroImg;
    }

    @Override
    public JLabel getImagen() {
        return zombiePistoleroImg;
    }

}
