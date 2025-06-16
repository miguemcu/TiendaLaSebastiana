/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package UI2;

import BusinessLogic.DetalleVenta;
import BusinessLogic.EmpleadoService;
import BusinessLogic.Producto;
import BusinessLogic.ProductoService;
import BusinessLogic.Utils;
import BusinessLogic.Venta;
import BusinessLogic.VentaService;
import BusinessLogic.helperUI;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author DELL
 */
public class MenuVenta extends javax.swing.JInternalFrame {

    private EmpleadoService empleadoService;
    private ProductoService productoService;
    private VentaService ventaService;
    private DefaultTableModel modeloTabla;
    private Venta venta;
    private Recibo recibo;
    private JDesktopPane desktopPane;
    private ArrayList<javax.swing.JTextField> camposEditables;

    /**
     * Creates new form Venta
     */
    public MenuVenta() {
        initComponents();
    }

    public MenuVenta(EmpleadoService empleadoService, ProductoService productoService,
            VentaService ventaService, JDesktopPane desktopPane) {
        initComponents();
        ((AbstractDocument) txtBuscar.getDocument()).setDocumentFilter(new helperUI(20));
        ((AbstractDocument) txtCantidadVender.getDocument()).setDocumentFilter(new helperUI(4));
        ((AbstractDocument) txtIVA.getDocument()).setDocumentFilter(new helperUI(5));
        ((AbstractDocument) txtDescuento.getDocument()).setDocumentFilter(new helperUI(5));
        initServices(empleadoService, productoService, ventaService);
        initJTable();
        this.venta = new Venta();
        this.venta.setDetalles(new ArrayList<>());
        this.desktopPane = desktopPane;
    }

    //Setter y Getter
    public ArrayList<JTextField> getCamposEditables() {
        return camposEditables;
    }

    public void setCamposEditables(ArrayList<JTextField> camposEditables) {
        this.camposEditables = camposEditables;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public VentaService getVentaService() {
        return ventaService;
    }

    public void setVentaService(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public JTable getTblProductosAgregados() {
        return tblProductosAgregados;
    }

    public void setTblProductosAgregados(JTable tblProductosAgregados) {
        this.tblProductosAgregados = tblProductosAgregados;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return venta.getDetalles();
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.venta.setDetalles(detalles);
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    @Override
    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    //Metodos para la clase
    private void setearCampos(Producto producto) throws Exception {
        txtNombreProducto.setEditable(false);
        txtCantidadDisponible.setEditable(false);
        txtPrecioUnitario.setEditable(false);
        txtCantidadVender.setEditable(true);
        txtNombreProducto.setText(producto.getNombre());
        txtCantidadDisponible.setText(String.valueOf(this.getProductoService().getCantidadProducto(producto.getId())));
        txtPrecioUnitario.setText(String.valueOf(producto.getPrecio()));
        txtTotalVenta.setEditable(false);
    }

    public LocalDateTime capturarFecha() {
        String diaStr = txtDia.getText().trim();
        String mesStr = txtMes.getText().trim();
        String anioStr = txtAnio.getText().trim();
        String horaStr = txtHora.getText().trim();
        String minutosStr = txtMinuto.getText().trim();

        LocalDateTime fecha = null;

        try {
            if (diaStr.isBlank() || mesStr.isBlank() || anioStr.isBlank()
                    || horaStr.isBlank() || minutosStr.isBlank()) {
                throw new IllegalArgumentException("Todos los campos de fecha y hora son obligatorios.");
            }

            int dia = Integer.parseInt(diaStr);
            int mes = Integer.parseInt(mesStr);
            int anio = Integer.parseInt(anioStr);
            int hora = Integer.parseInt(horaStr);
            int minutos = Integer.parseInt(minutosStr);

            fecha = LocalDateTime.of(anio, mes, dia, hora, minutos);

        } catch (NumberFormatException e) {
            txtError.setText("Error: Debe ingresar valores numéricos válidos para la fecha y hora.");
            fecha = null;
        } catch (DateTimeException e) {
            txtError.setText("Fecha y hora inválida: " + e.getMessage());
            fecha = null;
        } catch (IllegalArgumentException e) {
            txtError.setText(e.getMessage());
            fecha = null;
        } catch (Exception ex) {
            txtError.setText("Error inesperado: " + ex.getMessage());
            fecha = null;
        }

        return fecha;
    }

    public void limpiarCampos() {
        ArrayList<javax.swing.JTextField> campos = new ArrayList<>();
        campos.add(txtNombreProducto);
        campos.add(txtPrecioUnitario);
        campos.add(txtCantidadDisponible);
        campos.add(txtCantidadVender);
        campos.add(txtIVA);
        campos.add(txtDescuento);
        helperUI.limpiarCampos(campos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMes = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        lblPrecioUnitario = new javax.swing.JLabel();
        txtDia = new javax.swing.JTextField();
        lblTtlVenta = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        lblCantidadAVender = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        btnAgregarVenta = new javax.swing.JButton();
        txtMinuto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        txtPrecioUnitario = new javax.swing.JTextField();
        lblHora = new javax.swing.JLabel();
        txtCantidadDisponible = new javax.swing.JTextField();
        lblMinutos = new javax.swing.JLabel();
        txtCantidadVender = new javax.swing.JTextField();
        lblProductosAgregados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosAgregados = new javax.swing.JTable();
        lblCantidadDisponible = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();
        btnCancelarVenta = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();
        lblInsucienteStock = new javax.swing.JLabel();
        scrollVenta = new javax.swing.JScrollBar();
        lblDia = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblIngresarFecha = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        lblBuscarProducto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        lblIVA = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        lblCantidadAVender1 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lblPercent = new javax.swing.JLabel();
        lblPercent2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        lblMes.setText("MM");

        lblFecha.setText("Fecha:");

        lblNombreProducto.setText("Nombre Producto:");

        lblAnio.setText("AAAA");

        lblPrecioUnitario.setText("Precio Unitario:");

        lblTtlVenta.setText("Total Venta:");

        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        lblCantidadAVender.setText("Cantidad a vender:");

        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });

        btnAgregarVenta.setText("Agregar a la Venta");
        btnAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVentaActionPerformed(evt);
            }
        });

        txtMinuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinutoActionPerformed(evt);
            }
        });

        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });

        lblHora.setText("hh");

        txtCantidadDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadDisponibleActionPerformed(evt);
            }
        });

        lblMinutos.setText("mm");

        txtCantidadVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVenderActionPerformed(evt);
            }
        });

        lblProductosAgregados.setText("Productos Agregados:");

        tblProductosAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Precio Unitario", "Cantidad", "Subtotal Bruto", "IVA Generado", "Descuento Generado", "Subtotal Neto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductosAgregados);

        lblCantidadDisponible.setText("Cantidad Disponible:");

        txtTotalVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalVentaActionPerformed(evt);
            }
        });

        btnCancelarVenta.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelarVenta.setText("Cancelar Venta");
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });

        btnVender.setBackground(new java.awt.Color(51, 153, 255));
        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        lblInsucienteStock.setVisible(false);
        lblInsucienteStock.setText("Insuficiente Stock");

        lblDia.setText("DD");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        lblIngresarFecha.setText("Ingrese la fecha:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblBuscarProducto.setText("Buscar Producto:");

        txtError.setColumns(20);
        txtError.setRows(5);
        jScrollPane2.setViewportView(txtError);

        lblIVA.setText("Ingrese el porcentaje de IVA que se le aplica al producto:");

        txtIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIVAActionPerformed(evt);
            }
        });

        lblCantidadAVender1.setText("Ingrese el descuento aplicado: ");

        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });

        lblPercent.setText("%");

        lblPercent2.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregarVenta)
                                .addGap(0, 474, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductosAgregados)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTtlVenta)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblIngresarFecha)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFecha)
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblDia)
                                                        .addGap(25, 25, 25)
                                                        .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(23, 23, 23)
                                                        .addComponent(lblHora)
                                                        .addGap(27, 27, 27)
                                                        .addComponent(lblMinutos))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNombreProducto)
                                            .addComponent(lblPrecioUnitario)
                                            .addComponent(lblCantidadDisponible))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantidadDisponible, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCantidadAVender)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblInsucienteStock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCantidadVender, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPercent))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIVA)
                                    .addComponent(lblCantidadAVender1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPercent2))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(270, 270, 270))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblBuscarProducto)
                    .addContainerGap(630, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(scrollVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreProducto)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrecioUnitario)
                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadDisponible))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadAVender)
                            .addComponent(txtCantidadVender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIVA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPercent))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCantidadAVender1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPercent2)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblInsucienteStock)
                        .addGap(42, 42, 42)
                        .addComponent(btnAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(lblProductosAgregados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTtlVenta)
                            .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIngresarFecha))
                    .addComponent(btnVender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDia)
                            .addComponent(lblMes)
                            .addComponent(lblAnio)
                            .addComponent(lblHora)
                            .addComponent(lblMinutos)
                            .addComponent(lblFecha)))
                    .addComponent(btnCancelarVenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblBuscarProducto)
                    .addContainerGap(710, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void btnAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVentaActionPerformed
        try {
            int cantidadVender = Integer.parseInt(txtCantidadVender.getText());
            int cantidadDisponible = Integer.parseInt(txtCantidadDisponible.getText());
            if (txtCantidadVender.getText().isBlank() || txtIVA.getText().isBlank()
                    || txtDescuento.getText().isBlank()) {
                txtError.setText("Ingrese correctamente la información.");
                throw new IllegalArgumentException("Ingrese correctamente la información.");
            }

            if (!txtCantidadVender.getText().matches("\\d+")) {
                throw new IllegalArgumentException("La cantidad solo debe contener números positivos.");
            }
            if (!txtIVA.getText().matches("^\\d+(\\.\\d+)?$")) {
                throw new IllegalArgumentException("La casilla de IVA solo debe contener números positivos.");
            }

            double descuento = (Double.parseDouble(txtDescuento.getText()) / 100);

            if (!txtDescuento.getText().matches("^\\d+(\\.\\d+)?$") || descuento > 1) {
                throw new IllegalArgumentException("El descuento debe ser un número positivo y no mayor a 100.");
            }

            double iva = (Double.parseDouble(txtIVA.getText()) / 100);
            Producto producto = this.buscarAction();
            double precio = 0;
            double subtotalBruto = 0;
            double subtotalIVA = 0;
            double subtotalNeto = 0;

            if (cantidadDisponible < cantidadVender) {
                this.lblInsucienteStock.setVisible(true);
            } else {

                precio = this.getVentaService().calculaPrecio(cantidadVender, producto);

                subtotalBruto = this.getVentaService().calcularPrecioBruto(precio, cantidadVender);
                subtotalNeto = this.getVentaService().calcularPrecioNeto(subtotalBruto, iva, descuento);

                this.agregarProductoTabla(producto.getId(), producto.getNombre(), precio,
                        cantidadVender, subtotalBruto, this.getVentaService().calcularIVAGenerado(subtotalBruto, iva),
                        this.getVentaService().calcularDescuentoGenerado(subtotalBruto, iva, descuento),
                        subtotalNeto);

                var detalleVenta = new DetalleVenta(producto, cantidadVender, precio, subtotalBruto, iva, descuento, subtotalNeto);
                venta.getDetalles().add(detalleVenta);
            }

            txtTotalVenta.setText(String.valueOf(this.sumatoriaColumna(tblProductosAgregados, 7)));
            this.limpiarCampos();

            if (modeloTabla.getRowCount() > 0) {
                lblInsucienteStock.setVisible(false);

            }

        } catch (IllegalArgumentException e) {
            txtError.setText(e.getMessage());
        } catch (Exception ex) {
            txtError.setText("Error inesperado: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarVentaActionPerformed

    private void txtMinutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinutoActionPerformed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed

    private void txtCantidadDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadDisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDisponibleActionPerformed

    private void txtCantidadVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVenderActionPerformed

    private void txtTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentaActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        this.txtTotalVenta.setText("");
        this.limpiarCampos();
        this.modeloTabla.setRowCount(0);
        this.dispose();
    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        try {
            this.venderAction();
        } catch (Exception ex) {
            Logger.getLogger(MenuVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private boolean venderAction() throws Exception {
        txtTotalVenta.setText(String.valueOf(this.sumatoriaColumna(tblProductosAgregados, 7)));
        Long id = Utils.generarIdUnico(this.getVentaService().getVentas());
        LocalDateTime fecha = capturarFecha();

        if (fecha == null) {
            txtError.setText("Fecha inválida, ingrese de nuevo.");
            return false;
        }

        venta.setTotalVenta(this.sumatoriaColumna(tblProductosAgregados, 7));
        venta.setTotalBruto(this.sumatoriaColumna(tblProductosAgregados, 4));
        venta.setTotalDescuento(this.sumatoriaColumna(tblProductosAgregados, 6));
        venta.setTotalIva(this.sumatoriaColumna(tblProductosAgregados, 5));
        venta.setFecha(fecha);
        venta.setID(id);

        try {
            this.getVentaService().añadirVenta(venta);
        } catch (Exception ex) {
            txtError.setText("Error inesperado: No se pudo registrar la venta");
            return false;
        }

        this.getProductoService().ajustarCantidades(venta);

        this.dispose();

        if (this.getRecibo() == null) {
            this.setRecibo(new Recibo(this, this.getVenta()));
            this.getDesktopPane().add(this.getRecibo());
        }
        if (!this.getRecibo().isVisible()) {
            if (this.getRecibo().isClosed()) {
                this.getDesktopPane().add(this.getRecibo());
            }
            this.getRecibo().setVisible(true);
        }
        this.txtTotalVenta.setText("");
        this.limpiarCampos();
        this.modeloTabla.setRowCount(0);
        return true;
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscarAction();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private Producto buscarAction() {
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
                    for (DetalleVenta detalle : venta.getDetalles()) {
                        if (producto.getId() == detalle.getProducto().getId()) {
                            throw new NoSuchElementException("Producto ya agregado.");
                        }
                    }
                    setearCampos(producto);
                    encontrado = true;
                    return producto;
                } else {
                    encontrado = false;
                }

            } else {
                producto = this.getProductoService().buscarProducto("nombre", busqueda);
                if (producto != null) {
                    for (DetalleVenta detalle : venta.getDetalles()) {
                        if (producto.getId() == detalle.getProducto().getId()) {
                            throw new NoSuchElementException("Producto ya agregado.");
                        }
                    }
                    setearCampos(producto);
                    encontrado = true;
                    return producto;
                } else {
                    encontrado = false;
                }
            }

            if (!encontrado) {
                throw new NoSuchElementException("Producto no encontrado.");
            }
            txtError.setText("");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            txtError.setText(e.getMessage());
        } catch (Exception e) {
            txtError.setText("Error inesperado: " + e.getMessage());
        }
        return null;
    }

    private void txtIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAActionPerformed

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed
    public double sumatoriaColumna(JTable tabla, int columnIndex) {
        double suma = 0.0;
        int rowCount = modeloTabla.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object value = modeloTabla.getValueAt(i, columnIndex);

            if (value instanceof Double) {
                suma += (Double) value;
            }

            if (value instanceof String) {
                String strValue = (String) value;
                try {
                    suma += Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    txtError.setText("Error inesperado: " + e.getMessage());
                }
            }
        }
        return suma;
    }

    public void agregarProductoTabla(Long id, String nombre, double precioUnitario, int cantidad,
            double subtotalBruto, double iva, double descuento, double subtotalNeto) {

        Object[] rowData = {
            id,
            nombre,
            precioUnitario,
            cantidad,
            String.format("%.2f", subtotalBruto),
            String.format("%.2f", iva),
            String.format("%.2f", descuento),
            String.format("%.2f", subtotalNeto)
        };

        modeloTabla.addRow(rowData);
    }

    private void initServices(EmpleadoService empleadoService, ProductoService productoService,
            VentaService ventaService) {
        this.empleadoService = empleadoService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    private void initJTable() {
        modeloTabla = new DefaultTableModel();
        tblProductosAgregados.setModel(modeloTabla);
        String[] newColumnNames = {"ID", "Nombre", "Precio Unitario", "Cantidad",
            "Subtotal Bruto", "IVA Generado", "Descuento Generado", "Subtotal Neto"};
        modeloTabla.setColumnIdentifiers(newColumnNames);
        modeloTabla = new DefaultTableModel(newColumnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProductosAgregados.setModel(modeloTabla);
    }

    public void crearCamposDisponibles() {
        javax.swing.JTextField txtAnio;
        javax.swing.JTextField txtBuscar;
        javax.swing.JTextField txtCantidadDisponible;
        javax.swing.JTextField txtCantidadVender;
        javax.swing.JTextField txtDescuento;
        javax.swing.JTextField txtDia;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarVenta;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnVender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblBuscarProducto;
    private javax.swing.JLabel lblCantidadAVender;
    private javax.swing.JLabel lblCantidadAVender1;
    private javax.swing.JLabel lblCantidadDisponible;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblIngresarFecha;
    private javax.swing.JLabel lblInsucienteStock;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMinutos;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPercent;
    private javax.swing.JLabel lblPercent2;
    private javax.swing.JLabel lblPrecioUnitario;
    private javax.swing.JLabel lblProductosAgregados;
    private javax.swing.JLabel lblTtlVenta;
    private javax.swing.JScrollBar scrollVenta;
    private javax.swing.JTable tblProductosAgregados;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidadDisponible;
    private javax.swing.JTextField txtCantidadVender;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextArea txtError;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtMinuto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtTotalVenta;
    // End of variables declaration//GEN-END:variables

}
