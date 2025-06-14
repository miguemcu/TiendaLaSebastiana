/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import Repository.RepoVentas;

/**
 *
 * @author migue
 */
public class VentaService {

    private RepoVentas repositorio;

    public VentaService() {
        this.repositorio = new RepoVentas();
    }

    public RepoVentas getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepoVentas repositorio) {
        this.repositorio = repositorio;
    }

    public boolean añadirVenta(Venta venta) throws Exception {
        return repositorio.agregarVenta(venta.getDetalles(), venta.getTotalVenta(),
                venta.getTotalBruto(), venta.getTotalDescuento(), venta.getTotalIva(),
                venta.getFecha(), venta.getID());
    }
    
}
