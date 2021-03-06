/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.vista;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Equipo;
import baloncesto.modelo.Jugador;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 9fdam03
 */
public class vistaEquipo extends javax.swing.JFrame {

    /**
     * Creates new form vistaEquipo
     */
    private Equipo equipo;
    private Jugador jugador;
    private int select_id;
    private int select_index;

    public vistaEquipo() {
        initComponents();
        // this.setExtendedState(MAXIMIZED_BOTH);
    }

    public int getSelect_id() {
        String[] id = jComboBox1.getSelectedItem().toString().split("_");
        if (id.length == 2) {
            this.select_id = Integer.parseInt(jComboBox1.getSelectedItem().toString().split("_")[0]);
            return select_id;
        } else {
            return 0;
        }
    }

    public int getSelect_index() {
        this.select_index = (Integer) jComboBox1.getSelectedIndex();
        return select_index;
    }

    private vistaPrincipal vP;

    public vistaEquipo(vistaPrincipal vP, Equipo equipo) {
        initComponents();
        this.equipo = equipo;
        this.vP = vP;
        this.setLocationRelativeTo(this);
        descripciónEquipo();
        llenarComboJugadores();
        this.jugador = findJugadorSelecionado();
        detalleJugador();
    }

    private void descripciónEquipo() {
        this.jLabel1.setText(equipo.getNombre());
    }

    private void llenarComboJugadores() {
        jComboBox1.removeAllItems();
        if (equipo.getJugadores() != null && equipo.getJugadores().size() > 0) {
            for (Jugador curJ : equipo.getJugadores()) {
                jComboBox1.addItem(curJ.getId() + "_" + curJ.getNombre() + " " + curJ.getApellido());
            }
        } else {
            jComboBox1.addItem("No existen jugadores");
        }

        jComboBox1.addItem("0_Nuevo jugador");

    }

    private void detalleJugador() {
        //Si es nuevo enviar un jugador new()
        detalleReadJugador(this.jugador);
        detalleWritteJugador(this.jugador);
    }

    private void detalleReadJugador(Jugador jug) {
        if (jug != null) {
            labelNombreRead.setText(jug.getNombre());
            labelApellido1Read.setText(jug.getApellido());
            labelApellido2Read.setText(jug.getApellido2());
            labelPesoRead.setText(Float.toString(jug.getPeso()));
            labelAlturaRead.setText(Float.toString(jug.getAltura()));
            labelPosicionRead.setText(jug.getPosicion());
            labelDescripcionRead.setText(jug.getDescripcion());
        }
    }

    private void detalleWritteJugador(Jugador jug) {
        if (jug != null) {
            jTFieldNombre.setText(jug.getNombre());
            jTFieldApellido1.setText(jug.getApellido());
            jTFieldApellido2.setText(jug.getApellido2());
            jTFieldPeso.setText(Float.toString(jug.getPeso()));
            jTFieldAltura.setText(Float.toString(jug.getAltura()));
            jTFieldPosicion.setText(jug.getPosicion());
            jTAreaDescripcion.setText(jug.getDescripcion());
        }
    }

    private Jugador findJugadorSelecionado() {
        Jugador jugador = null;
        if (getSelect_id() != 0) {
            if (equipo.getJugadores() != null && equipo.getJugadores().size() > 0) {
                for (Jugador curJ : equipo.getJugadores()) {
                    if (curJ.getId() == getSelect_id()) {
                        // System.out.println(getSelect_id() + " -> " + curJ.getNombre());                
                        jugador = curJ;
                        break;
                    }
                }
            }
        } else {
            if (equipo.getJugadores().size() > 0) {
                jugador = new Jugador();
                //Cogemos el Id del ultimo jugador y lo incrementamos
                jugador.setId(equipo.getJugadores().get(equipo.getJugadores().size() - 1).getId() + 1);
                jugador.setConector(this.equipo.getConector());
                jugador.setEquipo(equipo);
                //System.out.println("Id ultimo jugador -> " + equipo.getJugadores().get(equipo.getJugadores().size() -1).getId());
                //System.out.println("Id juhador nuevo -> " + jugador.getId());
            } else {
                jugador = new Jugador();
                //Cogemos el Id del ultimo jugador y lo incrementamos
                jugador.setId(1);
                jugador.setConector(this.equipo.getConector());
                jugador.setEquipo(equipo);
            }
        }

        return jugador;
    }

    /**
     * Modificar los datos del objeto jugador con los nuevos
     */
    private Jugador modificarJugador() {
        Jugador jug = findJugadorSelecionado();
        if (jug != null) {
            jug.setNombre(jTFieldNombre.getText());
            jug.setApellido(jTFieldApellido1.getText());
            jug.setApellido2(jTFieldApellido2.getText());
            jug.setPeso(Float.parseFloat(jTFieldPeso.getText()));
            jug.setAltura(Float.parseFloat(jTFieldAltura.getText()));
            jug.setPosicion(jTFieldPosicion.getText());
            jug.setDescripcion(jTAreaDescripcion.getText());
        }

        return jug;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelPesoRead = new java.awt.Label();
        labelAlturaRead = new java.awt.Label();
        labelPosicionRead = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelNombreRead = new java.awt.Label();
        labelApellido1Read = new java.awt.Label();
        labelApellido2Read = new java.awt.Label();
        jLabel8 = new javax.swing.JLabel();
        labelDescripcionRead = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTFieldNombre = new javax.swing.JTextField();
        jTFieldApellido1 = new javax.swing.JTextField();
        jTFieldApellido2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTFieldPeso = new javax.swing.JTextField();
        jTFieldAltura = new javax.swing.JTextField();
        jTFieldPosicion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAreaDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Titulo Equipo");

        jButton4.setText("Atrás");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel10.setText("Selecciona tu jugador");

        jButton1.setText("Incidencias");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Entrenamientos");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton5.setText("-");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("PESO:");

        jLabel6.setText("ALTURA:");

        jLabel7.setText("POSICIÓN:");

        labelPesoRead.setText("label5");

        labelAlturaRead.setText("label6");

        labelPosicionRead.setText("label7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(labelPesoRead, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(labelAlturaRead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPosicionRead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPesoRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAlturaRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPosicionRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("NOMBRE:");

        jLabel3.setText("APELLIDO_1");

        jLabel4.setText("APELLIDO_2");

        labelNombreRead.setText("label1");

        labelApellido1Read.setText("label2");

        labelApellido2Read.setText("label3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(labelNombreRead, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(labelApellido1Read, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelApellido2Read, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNombreRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(labelApellido1Read, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(labelApellido2Read, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel8.setText("DESCRIPCIÓN");

        labelDescripcionRead.setText("label4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDescripcionRead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDescripcionRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Detalle", jPanel1);

        jButton6.setText("Grabar");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("NOMBRE:");

        jLabel11.setText("APELLIDO_1");

        jLabel12.setText("APELLIDO_2");

        jTFieldNombre.setText("jTextField1");

        jTFieldApellido1.setText("jTextField2");

        jTFieldApellido2.setText("jTextField3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTFieldApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jTFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jTFieldApellido1)
                            .addComponent(jLabel12))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFieldApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFieldApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("PESO:");

        jLabel14.setText("ALTURA:");

        jLabel15.setText("POSICIÓN:");

        jTFieldPeso.setText("jTextField4");

        jTFieldAltura.setText("jTextField5");

        jTFieldPosicion.setText("jTextField6");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jTFieldPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jTFieldAltura)
                    .addComponent(jTFieldPosicion))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addGap(11, 11, 11)
                .addComponent(jTFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFieldPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel16.setText("DESCRIPCIÓN");

        jTAreaDescripcion.setColumns(20);
        jTAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jTAreaDescripcion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Editar", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(666, 666, 666)
                .addComponent(jButton4)
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(49, 49, 49))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botón para ir a la ventana anterior
     */
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        vP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4MouseClicked

    /**
     * Botón para ir a la ventana de incidencias del jugador
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Jugador jugador = findJugadorSelecionado();
        if (jugador != null && jugador.getNombre() != null) {
            VistaIncidencias vIn = new VistaIncidencias(jugador, this);
            this.setVisible(false);
            vIn.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "El jugador todavía no existe");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * Botón para ir a la ventana de entrenamientos del jugador
     */
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Jugador jugador = findJugadorSelecionado();
        if (jugador != null && jugador.getNombre() != null) {
            VistaEntrenamientos vIn = new VistaEntrenamientos(jugador, this);
            this.setVisible(false);
            vIn.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "El jugador todavía no existe");
        }
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * Evento change del combo de jugadores para rellenar los formularios de
     * datos del jugador
     */
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:        
        if (jComboBox1 != null && jComboBox1.getItemCount() > 0) {
            // System.out.println("Id_select -> " + this.getSelect_id());
            if (this.getSelect_id() != 0) {
                jTabbedPane2.setSelectedIndex(0);
                this.jugador = findJugadorSelecionado();
            } else {
                jTabbedPane2.setSelectedIndex(1);
            }
            this.jugador = findJugadorSelecionado();
            detalleJugador();
        }

        //System.out.print("Cambiando item");
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * Botón para modificar los datos de un jugador o para insertar un nuevo
     * jugador
     */
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:

        String mensaje = "";
        if (getSelect_id() != 0) {
            mensaje += "¿Estas seguro de modificar los datos del jugador " + this.jugador.getNombre() + "?";
        } else {
            mensaje += "Vas a insertar un nuevo jugador ¿Estas seguro?";
        }
        Integer confirm = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de modificar los datos del jugador " + this.jugador.getNombre() + "?");
        //System.out.println("Estado CONFIRM = " + confirm);            
        switch (confirm) {
            case 0:
                //System.out.println("SI modificar");
                Jugador jMod = modificarJugador();
                if (jMod != null) {
                    if (getSelect_id() != 0) {
                        if (jMod.update()) {
                            detalleReadJugador(jMod);
                            int index_aux = getSelect_index();
                            llenarComboJugadores();
                            jComboBox1.setSelectedIndex(index_aux);
                        }
                    } else {
                        if (jMod.save()) {
                            equipo.getJugadores().add(jMod);
                            detalleReadJugador(jMod);
                            llenarComboJugadores();
                            jComboBox1.setSelectedIndex(jComboBox1.getItemCount() - 2);
                        }
                    }
                }
                break;
            case 1:
                //System.out.println("NO modificar");
                break;
            case 2:
                //System.out.println("ABORTEN modificar");
                detalleWritteJugador(this.jugador);
                break;
        }
    }//GEN-LAST:event_jButton6MouseClicked

    /**
     * Botón para insertar un nuevo jugador
     */
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:

        //System.out.println("Vamos a insertar un nuevo jugador");
        Integer confirm = JOptionPane.showConfirmDialog(rootPane, "¿Deseas añadir un nuevo jugador al equipo?");

        //System.out.println("Estado CONFIRM = " + confirm);
        switch (confirm) {
            case 0:
                //System.out.println("SI añadir");
                jComboBox1.setSelectedIndex(jComboBox1.getItemCount() - 1);
                break;
            case 1:
                //System.out.println("NO añadir");
                break;
            case 2:
                //System.out.println("ABORTEN añadir");
                break;
        }
    }//GEN-LAST:event_jButton3MouseClicked

    /**
     * Botón para eliminar un jugador
     */
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        //System.out.println("Vamos a eliminar el jugador seleccionado -> " + this.jugador.getNombre());
        Jugador jug_del = findJugadorSelecionado();
        if (jug_del != null && jug_del.getNombre() != null) {
            Integer confirm = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres eliminar al jugador " + jug_del.getNombre() + "?");

            //System.out.println("Estado CONFIRM = " + confirm);
            switch (confirm) {
                case 0:
                    //System.out.println("SI eliminar");

                    if (jug_del.delete()) {
                        JOptionPane.showMessageDialog(rootPane, "Se ha eliminado correctamente");

                        llenarComboJugadores();
                        jComboBox1.setSelectedIndex(0);
                    }

                    break;
                case 1:
                    //System.out.println("NO eliminar");
                    break;
                case 2:
                    //System.out.println("ABORTEN eliminar");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El jugador todavía no existe");
        }

    }//GEN-LAST:event_jButton5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaEquipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAreaDescripcion;
    private javax.swing.JTextField jTFieldAltura;
    private javax.swing.JTextField jTFieldApellido1;
    private javax.swing.JTextField jTFieldApellido2;
    private javax.swing.JTextField jTFieldNombre;
    private javax.swing.JTextField jTFieldPeso;
    private javax.swing.JTextField jTFieldPosicion;
    private javax.swing.JTabbedPane jTabbedPane2;
    private java.awt.Label labelAlturaRead;
    private java.awt.Label labelApellido1Read;
    private java.awt.Label labelApellido2Read;
    private java.awt.Label labelDescripcionRead;
    private java.awt.Label labelNombreRead;
    private java.awt.Label labelPesoRead;
    private java.awt.Label labelPosicionRead;
    // End of variables declaration//GEN-END:variables
}
