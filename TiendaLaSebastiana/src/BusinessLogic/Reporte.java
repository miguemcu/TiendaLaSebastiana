/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author migue
 */
public class Reporte {
    private Caja caja;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reporte(Caja caja) {
        this.caja = caja;
        this.fechaInicio = null;
        this.fechaFin = null;
    }
    
    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    public ArrayList<Venta> obtenerVentasSegunPeriodo(){
        ArrayList<Venta> ventasFiltradas = caja.getVentas().stream()
        .filter(v -> {
            LocalDate fechaVenta = v.getFecha().toLocalDate();
            return !fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin);
        })
        .collect(Collectors.toCollection(ArrayList::new));
        
        return ventasFiltradas;
    }
    
        public Map<Long, Double> filtrarStack() {
        Map<Long, Double> cantidadesFiltradas = new HashMap<>();

        for (Map.Entry<Long, Double> acceso : caja.getInventario().getCantidades().entrySet()) {
            Long productoId = acceso.getKey();
            Double cantidad = acceso.getValue();
            if (cantidad < 5 || cantidad > 20) {
                cantidadesFiltradas.put(productoId, cantidad);
            }
        }

        return cantidadesFiltradas;
    }
    
    public ArrayList<Producto> filtrarProductos(Map<Long, Double> cantidadesFiltradas) {
        ArrayList<Producto> productosFiltrados = new ArrayList<>();

        for (Producto producto : caja.getInventario().getProductos()) {
            Long productoId = producto.getId();
            if (cantidadesFiltradas.containsKey(productoId)) {
                productosFiltrados.add(producto);
            }
        }

        return productosFiltrados;
    }
}
