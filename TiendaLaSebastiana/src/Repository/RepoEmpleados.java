/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import BusinessLogic.Empleado;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;



/**
 *
 * @author migue
 */
public class RepoEmpleados{ 
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public RepoEmpleados() {
        var client = MongoClients.create("mongodb://localhost:27017/");
        database = client.getDatabase("TiendaLaSebastiana");
        collection = database.getCollection("Empleados");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
    
    
    public ArrayList<Empleado> getEmpleados() throws Exception{
        
        var empleados = collection.find();
        ArrayList<Empleado> empleadosEncontrados = new ArrayList<>();
        
        try{
            for (Document doc: empleados){
                var nombre = doc.getString("nombre");
                var documento = doc.getString("documento");

                Empleado empleado = new Empleado(nombre, documento);
                empleadosEncontrados.add(empleado);
            }

            return empleadosEncontrados;
        }catch(Exception e){
            throw new Exception("Ha ocurrido un error, contacte al administrador: "+e.getMessage());
        }
    }
    
    public Empleado getEmpleado(String nombre) throws Exception{         
        try{
            Document empleado = collection.find(Filters.eq("nombre", nombre)).first();

            if (empleado != null){
                return Empleado.fromDocument(empleado);
            }
            return null;
        }catch(Exception e){
            throw new Exception("Ha ocurrido un error, contacte al administrador: " +e.getMessage());
        }
    }
    
}
