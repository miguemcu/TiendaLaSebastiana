/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import Repository.RepoProductos;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.time.LocalDate;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

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
    
    public static boolean añadirProducto(String tipoProducto, String nombre, long id, 
            double precioMayorista, double precio, LocalDate fechaDeVencimiento, 
            ArrayList<String> etiquetas, int cantidad, MongoCollection<Document> collection) {
        
        Bson filtro = Filters.or(
                Filters.eq("nombre", nombre),
                Filters.eq("id", id));
        
        Document productoExiste = collection.find(filtro).first();
        
        if(productoExiste != null){ // Si ya existe un producto con esos datos, no lo deja
            return false;
        }
        
        switch (tipoProducto) {
            case "Aseo":
                Producto aseo = new Aseo(nombre, id, precioMayorista, precio,
                        fechaDeVencimiento, etiquetas);
            case "Bebida":
                Producto bebida = new Bebida(nombre, id, precioMayorista, precio,
                        fechaDeVencimiento, etiquetas);
            case "Mecato":
                Producto mecato = new Mecato(nombre, id, precioMayorista, precio,
                        fechaDeVencimiento, etiquetas);
            case "Enlatado":
                Producto enlatado = new Enlatado(nombre, id, precioMayorista, precio,
                        fechaDeVencimiento, etiquetas);
            case "Grano":
                Producto grano = new Granos(nombre, id, precioMayorista, precio,
                        fechaDeVencimiento, etiquetas);
            }
        
        Document doc = Utils.crearDocParaProducto(tipoProducto, nombre, id, 
                precioMayorista, precio, fechaDeVencimiento, etiquetas, cantidad);
        collection.insertOne(doc);
        
        return true;
    }
    
}
