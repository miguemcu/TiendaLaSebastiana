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
import BusinessLogic.Utils;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author migue
 */
public class RepoProductos {

    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public RepoProductos() {
        var client = MongoClients.create("mongodb+srv://miguemcu:admin@clusterejemploinicial.rvaw771.mongodb.net/");
        this.database = client.getDatabase("TiendaLaSebastiana");
        this.collection = database.getCollection("Productos");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public Map<Producto, Integer> getProductos() throws Exception {
        var productosEncontrados = collection.find();
        Map<Producto, Integer> productos = new HashMap<>();

        try {
            for (Document doc : productosEncontrados) {
                Document producto = (Document) doc.get("producto");
                String nombre = producto.getString("nombre");
                Long id = producto.getLong("id");
                Double precio = producto.getDouble("precio");
                Double precioMayorista = producto.getDouble("precioMayorista");
                String tipoProducto = producto.getString("tipoProducto");
                String fechaStr = producto.getString("fechaDeVencimiento");
                ArrayList<String> etiquetas = (ArrayList<String>) producto.get("etiquetas");
                int cantidad = doc.getInteger("cantidad");

                LocalDate fechaDeVencimiento = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);

                switch (tipoProducto.toUpperCase()) {
                    case ("ASEO"):
                        Producto aseo = new Aseo(nombre, id, precioMayorista, precio,
                                fechaDeVencimiento, etiquetas);
                        productos.put(aseo, cantidad);
                        break;
                    case ("BEBIDA"):
                        Producto bebida = new Bebida(nombre, id, precioMayorista, precio,
                                fechaDeVencimiento, etiquetas);
                        productos.put(bebida, cantidad);
                        break;
                    case ("MECATO"):
                        Producto mecato = new Mecato(nombre, id, precioMayorista, precio,
                                fechaDeVencimiento, etiquetas);
                        productos.put(mecato, cantidad);
                        break;
                    case ("ENLATADO"):
                        Producto enlatado = new Enlatado(nombre, id, precioMayorista, precio,
                                fechaDeVencimiento, etiquetas);
                        productos.put(enlatado, cantidad);
                        break;
                    case ("GRANOS"):
                        Producto grano = new Granos(nombre, id, precioMayorista, precio,
                                fechaDeVencimiento, etiquetas);
                        productos.put(grano, cantidad);
                        break;
                }
            }
            return productos;
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error, contacte al administrador: " + e.getMessage());
        }
    }

    public boolean añadirProducto(String tipoProducto, String nombre, long id,
            double precioMayorista, double precio, LocalDate fechaDeVencimiento,
            ArrayList<String> etiquetas, int cantidad) throws Exception {
        try {
            Bson filtro = Filters.or(
                    Filters.eq("producto.nombre", nombre),
                    Filters.eq("producto.id", id));

            Document producto = collection.find(filtro).first();

            if (producto != null) { // Si ya existe un producto con esos datos, no lo deja
                return false;
            }

            switch (tipoProducto.toUpperCase()) {
                case "ASEO":
                    Producto aseo = new Aseo(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                case "BEBIDA":
                    Producto bebida = new Bebida(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                case "MECATO":
                    Producto mecato = new Mecato(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                case "ENLATADOS":
                    Producto enlatado = new Enlatado(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
                case "GRANOS":
                    Producto grano = new Granos(nombre, id, precioMayorista, precio,
                            fechaDeVencimiento, etiquetas);
            }

            Document doc = Utils.crearDocParaProducto(tipoProducto, nombre, id,
                    precioMayorista, precio, fechaDeVencimiento, etiquetas, cantidad);
            collection.insertOne(doc);

            return true;
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error, por favor contacte al administrador");
        }
    }

    public void editarCantidadProducto(Producto producto, int cantidad) throws Exception {
        try {
            Bson filtro = Filters.eq("producto.nombre", producto.getNombre());
            Bson cantidadNueva = Updates.set("cantidad", cantidad);
            collection.updateOne(filtro, cantidadNueva);
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error, por favor contacte al administrador");
        }
    }
}
