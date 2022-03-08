/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import ClasesAplicacion.ListaUsuarios;
import ClasesAplicacion.Partida;
import ClasesAplicacion.Usuario;
import ClasesPersonajes.ColBoxeadora;
import ClasesPersonajes.FrutaEstrella;
import ClasesPersonajes.Girasol;
import ClasesPersonajes.Jugador;
import ClasesPersonajes.LanzaGuisantes;
import ClasesPersonajes.Nuez;
import ClasesPersonajes.Personaje;
import ClasesPersonajes.Petacereza;
import ClasesPersonajes.Planta;
import ClasesPersonajes.Zombie;
import ClasesPersonajes.ZombieCaraCubo;
import ClasesPersonajes.ZombieComun;
import ClasesPersonajes.ZombieDeportista;
import ClasesPersonajes.ZombieGrandullon;
import ClasesPersonajes.ZombiePistolero;
import java.awt.Color;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author David Ramos y Sergio Sánchez Esta ventana muestra el juego en
 * ejecucion
 */
public class Tablerojframe extends javax.swing.JFrame {

    /**
     * Creates new form Tablero
     */
    private OpcionesPartida parent;
    private Jugador jugador = new Jugador();
    private String dificultad;
    private Personaje personajes[][];
    private ArrayList<Integer> turnosGeneracionZombies;
    private int zombiesTotales;
    private Random zombieTurno = new Random();
    private Partida partida;
    private String dni;
    Color color = new Color(51, 153, 0);
    private boolean victoria = false;

    /**
     * Constructor
     *
     * @param op datos de la ventana OpcionesPartida.java
     * @param f es el número de filas
     * @param c es el número de columnas
     * @param dif es la dificultad (Baja,Media,Alta,Imposible)
     */
    public Tablerojframe(OpcionesPartida op, int f, int c, Object dif, String dni) {
        
        parent = op;
       
        dificultad = String.valueOf(dif);
        parent.setVisible(false);
        this.dni = dni;
        initComponents();
        // este codigo permite seleccionar casillas
        jTableTablero.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableTablero.setColumnSelectionAllowed(true);
        jTableTablero.setRowSelectionAllowed(true);
        // Creacion del tablero
        DefaultTableModel model = (DefaultTableModel) jTableTablero.getModel();
        model.setRowCount(f);
        model.setColumnCount(c);
        jTableTablero.setModel(model);
        jTableTablero.setRowHeight(40);
        
        JLabel girasol = new JLabel();
        girasol.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/Girasol.png"));
        girasol.setBounds(0, 0, 61, 42);
        jLabelImagenGirasol.add(girasol);

        JLabel langui = new JLabel();
        langui.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/Lanzaguistantes.png"));
        langui.setBounds(0, 0, 61, 42);
        jLabelImagenLanzaguisantes.add(langui);
        
        JLabel fruta = new JLabel();
        fruta.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/FrutaEstrella.png"));
        fruta.setBounds(0, 0, 61, 42);
        jLabelImagenFrutaEstrella.add(fruta);
        
        JLabel col = new JLabel();
        col.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/ColBoxeadora.png"));
        col.setBounds(0, 0, 61, 42);
        jLabelImagenColboxeadora.add(col);
        
        JLabel cereza = new JLabel();
        cereza.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/PetaCereza.png"));
        cereza.setBounds(0, 0, 61, 42);
        jLabelImagenPetacereza.add(cereza);
        
// Matriz doble donde se almacenan los personajes de la partida
        personajes = new Personaje[c][f];
        // Generacion de turnos de la aparicion de zombies
        turnosGeneracionZombies = new ArrayList<Integer>();
        if (dificultad.equals("Baja")) {
            zombiesTotales = 5;
            for (int i = 0; i < zombiesTotales; i++) {
                turnosGeneracionZombies.add(11 + zombieTurno.nextInt(31));
            }
        }
        if (dificultad.equals("Media")) {
            zombiesTotales = 15;
            for (int i = 0; i < zombiesTotales; i++) {
                turnosGeneracionZombies.add(8 + zombieTurno.nextInt(31));
            }
        }
        if (dificultad.equals("Alta")) {
            zombiesTotales = 25;
            for (int i = 0; i < zombiesTotales; i++) {
                turnosGeneracionZombies.add(6 + zombieTurno.nextInt(31));
            }
        }
        if (dificultad.equals("Imposible")) {
            zombiesTotales = 50;
            for (int i = 0; i < zombiesTotales; i++) {
                turnosGeneracionZombies.add(6 + zombieTurno.nextInt(31));
            }
        }
        jTextFieldMarcadorZombies.setText(String.valueOf(zombiesTotales));

        partida = new Partida(dificultad, ListaUsuarios.getLista().get(dni).getNombre());
        jTableTablero.setBackground(color);
    }

    // Este metodo actualiza el jTableTablero de la modificaciones hechas en la matriz personajes
    private void cargarTabla() {
        int columnas = personajes.length - 1;
        int filas = personajes[0].length - 1;
        for (int i = 0; i <= columnas; i++) {
            for (int j = 0; j <= filas; j++) {
                if (personajes[i][j] != null) {

                    if (personajes[i][j] instanceof Girasol) {
                        Girasol g = (Girasol) personajes[i][j];
                        if (g.getContador() == 1) {
                            jTableTablero.add(g.crearImagen(i, j));
                        }
                        if (g.muerto()) {
                            jTableTablero.remove(g.getImagen());
                        }

                    } else if (personajes[i][j] instanceof LanzaGuisantes) {
                        LanzaGuisantes lg = (LanzaGuisantes) personajes[i][j];
                        if (lg.getContador() == 1) {
                            jTableTablero.add(lg.crearImagen(i, j));
                        }
                        if (lg.muerto()) {
                            jTableTablero.remove(lg.getImagen());
                        }

                    } else if (personajes[i][j] instanceof Petacereza) {
                        Petacereza pc = (Petacereza) personajes[i][j];
                        if (pc.getContador() == 1) {
                            jTableTablero.add(pc.crearImagen(i, j));
                        }
                        if (pc.muerto()) {
                            jTableTablero.remove(pc.getImagen());
                        }

                    } else if (personajes[i][j] instanceof Nuez) {
                        Nuez n = (Nuez) personajes[i][j];
                        if (n.getContador() == 1) {
                            jTableTablero.add(n.crearImagen(i, j));
                        }
                        if (n.muerto()) {
                            jTableTablero.remove(n.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ColBoxeadora) {
                        ColBoxeadora cb = (ColBoxeadora) personajes[i][j];
                        if (cb.getContador() == 1) {
                            jTableTablero.add(cb.crearImagen(i, j));
                        }
                        if (cb.muerto()) {
                            jTableTablero.remove(cb.getImagen());
                        }

                    } else if (personajes[i][j] instanceof FrutaEstrella) {
                        FrutaEstrella fe = (FrutaEstrella) personajes[i][j];
                        if (fe.getContador() == 1) {
                            jTableTablero.add(fe.crearImagen(i, j));
                        }
                        if (fe.muerto()) {
                            jTableTablero.remove(fe.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ZombieComun) {
                        ZombieComun zc = (ZombieComun) personajes[i][j];
                        if (zc.getContador() == 1) {
                            jTableTablero.add(zc.crearImagen(i, j));
                        }
                        if (zc.muerto()) {
                            jTableTablero.remove(zc.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ZombieCaraCubo) {
                        ZombieCaraCubo zcc = (ZombieCaraCubo) personajes[i][j];
                        if (zcc.getContador() == 1) {
                            jTableTablero.add(zcc.crearImagen(i, j));
                        }
                        if (zcc.muerto()) {
                            jTableTablero.remove(zcc.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ZombieDeportista) {
                        ZombieDeportista zd = (ZombieDeportista) personajes[i][j];
                        if (zd.getContador() == 1) {
                            jTableTablero.add(zd.crearImagen(i, j));
                        }
                        if (zd.muerto()) {
                            jTableTablero.remove(zd.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ZombiePistolero) {
                        ZombiePistolero zp = (ZombiePistolero) personajes[i][j];
                        if (zp.getContador() == 1) {
                            jTableTablero.add(zp.crearImagen(i, j));
                        }
                        if (zp.muerto()) {
                            jTableTablero.remove(zp.getImagen());
                        }

                    } else if (personajes[i][j] instanceof ZombieGrandullon) {
                        ZombieGrandullon zg = (ZombieGrandullon) personajes[i][j];
                        if (zg.getContador() == 1) {
                            jTableTablero.add(zg.crearImagen(i, j));
                        }
                        if (zg.muerto()) {
                            jTableTablero.remove(zg.getImagen());
                        }
                    }
                    if (personajes[i][j].muerto()) {
                        if (personajes[i][j] instanceof Zombie) {
                            zombiesTotales--;
                            jTextFieldMarcadorZombies.setText(String.valueOf(zombiesTotales));
                        }
                        personajes[i][j] = null;

                    }
                } else {
                    jTableTablero.setValueAt("", j, i);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTablero = new javax.swing.JTable();
        jButtonVolver = new javax.swing.JButton();
        jButtonPasarTurno = new javax.swing.JButton();
        jButtonPlantaGuisante = new javax.swing.JButton();
        jTextFieldSoles = new javax.swing.JTextField();
        jButtonGirasol = new javax.swing.JButton();
        jLabelMarcadorSoles = new javax.swing.JLabel();
        jLabelMarcadorZombie = new javax.swing.JLabel();
        jTextFieldMarcadorZombies = new javax.swing.JTextField();
        jLabelMarcadorTurno1 = new javax.swing.JLabel();
        jTextFieldMarcadorTurno = new javax.swing.JTextField();
        jButtonAyuda = new javax.swing.JButton();
        jButtonPetacereza = new javax.swing.JButton();
        jButtonNuez = new javax.swing.JButton();
        jButtonColBoxeadora = new javax.swing.JButton();
        jButtonFrutaEstrella = new javax.swing.JButton();
        jLabelImagenLanzaguisantes = new javax.swing.JLabel();
        jLabelImagenFrutaEstrella = new javax.swing.JLabel();
        jLabelImagenGirasol = new javax.swing.JLabel();
        jLabelImagenColboxeadora = new javax.swing.JLabel();
        jLabelImagenPetacereza = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PLANTAS VS ZOMBIES");
        setMinimumSize(new java.awt.Dimension(700, 500));

        jTableTablero.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTableTablero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTablero.setGridColor(new java.awt.Color(0, 102, 0));
        jTableTablero.setOpaque(false);
        jTableTablero.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jTableTablero.setSelectionForeground(new java.awt.Color(51, 153, 0));
        jTableTablero.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(jTableTablero);

        jButtonVolver.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonPasarTurno.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jButtonPasarTurno.setText("Pasar de turno");
        jButtonPasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasarTurnoActionPerformed(evt);
            }
        });

        jButtonPlantaGuisante.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonPlantaGuisante.setText("Planta LanzaGuisantes (50)");
        jButtonPlantaGuisante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlantaGuisanteActionPerformed(evt);
            }
        });

        jTextFieldSoles.setEditable(false);
        jTextFieldSoles.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jTextFieldSoles.setText("50");

        jButtonGirasol.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonGirasol.setText("Planta Girasol (20)");
        jButtonGirasol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGirasolActionPerformed(evt);
            }
        });

        jLabelMarcadorSoles.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabelMarcadorSoles.setText("Soles");

        jLabelMarcadorZombie.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabelMarcadorZombie.setText("Zombies");

        jTextFieldMarcadorZombies.setEditable(false);
        jTextFieldMarcadorZombies.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jTextFieldMarcadorZombies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcadorZombiesActionPerformed(evt);
            }
        });

        jLabelMarcadorTurno1.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabelMarcadorTurno1.setText("Turno");

        jTextFieldMarcadorTurno.setEditable(false);
        jTextFieldMarcadorTurno.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jTextFieldMarcadorTurno.setText("1");
        jTextFieldMarcadorTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcadorTurnoActionPerformed(evt);
            }
        });

        jButtonAyuda.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jButtonAyuda.setText("Ayuda");
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });

        jButtonPetacereza.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonPetacereza.setText("Petacereza (50)");
        jButtonPetacereza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPetacerezaActionPerformed(evt);
            }
        });

        jButtonNuez.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonNuez.setText("Nuez (50)");
        jButtonNuez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuezActionPerformed(evt);
            }
        });

        jButtonColBoxeadora.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonColBoxeadora.setText("ColBoxeadora (75)");
        jButtonColBoxeadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColBoxeadoraActionPerformed(evt);
            }
        });

        jButtonFrutaEstrella.setFont(new java.awt.Font("MV Boli", 1, 11)); // NOI18N
        jButtonFrutaEstrella.setText("FrutaEstrella (125)");
        jButtonFrutaEstrella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFrutaEstrellaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonGirasol)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabelImagenGirasol, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabelImagenLanzaguisantes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonPlantaGuisante)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonFrutaEstrella)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonPetacereza)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonColBoxeadora))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabelImagenFrutaEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabelImagenPetacereza, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82)
                                        .addComponent(jLabelImagenColboxeadora, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPasarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelMarcadorTurno1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMarcadorTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMarcadorSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMarcadorZombie, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMarcadorZombies, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButtonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(682, 682, 682)
                                .addComponent(jButtonNuez)))
                        .addGap(0, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMarcadorTurno1)
                        .addComponent(jLabelMarcadorSoles)
                        .addComponent(jTextFieldSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMarcadorZombie)
                        .addComponent(jTextFieldMarcadorZombies, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldMarcadorTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuez)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImagenLanzaguisantes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImagenFrutaEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImagenPetacereza, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImagenGirasol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonPasarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonGirasol)
                                    .addComponent(jButtonPlantaGuisante)
                                    .addComponent(jButtonFrutaEstrella)
                                    .addComponent(jButtonPetacereza)
                                    .addComponent(jButtonColBoxeadora))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonVolver))))
                    .addComponent(jLabelImagenColboxeadora, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // Al pulsar el boton finaliza esta ventana y se ejecuta la ventana OpcionesPartida.java manteniendo los datos almacenados en el objeto parent
        this.setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasarTurnoActionPerformed
//Al pulsar este boton pasamos al siguiente turno

//Turno Plantas
//Recolectar soles de los girasoles
// i= columna , j= fila
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[0].length; j++) {
                if (personajes[i][j] instanceof Girasol) {
                    Girasol g = (Girasol) personajes[i][j];
                    if ((personajes[i][j].getContador() % g.getFrecuencia()) == 0) {
                        jugador.setSoles(jugador.getSoles() + g.getSolesGenerado());
                    }

                }
                if (personajes[i][j] instanceof Planta) {
                    Planta p = (Planta) personajes[i][j];
                    p.setContador(p.getContador() + 1);
                }
            }
        }
        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
        // Ataque de las plantas       
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[0].length; j++) {
                if (personajes[i][j] instanceof Planta) {
                    Planta p = (Planta) personajes[i][j];
                    // ataque petacereza
                    if (p instanceof Petacereza) {
                        if ((personajes[i][j].getContador() % p.getFrecuencia()) == 0) {
                            //Comprobacion 
                            for (int x = 1; x <= p.getRango(); x++) {
                                try {
                                    if (personajes[i - x][j] instanceof Zombie) {
                                        Zombie z = (Zombie) personajes[i - x][j];
                                        p.atacar(z);
                                    }
                                } catch (ArrayIndexOutOfBoundsException aioe) {
                                }
                                try {
                                    if (personajes[i + x][j] instanceof Zombie) {
                                        Zombie z = (Zombie) personajes[i + x][j];
                                        p.atacar(z);
                                    }
                                } catch (ArrayIndexOutOfBoundsException aioe) {
                                    System.out.println("Pasa algo");
                                }
                                try {
                                    if (personajes[i][j - x] instanceof Zombie) {
                                        Zombie z = (Zombie) personajes[i][j - x];
                                        p.atacar(z);
                                    }
                                } catch (ArrayIndexOutOfBoundsException aioe) {

                                }
                                try {
                                    if (personajes[i][j + x] instanceof Zombie) {
                                        Zombie z = (Zombie) personajes[i][j + x];
                                        p.atacar(z);
                                    }
                                } catch (ArrayIndexOutOfBoundsException aioe) {

                                }

                                p.setResistencia(0);
                                cargarTabla();
                            }
                        }
                        //Para que no entre en el ataque normal
                        break;
                    }
                    // ataque FrutaEstrella
                    if (p instanceof FrutaEstrella) {

                        for (int k = i + 1; k < min(p.getRango() + i + 1, personajes.length); k++) {
                            if (personajes[k][j] instanceof Zombie) {
                                Zombie z = (Zombie) personajes[k][j];
                                if ((p.getContador() - 1) % p.getFrecuencia() == 0) {
                                    p.atacar(z);
                                    cargarTabla();
                                }
                            }
                        }
                        // Para evitar el ataque normal
                        break;
                    }
                    for (int k = i + 1; k < min(p.getRango() + i + 1, personajes.length); k++) {
                        if (personajes[k][j] instanceof Zombie) {
                            Zombie z = (Zombie) personajes[k][j];
                            p.atacar(z);
                            cargarTabla();
                            break;
                        }
                    }
                }
            }
        }
        // Turno Zombie
        //Los zombies realizan el ataque 
        // i= columna , j= fila
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[0].length; j++) {
                if (personajes[i][j] instanceof Zombie) {
                    Zombie z = (Zombie) personajes[i][j];
                    boolean atacar = false;
                    for (int k = i - 1; k >= max(i - z.getRango(), 0); k--) {
                        if (personajes[k][j] instanceof Planta) {
                            Planta p = (Planta) personajes[k][j];
                            z.atacar(p);
                            cargarTabla();
                            atacar = true;
                            break;
                        }
                    }
                    //Movimiento de los zombies, si el zombie ha atacado no se mueve
                    if (!atacar) {
                        // Si el zombie llega al final, salta la excepcion IndexOutOfBoundsException y la partida finaliza
                        try {
                            if ((personajes[i][j].getContador() % z.getFrecuenciaMov()) == 0) {
                                if (personajes[i - 1][j] == null) {
                                    personajes[i - 1][j] = personajes[i][j];
                                    jTableTablero.remove(personajes[i][j].getImagen());
                                    jTableTablero.add(personajes[i - 1][j].crearImagen(i - 1, j));
                                    personajes[i][j] = null;

                                }
                            }
                        } catch (IndexOutOfBoundsException index) {
                            PantallaDerrota pd = new PantallaDerrota(dni, this);
                            pd.setVisible(true);                           
                            ListaUsuarios.cargarDatos();
                            HashMap<String, Usuario> lista = ListaUsuarios.getLista();
                            Usuario u;
                            u = (Usuario) lista.get(dni);
                            u.setDerrotas(u.getDerrotas() + 1);
                            u.añadirPartida(partida);
//                            ListaUsuarios.getLista().replace(dni, u);
                            ListaUsuarios.guardarDatos();
                            this.dispose();
                            // evitar que salte mas de una excepcion
                            break;

                        }
                        z.setContador(z.getContador() + 1);
                        cargarTabla();
                    }
                }

            }
        }

        // Generar zombies
        for (int f : turnosGeneracionZombies) {
            if (f == Integer.valueOf(jTextFieldMarcadorTurno.getText())) {
                Random rand = new Random();
                int num = 1 + rand.nextInt(100);
                if (num < 51) {
                    ZombieComun zc = new ZombieComun();
                    int fila;
                    do {
                        fila = zombieTurno.nextInt(personajes[0].length);
                    } while (personajes[personajes.length - 1][fila] != null);
                    personajes[personajes.length - 1][fila] = zc;
                    cargarTabla();
                }
                if (51 <= num && num < 76) {
                    ZombieCaraCubo zcc = new ZombieCaraCubo();
                    int fila;
                    do {
                        fila = zombieTurno.nextInt(personajes[0].length);
                    } while (personajes[personajes.length - 1][fila] != null);
                    personajes[personajes.length - 1][fila] = zcc;
                    cargarTabla();
                }
                if (76 <= num && num < 86) {
                    ZombieDeportista zd = new ZombieDeportista();
                    int fila;
                    do {
                        fila = zombieTurno.nextInt(personajes[0].length);
                    } while (personajes[personajes.length - 1][fila] != null);
                    personajes[personajes.length - 1][fila] = zd;
                    cargarTabla();
                }
                if (86 <= num && num < 96) {
                    ZombiePistolero zp = new ZombiePistolero();
                    int fila;
                    do {
                        fila = zombieTurno.nextInt(personajes[0].length);
                    } while (personajes[personajes.length - 1][fila] != null);
                    personajes[personajes.length - 1][fila] = zp;
                    cargarTabla();
                }
                if (96 <= num && num < 101) {
                    ZombieGrandullon zg = new ZombieGrandullon();
                    int fila;
                    do {
                        fila = zombieTurno.nextInt(personajes[0].length);
                    } while (personajes[personajes.length - 1][fila] != null);
                    personajes[personajes.length - 1][fila] = zg;
                    cargarTabla();
                }

            }
        }
// Si el marcador llega a 0, ganas la partida y finaliza la partida
        if (jTextFieldMarcadorZombies.getText().equals("0") && !victoria) {
        //
        JLabel serpentina1 = new JLabel();
        serpentina1.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/serpentina.png"));
        serpentina1.setBounds(0, 0, 287,200);
        jTableTablero.add(serpentina1);
        JLabel serpentina2 = new JLabel();
        serpentina2.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/serpentina.png"));
        serpentina2.setBounds(288, 0, 287,200);
        jTableTablero.add(serpentina2);
        JLabel serpentina3 = new JLabel();
        serpentina3.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/serpentina.png"));
        serpentina3.setBounds(575, 0, 287,200);
        jTableTablero.add(serpentina3);
        JLabel serpentina4 = new JLabel();
        serpentina4.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/serpentina.png"));
        serpentina4.setBounds(863, 0, 287,200);
        jTableTablero.add(serpentina4);
        JLabel serpentina5 = new JLabel();
        serpentina5.setIcon(new ImageIcon("../PL3 Ramos David y Sanchez Sergio 18-19 GSI/serpentina.png"));
        serpentina5.setBounds(1150, 0, 287,200);
        jTableTablero.add(serpentina5);
        
        //  
            
            Tablerojframe tf = this;
            PantallaVictoria pv = new PantallaVictoria(dni,tf);
            pv.setVisible(true);
            
            int contadorCoste = 0;
            for (int i = 0; i < personajes.length; i++) {
                for (int j = 0; j < personajes[0].length; j++) {
                    if (personajes[i][j] instanceof Planta) {
                        Planta p = (Planta) personajes[i][j];
                        contadorCoste += p.getCoste();
                    }
                }
            }
            partida.calcularPuntuacionTot(contadorCoste, Integer.valueOf(jTextFieldSoles.getText()));
            ListaUsuarios.cargarDatos();
            Usuario u;
            HashMap<String, Usuario> lista;
            lista = ListaUsuarios.getLista();
            u = lista.get(dni);
            u.setVictorias(u.getVictorias() + 1);
            u.añadirPartida(partida);
            ListaUsuarios.guardarDatos();
            victoria=true;
        }

        jTextFieldMarcadorTurno.setText(String.valueOf(Integer.valueOf(jTextFieldMarcadorTurno.getText()) + 1));
    }//GEN-LAST:event_jButtonPasarTurnoActionPerformed

    private void jButtonPlantaGuisanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlantaGuisanteActionPerformed
        // Al pulsar el boton se crea un lanzaguisantes y se guarda en la matriz personajes
        if (jugador.getSoles() >= 50) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        LanzaGuisantes lg = new LanzaGuisantes();
                        personajes[columna][fila] = lg;
                        jugador.setSoles(jugador.getSoles() - 50);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }

        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonPlantaGuisanteActionPerformed

    private void jButtonGirasolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGirasolActionPerformed
        // Al pulsar el boton se crea un girasol y se guarda en la matriz personajes
        if (jugador.getSoles() >= 20) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        Girasol g = new Girasol();
                        personajes[columna][fila] = (Girasol) g;
                        jugador.setSoles(jugador.getSoles() - 20);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }
        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonGirasolActionPerformed

    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        // Al pulsar el boton se cierra la ventana actual guardando su estado en un objeto y se ejecuta la ventana Instrucciones.java
        Instrucciones i = new Instrucciones(this);
        i.setVisible(true);
    }//GEN-LAST:event_jButtonAyudaActionPerformed

    private void jButtonPetacerezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPetacerezaActionPerformed
        // Al pulsar el boton se crea una petacereza y se guarda en la matriz personajes
        if (jugador.getSoles() >= 50) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        Petacereza pc = new Petacereza();
                        personajes[columna][fila] = pc;
                        jugador.setSoles(jugador.getSoles() - 50);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }

        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonPetacerezaActionPerformed

    private void jButtonNuezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuezActionPerformed
        // Al pulsar el boton se crea una nuez y se guarda en la matriz personajes
        if (jugador.getSoles() >= 50) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        Nuez n = new Nuez();
                        personajes[columna][fila] = n;
                        jugador.setSoles(jugador.getSoles() - 50);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }

        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonNuezActionPerformed

    private void jButtonColBoxeadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColBoxeadoraActionPerformed
        // Al pulsar el boton se crea una ColBOxeadora y se guarda en la matriz personajes
        if (jugador.getSoles() >= 75) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        ColBoxeadora cb = new ColBoxeadora();
                        personajes[columna][fila] = cb;
                        jugador.setSoles(jugador.getSoles() - 75);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }

        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonColBoxeadoraActionPerformed

    private void jTextFieldMarcadorTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcadorTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcadorTurnoActionPerformed

    private void jTextFieldMarcadorZombiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcadorZombiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcadorZombiesActionPerformed

    private void jButtonFrutaEstrellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFrutaEstrellaActionPerformed
        // Al pulsar el boton se crea una FrutaEstella y se guarda en la matriz personajes
        if (jugador.getSoles() >= 125) {
            int columna = jTableTablero.getSelectedColumn();
            int fila = jTableTablero.getSelectedRow();
            if (columna > -1 && fila > -1) {
                if (columna != personajes.length - 1) {
                    if (personajes[columna][fila] == null) {
                        FrutaEstrella fe = new FrutaEstrella();
                        personajes[columna][fila] = fe;
                        jugador.setSoles(jugador.getSoles() - 125);
                        jTextFieldSoles.setText(String.valueOf(jugador.getSoles()));
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "LA CASILLA ESTA OCUPADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "NO PUEDES CREAR UNA PLANTA EN ESTA COLUMNA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "DEBES SELECCIONAR UNA CELDA");
            }

        } else {
            JOptionPane.showMessageDialog(this, "NO TIENES SUFICIENTES SOLES");
        }
    }//GEN-LAST:event_jButtonFrutaEstrellaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonColBoxeadora;
    private javax.swing.JButton jButtonFrutaEstrella;
    private javax.swing.JButton jButtonGirasol;
    private javax.swing.JButton jButtonNuez;
    private javax.swing.JButton jButtonPasarTurno;
    private javax.swing.JButton jButtonPetacereza;
    private javax.swing.JButton jButtonPlantaGuisante;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelImagenColboxeadora;
    private javax.swing.JLabel jLabelImagenFrutaEstrella;
    private javax.swing.JLabel jLabelImagenGirasol;
    private javax.swing.JLabel jLabelImagenLanzaguisantes;
    private javax.swing.JLabel jLabelImagenPetacereza;
    private javax.swing.JLabel jLabelMarcadorSoles;
    private javax.swing.JLabel jLabelMarcadorTurno1;
    private javax.swing.JLabel jLabelMarcadorZombie;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTablero;
    private javax.swing.JTextField jTextFieldMarcadorTurno;
    private javax.swing.JTextField jTextFieldMarcadorZombies;
    private javax.swing.JTextField jTextFieldSoles;
    // End of variables declaration//GEN-END:variables
}
