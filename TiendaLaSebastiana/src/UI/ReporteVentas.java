/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import BusinessLogic.Reporte;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author migue
 */
public class ReporteVentas extends javax.swing.JFrame {
    private Main parent;
    /**
     * Creates new form ReporteVentas
     */
    public ReporteVentas(Main parent) {
        this.parent = parent;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelecPeriodoTiempo = new javax.swing.JLabel();
        lblFechaInicioConsultar = new javax.swing.JLabel();
        lblFechaFinConsultar = new javax.swing.JLabel();
        txtDiaInicio = new javax.swing.JTextField();
        txtMesInicio = new javax.swing.JTextField();
        txtAnioInicio = new javax.swing.JTextField();
        txtDiaFin = new javax.swing.JTextField();
        txtMesFin = new javax.swing.JTextField();
        txtAnioFin = new javax.swing.JTextField();
        lblDiaInicio = new javax.swing.JLabel();
        lblDiaFin = new javax.swing.JLabel();
        lblMesInicio = new javax.swing.JLabel();
        lblMesFin = new javax.swing.JLabel();
        lblAnioInicio = new javax.swing.JLabel();
        lblAnioFin = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSelecPeriodoTiempo.setText("Seleccione el periodo de tiempo a consultar:");

        lblFechaInicioConsultar.setText("Fecha Inicio:");

        lblFechaFinConsultar.setText("Fecha fin:");

        txtMesInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesInicioActionPerformed(evt);
            }
        });

        txtAnioInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioInicioActionPerformed(evt);
            }
        });

        txtMesFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesFinActionPerformed(evt);
            }
        });

        txtAnioFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioFinActionPerformed(evt);
            }
        });

        lblDiaInicio.setText("DD");

        lblDiaFin.setText("DD");

        lblMesInicio.setText("MM");

        lblMesFin.setText("MM");

        lblAnioInicio.setText("AAAA");

        lblAnioFin.setText("AAAA");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelecPeriodoTiempo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFechaFinConsultar)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDiaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblDiaFin)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtMesFin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtAnioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblMesFin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(lblAnioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 51, Short.MAX_VALUE))
                                    .addComponent(lblFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFechaInicioConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDiaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblDiaInicio)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblMesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtAnioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(lblAnioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblSelecPeriodoTiempo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaInicioConsultar)
                    .addComponent(txtDiaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaInicio)
                    .addComponent(lblMesInicio)
                    .addComponent(lblAnioInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaFinConsultar)
                    .addComponent(txtDiaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnioFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaFin)
                    .addComponent(lblMesFin)
                    .addComponent(lblAnioFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnConsultar))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnioInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioInicioActionPerformed

    private void txtMesInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesInicioActionPerformed

    private void txtMesFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesFinActionPerformed

    private void txtAnioFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioFinActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Reporte reporte = new Reporte(parent.getCaja());
        String diaInicioStr = txtDiaInicio.getText().trim();
        String mesInicioStr = txtMesInicio.getText().trim();
        String anioInicioStr = txtAnioInicio.getText().trim();
        String diaFinStr = txtDiaFin.getText().trim();
        String mesFinStr = txtMesFin.getText().trim();
        String anioFinStr = txtAnioFin.getText().trim();

        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        lblFechaInicio.setText("");
        lblFechaFin.setText("");

        try {

            if (diaInicioStr.isBlank() || mesInicioStr.isBlank() || anioInicioStr.isBlank()
                    || diaFinStr.isBlank() || mesFinStr.isBlank() || anioFinStr.isBlank()){
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            int diaInicio = Integer.parseInt(diaInicioStr);
            int mesInicio = Integer.parseInt(mesInicioStr);
            int anioInicio = Integer.parseInt(anioInicioStr);
            int diaFin = Integer.parseInt(diaFinStr);
            int mesFin = Integer.parseInt(mesFinStr);
            int anioFin = Integer.parseInt(anioFinStr);

            fechaInicio = LocalDate.of(anioInicio, mesInicio, diaInicio);
            fechaFin = LocalDate.of(anioFin, mesFin, diaFin);

            lblFechaInicio.setText("Fecha de inicio: "+ fechaInicio);
            lblFechaFin.setText("Fecha de fin: "+ fechaFin);
            
            reporte.setFechaInicio(fechaInicio);
            reporte.setFechaFin(fechaFin);
            

            var ventasAMostrar = reporte.obtenerVentasSegunPeriodo();
            var ventasPeriodo = new VentasPeriodo(ventasAMostrar);
            ventasPeriodo.setVisible(true);
            ventasPeriodo.mostrarVentasEnTabla(ventasAMostrar);

        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico.");
            lblFechaInicio.setText("Error: Ingrese números válidos.");
            lblFechaFin.setText("Error: Ingrese números válidos.");
        } catch (DateTimeException e) {
            System.err.println("Error de fecha: " + e.getMessage());
            if (e.getMessage().contains("inicio")) {
                lblFechaInicio.setText("Error en Fecha Inicio: " + e.getMessage());
            } else if (e.getMessage().contains("fin")) {
                lblFechaFin.setText("Error en Fecha Fin: " + e.getMessage());
            } else {
                lblFechaInicio.setText("Error de fecha.");
                lblFechaFin.setText("Error de fecha.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            lblFechaInicio.setText("Error: " + e.getMessage());
            lblFechaFin.setText("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblAnioFin;
    private javax.swing.JLabel lblAnioInicio;
    private javax.swing.JLabel lblDiaFin;
    private javax.swing.JLabel lblDiaInicio;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaFinConsultar;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaInicioConsultar;
    private javax.swing.JLabel lblMesFin;
    private javax.swing.JLabel lblMesInicio;
    private javax.swing.JLabel lblSelecPeriodoTiempo;
    private javax.swing.JTextField txtAnioFin;
    private javax.swing.JTextField txtAnioInicio;
    private javax.swing.JTextField txtDiaFin;
    private javax.swing.JTextField txtDiaInicio;
    private javax.swing.JTextField txtMesFin;
    private javax.swing.JTextField txtMesInicio;
    // End of variables declaration//GEN-END:variables
}
