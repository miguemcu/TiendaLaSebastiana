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

    public Producto buscarProducto(String idONombre, String busqueda) throws Exception {
        switch (idONombre) {
            case ("ID") -> {
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

}
