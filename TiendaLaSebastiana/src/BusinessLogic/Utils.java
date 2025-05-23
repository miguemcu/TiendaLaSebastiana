/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author migue
 */
public  class Utils {
    
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
}
