/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package UI2;

import BusinessLogic.Producto;
import BusinessLogic.ProductoService;
import BusinessLogic.helperUI;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author DELL
 */
public class EdicionProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form CreacionProducto
     */
    private ProductoService productoService;
    private InventarioSistema inventarioSistema;
    private Producto productoEditar;

    public EdicionProducto() {
        initComponents();
    }

    public EdicionProducto(ProductoService productoService, InventarioSistema inventarioSistema) {
        initComponents();
        initServices(productoService);
        this.inventarioSistema = inventarioSistema;
        initComboTipoProd();
        ((AbstractDocument) txtDay.getDocument()).setDocumentFilter(new helperUI(2));
        ((AbstractDocument) txtMonth.getDocument()).setDocumentFilter(new helperUI(2));
        ((AbstractDocument) txtYear.getDocument()).setDocumentFilter(new helperUI(5));
        txtID.setEditable(false);
        this.setearEditCampos(false);
    }
//gettets y setters.

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public InventarioSistema getInventarioSistema() {
        return inventarioSistema;
    }

    public void setInventarioSistema(InventarioSistema inventarioSistema) {
        this.inventarioSistema = inventarioSistema;
    }

//metodos para la clase.
    public void setearEditCampos(boolean editable) {
        txtNombre.setEditable(editable);
        txtCantidad.setEditable(editable);
        txtPrecioMayorista.setEditable(editable);
        txtPrecio.setEditable(editable);
        txtEtiquetas.setEditable(editable);
        txtDay.setEditable(editable);
        txtMonth.setEditable(editable);
        txtYear.setEditable(editable);
        btnEliminar.setEnabled(editable);
        btnActualizar.setEnabled(editable);
    }

    public LocalDate capturarFechaVencimiento() {
        String yearStr = txtYear.getText().trim();
        String monthStr = txtMonth.getText().trim();
        String dayStr = txtDay.getText().trim();
        LocalDate fechaVencimiento = null;

        try {
            if (yearStr.isBlank() || monthStr.isBlank() || dayStr.isBlank()) {
                throw new IllegalArgumentException("Todos los campos de la fecha de vencimiento son obligatorios.");
            }
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            fechaVencimiento = LocalDate.of(year, month, day);
            txtErrorUpdate.setText("");

        } catch (NumberFormatException e) {
            txtErrorUpdate.setText("Error: Debe ingresar valores numéricos válidos para la fecha de vencimiento.");
        } catch (DateTimeException e) {
            txtErrorUpdate.setText("Error: Fecha de vencimiento inválida - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            txtErrorUpdate.setText(e.getMessage());
        } catch (Exception ex) {
            txtErrorUpdate.setText("Error inesperado al capturar la fecha de vencimiento: " + ex.getMessage());
        }
        return fechaVencimiento;
    }

    public void limpiarCampos() {
        txtID.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecioMayorista.setText("");
        txtPrecio.setText("");
        txtDay.setText("");
        txtMonth.setText("");
        txtYear.setText("");
        txtEtiquetas.setText("");
    }
    
    private void setearCampos(Producto producto) throws Exception {
        txtNombre.setText(producto.getNombre());
        txtID.setText(String.valueOf(producto.getId()));
        int cantidad = this.getProductoService().getCantidadProducto(producto.getId());
        txtCantidad.setText(Double.toString(cantidad));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtPrecioMayorista.setText(String.valueOf(producto.getPrecioMayorista()));
        String[] partesFecha = producto.getFechaDeVencimiento().toString().split("-");
        txtDay.setText(partesFecha[2]);
        txtMonth.setText(partesFecha[1]);
        txtYear.setText(partesFecha[0]);
        StringBuilder etiquetas = new StringBuilder();
        if (producto.getEtiquetas() != null){
            for (String etiqueta : producto.getEtiquetas()){
            etiquetas.append(etiqueta).append(",");
            }
        }else{
            etiquetas.append("");
        }
        
        txtEtiquetas.setText(etiquetas.toString());
        switch (producto.getTipoProducto().toString().toUpperCase()){
            case ("ASEO"):
                txtTipoProd.setSelectedIndex(0);
                break;
            case ("BEBIDA"):
                txtTipoProd.setSelectedIndex(1);
                break;
            case ("MECATO"):
                txtTipoProd.setSelectedIndex(2);
                break;
            case ("GRANOS"):
                txtTipoProd.setSelectedIndex(3);
                break;
            case ("ENLATADOS"):
                txtTipoProd.setSelectedIndex(4);
                break;
        }
        this.setearEditCampos(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtYear = new javax.swing.JTextField();
        lblDia = new javax.swing.JLabel();
        lblMes = new javax.swing.JLabel();
        lblAño = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtEtiquetas = new javax.swing.JTextField();
        lblEtiqueta = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblIndicacionEtiquetas = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecioMayorista = new javax.swing.JLabel();
        txtPrecioMayorista = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblTipoProd = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JToggleButton();
        ScrollMensajesProductos = new javax.swing.JScrollPane();
        txtErrorUpdate = new javax.swing.JTextArea();
        lblFechaVencimiento = new javax.swing.JLabel();
        txtDay = new javax.swing.JTextField();
        txtMonth = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JToggleButton();
        lblBuscar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JToggleButton();
        txtBuscar = new javax.swing.JTextField();
        txtTipoProd = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);

        lblDia.setText("DD");

        lblMes.setText("MM");

        lblAño.setText("YYYY");

        lblID.setText("ID : ");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEtiquetasActionPerformed(evt);
            }
        });

        lblEtiqueta.setText("Etiqueta :");

        lblNombre.setText("Nombre : ");

        lblIndicacionEtiquetas.setText("#Escriba asi: etiqueta1,etiqueta2,...,etiquetak");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblCantidad.setText("Cantidad :");

        lblPrecioMayorista.setText("Precio Mayorista :");

        lblPrecio.setText("Precio Menor :");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        lblTipoProd.setText("Tipo Producto :");

        btnActualizar.setBackground(new java.awt.Color(0, 255, 153));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        ScrollMensajesProductos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollMensajesProductos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollMensajesProductos.setAutoscrolls(true);

        txtErrorUpdate.setColumns(20);
        txtErrorUpdate.setRows(5);
        ScrollMensajesProductos.setViewportView(txtErrorUpdate);

        lblFechaVencimiento.setText("Fecha de Vencimiento : ");

        txtDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDayActionPerformed(evt);
            }
        });

        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblBuscar.setText("Ingrese el ID o el Nombre del producto:");

        btnEliminar.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        txtTipoProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aseo", "Bebida", "Mecato", "Granos", "Enlatado" }));
        txtTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollMensajesProductos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addGap(12, 12, 12)
                        .addComponent(lblIndicacionEtiquetas)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaVencimiento))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txtEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblDia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblAño, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidad)
                            .addComponent(lblNombre)
                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecioMayorista))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioMayorista)
                    .addComponent(txtPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoProd)
                    .addComponent(txtTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaVencimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDia)
                    .addComponent(lblMes)
                    .addComponent(lblAño))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqueta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIndicacionEtiquetas)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(ScrollMensajesProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEtiquetasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEtiquetasActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        try {
            String nombre = txtNombre.getText().trim();
            String id = txtID.getText().trim();
            String Cantidad = txtCantidad.getText().trim();
            String PrecioMayorista = txtPrecioMayorista.getText().trim();
            String Precio = txtPrecio.getText().trim();
            String tipoSeleccionado = (String) txtTipoProd.getSelectedItem();
            String dia = txtDay.getText().trim();
            String mes = txtMonth.getText().trim();
            String annio = txtYear.getText().trim();
            String textoEtiquetas = txtEtiquetas.getText().trim();

            if (nombre.isBlank() || id.isBlank() || Cantidad.isBlank()
                    || PrecioMayorista.isBlank() || Precio.isBlank() || tipoSeleccionado == null
                    || dia.isBlank() || mes.isBlank() || annio.isBlank()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s]+")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }

            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("El ID solo debe contener números.");
            }

            // Verificamos que Cantidad solo contenga números (y opcionalmente punto decimal)
            if (!Cantidad.matches("\\d+(\\.\\d+)?")) {
                throw new IllegalArgumentException("La cantidad debe ser un número válido.");
            }

            // Verificamos que Precio Mayor solo contenga números (y opcionalmente punto decimal)
            if (!PrecioMayorista.matches("\\d+(\\.\\d+)?")) {
                throw new IllegalArgumentException("El precio mayor debe ser un número válido.");
            }

            // Verificamos que Precio Menor solo contenga números (y opcionalmente punto decimal)
            if (!Precio.matches("\\d+(\\.\\d+)?")) {
                throw new IllegalArgumentException("El precio menor debe ser un número válido.");
            }
            if (!dia.matches("\\d+")) {
                throw new IllegalArgumentException("El día solo debe contener números.");
            }
            if (!mes.matches("\\d+")) {
                throw new IllegalArgumentException("El mes solo debe contener números.");
            }
            if (!annio.matches("\\d+")) {
                throw new IllegalArgumentException("El año solo debe contener números.");
            }
            if (!textoEtiquetas.isBlank() && !textoEtiquetas.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s,]*")) {
                throw new IllegalArgumentException("Las etiquetas solo puede contener letras y espacios.");
            }

            long Id = Long.parseLong(id);
            int cantidad = Integer.parseInt(Cantidad);
            double precio = Double.parseDouble(Precio);
            double precioMayorista = Double.parseDouble(PrecioMayorista);

            if (this.capturarFechaVencimiento() == null) {
                txtErrorUpdate.setText("Fecha inválida. Por favor ingrese de nuevo.");
                return;
            }

            LocalDate fechaVencimiento = this.capturarFechaVencimiento();

            ArrayList<String> etiquetas = new ArrayList<>();
            String[] etiquetasArray = textoEtiquetas.split(",");
            for (String etiqueta : etiquetasArray) {
                etiquetas.add(etiqueta.trim());
            }
            if (precio < precioMayorista) {
                throw new IllegalArgumentException("El precio por mayor debe ser menor o igual al precio por menor.");
            }

            if (Integer.parseInt(txtYear.getText().trim()) < Year.now().getValue()) {
                throw new IllegalArgumentException("La fecha de vencimiento es incorrecta.");
            }
            var p = this.productoEditar;
            if (this.getProductoService().actualizarProducto(tipoSeleccionado, nombre, p.getId(), precio,
                    precioMayorista, fechaVencimiento, etiquetas, cantidad) == false) {
                throw new Exception("No se pudo actualizar el producto");
            }

            this.limpiarCampos();
            this.dispose();
            InventarioSistema inventarioSistema = new InventarioSistema(this.productoService);
            inventarioSistema.setVisible(true);

        } catch (IllegalArgumentException ex) {
            txtErrorUpdate.setText(ex.getMessage());
        } catch (Exception ex) {
            txtErrorUpdate.setText("Error inesperado: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        limpiarCampos();
        String busqueda = txtBuscar.getText();
        try {
            if (busqueda.isEmpty() || busqueda.isBlank()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }
            boolean encontrado = false;
            Producto producto;
            if (busqueda.matches("\\d+")) {
                producto = this.getProductoService().buscarProducto("id", busqueda);
                if (producto != null) {
                    setearCampos(producto);
                    encontrado = true;
                    this.productoEditar = producto;
                } else {
                    encontrado = false;
                }

            } else {
                producto = this.getProductoService().buscarProducto("nombre", busqueda);
                if (producto != null) {
                    setearCampos(producto);
                    encontrado = true;
                    this.productoEditar = producto;
                } else {
                    encontrado = false;
                }
            }

            if (!encontrado) {
                throw new NoSuchElementException("Producto no encontrado.");
            }
            txtErrorUpdate.setText("");

        } catch (IllegalArgumentException | NoSuchElementException e) {
            txtErrorUpdate.setText(e.getMessage());
        } catch (Exception e) {
            txtErrorUpdate.setText("Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            this.productoService.eliminarProducto(this.productoEditar.getId());
        } catch (Exception ex) {
            Logger.getLogger(EdicionProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMonthActionPerformed

    private void txtDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDayActionPerformed

    private void txtTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoProdActionPerformed

    }//GEN-LAST:event_txtTipoProdActionPerformed

    private void initServices(ProductoService productoService) {
        this.productoService = productoService;
    }

    private void initComboTipoProd() {
        txtTipoProd.setModel(new DefaultComboBoxModel<>(new String[]{
            "Bebida", "Mecato", "Aseo", "Enlatado", "Grano"
        }));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollMensajesProductos;
    private javax.swing.JToggleButton btnActualizar;
    private javax.swing.JToggleButton btnBuscar;
    private javax.swing.JToggleButton btnEliminar;
    private javax.swing.JLabel lblAño;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblEtiqueta;
    private javax.swing.JLabel lblFechaVencimiento;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIndicacionEtiquetas;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecioMayorista;
    private javax.swing.JLabel lblTipoProd;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDay;
    private javax.swing.JTextArea txtErrorUpdate;
    private javax.swing.JTextField txtEtiquetas;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMonth;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioMayorista;
    private javax.swing.JComboBox<String> txtTipoProd;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
