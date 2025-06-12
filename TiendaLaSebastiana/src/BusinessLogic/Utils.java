/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.bson.BsonInt64;
import org.bson.Document;

/**
 *
 * @author migue
 */
public class Utils {

    public static Long generarIdUnico(ArrayList<Venta> ventas) {
        Random random = new Random();
        Long nuevoId;
        boolean existe;

        do {
            nuevoId = random.nextLong();
            if (nuevoId < 0) { // Por si me generó un número Negativo
                nuevoId = -nuevoId;
            }

            existe = false;
            for (Venta venta : ventas) {
                if (venta.getID() == nuevoId) {
                    existe = true;
                    break;
                }
            }
        } while (existe);

        return nuevoId;
    }

    public static String mostrarMenu(Producto producto) {
        StringBuilder ficha = new StringBuilder();
        ficha.append("=== Ficha Producto ===\n");
        ficha.append("Nombre: ").append(producto.getNombre()).append("\n");
        ficha.append("ID: ").append(producto.getId()).append("\n");
        ficha.append("Precio: $").append(producto.getPrecio()).append("\n");
        ficha.append("Precio Mayorista: $").append(producto.getPrecioMayorista()).append("\n");
        if (producto.getTipoProducto() != null) {
            LocalDate fechaDeVencimiento = producto.getFechaDeVencimiento();
            if (fechaDeVencimiento != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
                ficha.append("Fecha Vencimiento: ").append(fechaDeVencimiento.format(formatter)).append("\n");
            } else {
                ficha.append("Fecha Vencimiento: No especificada\n");
            }

            if (producto.getEtiquetas() == null) {
                ficha.append("El producto no tiene etiquetas.").append("\n");
            } else {
                ficha.append("Etiquetas:\n");
                for (String etiqueta : producto.getEtiquetas()){
                    ficha.append("- ").append(etiqueta).append("\n");
                }
            }
        }
        return ficha.toString();
    }

    public static Document crearDocParaProducto(String tipoProducto, String nombre, long id, double precioMayorista, double precio,
            LocalDate fechaDeVencimiento, ArrayList<String> etiquetas, int cantidad) {

        Document doc = new Document("producto", new Document()
                .append("nombre", nombre)
                .append("id", new BsonInt64(id)) // Me aseguro de que no lo guarde como int, que siempre sea Long
                .append("precioMayorista", precioMayorista)
                .append("precio", precio)
                .append("tipoProducto", tipoProducto)
                .append("fechaDeVencimiento", fechaDeVencimiento.toString())
                .append("Etiquetas", Arrays.asList(etiquetas)))
                .append("cantidad", cantidad);
        return doc;
    }
}
