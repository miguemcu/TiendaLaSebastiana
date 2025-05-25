/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import Repository.RepoEmpleados;

/**
 *
 * @author migue
 */
public class EmpleadoLogin {
    private RepoEmpleados repositorio;

    public EmpleadoLogin() {
        this.repositorio = new RepoEmpleados();
    }
    
    public Empleado validarUsuario(String nombre, String documento) throws Exception{
        var empleado = repositorio.getEmpleado(nombre);
        
        if (empleado != null && empleado.getDocumento().equals(documento)){
            return empleado;
        }
        return null;
    }

}
