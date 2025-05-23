package BusinessLogic;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import BusinessLogic.Aseo;
import BusinessLogic.Bebida;
import BusinessLogic.Enlatado;
import BusinessLogic.Granos;
import BusinessLogic.Mecato;
import BusinessLogic.Producto;
import java.time.LocalDate;
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

    public void crearProductos(String tipoSeleccionado, String nombre, long Id, double precioMayorista, double precio,
            LocalDate fechaVencimiento, ArrayList<String> etiquetas, double cantidad) {
        switch (tipoSeleccionado) {
            case "Aseo":
                Aseo aseo = new Aseo(nombre, Id, precioMayorista, precio,
                        fechaVencimiento, etiquetas);
                this.getProductos().add(aseo);
            case "Bebida":
                Bebida bebida = new Bebida(nombre, Id, precioMayorista, precio,
                        fechaVencimiento, etiquetas);
                this.getProductos().add(bebida);
            case "Mecato":
                Mecato mecato = new Mecato(nombre, Id, precioMayorista, precio,
                        fechaVencimiento, etiquetas);
                this.getProductos().add(mecato);
            case "Enlatado":
                Enlatado enlatado = new Enlatado(nombre, Id, precioMayorista, precio,
                        fechaVencimiento, etiquetas);
                this.getProductos().add(enlatado);
            case "Grano":
                Granos grano = new Granos(nombre, Id, precioMayorista, precio,
                        fechaVencimiento, etiquetas);
                this.getProductos().add(grano);
        }
        this.getCantidades().put(Id, cantidad);
    }

    public Producto buscarProductos(String ID, String busqueda) {
        switch (ID) {
            case ("ID") -> {
                long id = Long.parseLong(busqueda);
                for (Producto producto : productos) {
                    if (producto.getId() == id) {
                        return producto;
                    }
                }
                return null;
            }
            case ("nombre") -> {

                for (Producto producto : productos) {
                    if (producto.getNombre().equals(busqueda)) {
                        return producto;
                    }
                }
                return null;
            }
        }
        return null;
    }
    
    public double getCantidadProducto(Long idProd) {
        if (this.cantidades.containsKey(idProd)) {
            return this.cantidades.get(idProd);
        } else {
            return 4;
        }
    }

    public double ajustarCantidadProducto(Long idProd, double ajuste) {
        double cantidadActual = cantidades.get(idProd);
        double nuevaCantidad = cantidadActual + ajuste;
        cantidades.put(idProd, nuevaCantidad);
        return nuevaCantidad;
    }
}
