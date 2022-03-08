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
public class ZombieCaraCubo extends Zombie {
    private JLabel zombieCaraCuboImg;
    public ZombieCaraCubo() {
        //frecuenciaMov,resistencia,daño,rango 
        super(4, 8, 1, 1);
        zombieCaraCuboImg = new JLabel();
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        zombieCaraCuboImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ZombieCaraCubo.png"));
        zombieCaraCuboImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return zombieCaraCuboImg;
    }

    @Override
    public JLabel getImagen() {
        return zombieCaraCuboImg;
    }

}
