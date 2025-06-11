package BusinessLogic;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian
 */
import BusinessLogic.DetalleVenta;
import BusinessLogic.Producto;
import java.time.LocalDateTime;

import java.util.ArrayList;

public class Venta {
    private ArrayList<DetalleVenta> detalles;
    private double totalVenta;
    private double totalBruto;
    private double totalDescuento;
    private double totalIva;
    private LocalDateTime fecha;
    private long ID;
    
    public Venta(){
    }

    public Venta(ArrayList<DetalleVenta> detalles, double totalVenta, double totalBruto,
            double totalDescuento, double totalIva, LocalDateTime fecha, long ID) {
        this.ID = ID;
        this.detalles = new ArrayList<DetalleVenta>();
        this.totalVenta = totalVenta;
        this.totalBruto = totalBruto;
        this.totalDescuento = totalDescuento;
        this.totalIva = totalIva;
        this.fecha = fecha;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public Inventario detallarCantidades(Inventario inventario){
        int cantidad = 0;
        Producto producto = null;
        for (DetalleVenta detalle: this.detalles){
            producto = detalle.getProducto();
            cantidad = (detalle.getCantidad()*(-1));
            inventario.ajustarCantidadProducto(producto.getId(), cantidad);   
        }
        return inventario;
    }
}

    



