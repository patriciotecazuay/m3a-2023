/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.controlador.ControladorMenuPrincipal;
import mvc.vista.VistaMenuPrincipal;


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
        VistaMenuPrincipal vistaMenuPrincipal=new VistaMenuPrincipal();
        ControladorMenuPrincipal controlPrincipal= new ControladorMenuPrincipal(vistaMenuPrincipal);
        controlPrincipal.iniciaControl();
    }
    
}
