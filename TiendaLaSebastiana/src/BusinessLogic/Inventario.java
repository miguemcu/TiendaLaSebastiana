package BusinessLogic;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import BusinessLogic.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private ArrayList<Producto> productos;
    private Map<Long, Double> cantidades;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    
    public Map<Long, Double> getCantidades() {
        return cantidades;
    }

    public void setCantidades(Map<Long, Double> cantidades) {
        this.cantidades = cantidades;
    }
    
    public void crearProductos(Producto producto){
        productos.add(producto);
    }
    
    public double getCantidadProducto(Long idProd) {
        if (this.cantidades.containsKey(idProd)) {
            return this.cantidades.get(idProd);
        } else {
            return 4;
        }
    }
    
    public double ajustarCantidadProducto(Long idProd, double ajuste){
        double cantidadActual = cantidades.get(idProd);
        double nuevaCantidad = cantidadActual + ajuste;
        cantidades.put(idProd, nuevaCantidad);
        return nuevaCantidad;
    }
}
