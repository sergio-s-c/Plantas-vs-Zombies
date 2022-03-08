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
public class ZombieComun extends Zombie {

    private JLabel zombieComunImg;

    /**
     * Constructor
     */
    public ZombieComun() {
        //(frecuenciaMov, resistencia, daño,rango)
        super(2, 5, 1, 1);
        zombieComunImg= new JLabel();
        zombieComunImg.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ZombieComun.png"));
        
    }

    @Override
    public JLabel crearImagen(int i, int j) {
        zombieComunImg.setBounds(i * 130 + 34, j * 40, 69, 40);
        return zombieComunImg;
    }

    @Override
    public JLabel getImagen() {
        return zombieComunImg;
    }

}
