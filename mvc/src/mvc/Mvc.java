/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.controlador.ControladorPersonas;
import mvc.modelo.ModeloPersona;
import mvc.vista.VistaPersonas;

/**
 *
 * @author Patricio
 */
public class Mvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ModeloPersona modelo=new ModeloPersona();
        VistaPersonas vista=new VistaPersonas();
        ControladorPersonas controlador=new ControladorPersonas(modelo, vista);
        controlador.iniciaControl();
    }
    
}
