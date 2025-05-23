package BusinessLogic;


import java.util.ArrayList;
import java.time.LocalDate;
 public class Bebida extends Producto {

    public Bebida() {
    }
    
    public Bebida(String nombre, long id,double precio, double precioMayorista,
                  LocalDate fechaDeVencimiento, ArrayList<String> etiquetas) {
        super(nombre,id,precio, precioMayorista, EnumTipoProd.GRANOS, fechaDeVencimiento, etiquetas);
    }

    @Override
    public String imprimirFicha() {
        return Utils.mostrarMenu(this);
    }
}

