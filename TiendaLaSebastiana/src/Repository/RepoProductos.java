/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import BusinessLogic.Aseo;
import BusinessLogic.Bebida;
import BusinessLogic.Enlatado;
import BusinessLogic.Granos;
import BusinessLogic.Mecato;
import BusinessLogic.Producto;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author migue
 */
public class RepoProductos {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public RepoProductos() {
        var client = MongoClients.create("mongodb://localhost:27017/");
        this.database = client.getDatabase("TiendaLaSebastiana");
        this.collection = database.getCollection("Productos");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
    
    public Map getProductos() throws Exception{
        var productosEncontrados = collection.find();
        Map<Producto,Integer> productos = new HashMap<>();
        
        try{
            for (Document doc: productosEncontrados){
                Document producto = (Document)doc.get("producto");
                String nombre = producto.getString("nombre");
                Long id = producto.getLong("id");
                Double precio = producto.getDouble("precio");
                Double precioMayorista = producto.getDouble("precioMayorista");
                String tipoProducto = producto.getString("tipoProducto");
                String fechaStr = producto.getString("fechaDeVencimiento");
                ArrayList<String> etiquetas = (ArrayList<String>)producto.get("etiquetas");
                int cantidad = doc.getInteger("cantidad");

                LocalDate fechaDeVencimiento = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);

                switch (tipoProducto) {
                case "Aseo":
                    Producto aseo = new Aseo(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                    productos.put(aseo, cantidad);
                case "Bebida":
                    Producto bebida = new Bebida(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                    productos.put(bebida, cantidad);
                case "Mecato":
                    Producto mecato = new Mecato(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                    productos.put(mecato, cantidad);
                case "Enlatado":
                    Producto enlatado = new Enlatado(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                    productos.put(enlatado, cantidad);
                case "Grano":
                    Producto grano = new Granos(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                    productos.put(grano, cantidad);
                }
            }
            return productos;
        }catch(Exception e){
            throw new Exception("Ha ocurrido un error, contacte al administrador: "+e.getMessage());
        }
    }
    
    
    }
    
    
  

