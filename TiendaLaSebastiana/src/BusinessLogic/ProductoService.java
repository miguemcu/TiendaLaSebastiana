/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import Repository.RepoProductos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author migue
 */
public class ProductoService {

    private RepoProductos repositorio;

    public ProductoService() {
        this.repositorio = new RepoProductos();
    }

    public RepoProductos getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepoProductos repositorio) {
        this.repositorio = repositorio;
    }

    public boolean añadirProducto(String tipoProducto, String nombre, long id,
            double precioMayorista, double precio, LocalDate fechaDeVencimiento,
            ArrayList<String> etiquetas, int cantidad) throws Exception {

        return repositorio.añadirProducto(tipoProducto, nombre, id, precioMayorista, precio, fechaDeVencimiento, etiquetas, cantidad);
    }
    
    public Map<Producto, Integer> getProductos() throws Exception{
        return repositorio.getProductos();
    }

    public Producto buscarProducto(String idONombre, String busqueda) throws Exception {
        switch (idONombre) {
            case ("id") -> {
                long id = Long.parseLong(busqueda);
                for (Map.Entry<Producto, Integer> accesoProducto : repositorio.getProductos().entrySet()) {
                    Producto producto = accesoProducto.getKey();
                    if (producto.getId() == id) {
                        return producto;
                    }
                }
                return null;
            }
            case ("nombre") -> {

                for (Map.Entry<Producto, Integer> accesoProducto : repositorio.getProductos().entrySet()) {
                    Producto producto = accesoProducto.getKey();
                    if (producto.getNombre().equals(busqueda)) {
                        return producto;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public int getCantidadProducto(Long idProd) throws Exception {
        Map<Producto, Integer> productos = repositorio.getProductos();
        for (Map.Entry<Producto, Integer> productosAccedidos : productos.entrySet()) {
            var producto = productosAccedidos.getKey();
            var cantidad = productosAccedidos.getValue();
            if (producto.getId() == idProd) {
                return cantidad;
            }
        }
        return 0;
    }
    
    public boolean editarCantidadProducto(Producto producto, int nuevaCantidad) throws Exception {
        repositorio.editarCantidadProducto(producto, nuevaCantidad);
        return true;
    }

}
