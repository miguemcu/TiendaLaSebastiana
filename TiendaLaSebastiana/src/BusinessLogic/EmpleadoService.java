/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import Repository.RepoEmpleados;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author migue
 */
public class EmpleadoService {
    private RepoEmpleados repositorio;

    public EmpleadoService() throws Exception {
        this.repositorio = new RepoEmpleados();
    }

    public RepoEmpleados getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepoEmpleados repositorio) {
        this.repositorio = repositorio;
    }
    
    
    public Empleado validarDocumento(String nombre, String documento) throws Exception{
        var empleado = repositorio.getEmpleado(nombre);
        
        if (empleado != null && empleado.getDocumento().equals(documento)){
            return empleado;
        }
        
        return null;
    }
    
    public boolean agregarEmpleado(String nombre, String documento, MongoCollection<Document> collection){
        
        Bson filtro = Filters.or(
                Filters.eq("nombre", nombre),
                Filters.eq("documento", documento));
        
        Document empleadoExiste = collection.find(filtro).first();
        
        if(empleadoExiste != null){ // Si ya existe alguien con esos datos, no lo deja
            return false;
        }
        
        Document newEmpleado = new Document("nombre", nombre)
                .append("documento",documento);
        
        collection.insertOne(newEmpleado);
        
        return true;
    }
    
    public boolean eliminarEmpleado(String nombre, String documento, MongoCollection<Document> collection){
        Bson filtro = Filters.and(
                Filters.eq("nombre", nombre),
                Filters.eq("documento", documento));
        
        if (collection.find(filtro).first() == null){
            return false;
        }
        
        collection.deleteOne(filtro);
        return true;
    }
    

}
