/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.vista;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import baloncesto.modelo.Entrenamiento;
import baloncesto.modelo.Incidencia;
import baloncesto.modelo.Jugador;
import baloncesto.modelo.TipoEntrenamiento;
import baloncesto.modelo.TipoIncidencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 9fdam02
 */
public class VistaIncidencias extends javax.swing.JFrame{

    private final boolean pruebas = false;

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";

    private vistaEquipo vE;
    private Jugador j;
    private List<Incidencia> listaIncidencias = new ArrayList<>();
    private List<TipoIncidencia> tiposIncidencias = null;
    
    private DefaultTableModel tableModel;

    /**
     * Creates new form VistaIncidencias
     */
    public VistaIncidencias() {
        initComponents();
        this.setLocationRelativeTo(this);
    }

    public VistaIncidencias(Jugador j, vistaEquipo vE) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.vE = vE;
        this.j = j;
        jLabel1.setText("Incidencias de " + j.getNombre() + " " + j.getApellido());
        rellenarJTable();
        rellenarJComboBox();
    }

    private void rellenarJTable() {
        if (pruebas) {
            listaIncidencias = new ArrayList<>();
            TipoIncidencia tE = new TipoIncidencia(1, "llegar tarde", "1000", "pues eso");
            Incidencia i = new Incidencia(tE, j, "22-07-19977");
            listaIncidencias.add(i);
        } else {
            listaIncidencias = j.getIncidencias();
        }
        Object[][] data = {};
        if (listaIncidencias != null) {
            data = new Object[listaIncidencias.size()][4];
            for (int i = 0; i < listaIncidencias.size(); i++) {
                data[i][0] = listaIncidencias.get(i).getTipoIncidencia().getTipo();
                data[i][1] = listaIncidencias.get(i).getFecha();
                data[i][2] = listaIncidencias.get(i).getTipoIncidencia().getSancion();
                data[i][3] = listaIncidencias.get(i).getTipoIncidencia().getDescripcion();
            }
        }
        String[] colName = {"Tipo", "Fecha", "Sanción", "Descripción"};

        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(data, colName));
        
        tableModel = (DefaultTableModel)jTable1.getModel();
        jScrollPane1.setViewportView(jTable1);

        jTable1 = new JTable(data, colName);
        jScrollPane1 = new JScrollPane(jTable1);
        
    }
    public void refrescarJTable(){     
        tableModel.setRowCount(0);
        Object[] data = {};
        if (listaIncidencias != null) {
            data = new Object[4];
            for (int i = 0; i < listaIncidencias.size(); i++) {
                data[0] = listaIncidencias.get(i).getTipoIncidencia().getTipo();
                data[1] = listaIncidencias.get(i).getFecha();
                data[2] = listaIncidencias.get(i).getTipoIncidencia().getSancion();
                data[3] = listaIncidencias.get(i).getTipoIncidencia().getDescripcion();
                tableModel.addRow(data);
                System.out.println(tableModel.getRowCount());
            }
        }       

        jTable1.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void rellenarJComboBox() {

        switch (j.getConector()) {
            case mysqlConector:
            case sqlServerConector: {
                try {
                    tiposIncidencias = SQLInterface.getTiposIncidencias(j.getConector());
                } catch (SQLException ex) {
                    Logger.getLogger(VistaIncidencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case db4oConector:
                tiposIncidencias = DB4OInteface.getTiposIncidencias(new TipoIncidencia());
                break;
            default:
                throw new AssertionError();
        }

        if (tiposIncidencias != null && tiposIncidencias.size() > 0) {
            for (TipoIncidencia curT : tiposIncidencias) {
                jComboBox1.addItem(curT.getId() + "_" + curT.getTipo());
            }
        } else {
            jComboBox1.addItem("Sin Tipos");
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Incidencias Jugador");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nueva Incidecnia");

        jLabel3.setText("Tipo incidencia");

        jLabel4.setText("Fecha(YYYY-MM-DD)");
        jLabel4.setToolTipText("");

        jButton1.setText("Añadir Incidencia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        vE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fecha = jTextField1.getText();
        TipoIncidencia tI = new TipoIncidencia();
        String elegido = (String) jComboBox1.getSelectedItem();
        String[] id = elegido.split("_");
        tI.setId(Integer.parseInt(id[0]));//conseguimos el id y se lo asignamos al tipo de Incidencia

        tI = tiposIncidencias.get(tiposIncidencias.indexOf(tI)); //conseguimos el objeto

        Incidencia i = new Incidencia(tI, j, fecha);

        switch (j.getConector()) {
            case mysqlConector:
            case sqlServerConector: {
                try {
                    SQLInterface.insertIncidencia(i, j.getConector());
                } catch (SQLException ex) {
                    Logger.getLogger(VistaIncidencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case db4oConector:
                DB4OInteface.insertIncidencia(i);
                System.out.println("Insertada incidencia");
                break;
            default:
                throw new AssertionError();
        }
        refrescarJTable();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaIncidencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


}
