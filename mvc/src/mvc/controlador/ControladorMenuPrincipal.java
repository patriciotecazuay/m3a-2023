/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import mvc.modelo.ModeloPersona;
import mvc.vista.VistaMenuPrincipal;
import mvc.vista.VistaPersonas;

/**
 *
 * @author Patricio
 */
public class ControladorMenuPrincipal {
    
    VistaMenuPrincipal vistaPrincipal;

    public ControladorMenuPrincipal(VistaMenuPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
    }
    
    public void iniciaControl(){
    //MENU PRINCIPAL    
    vistaPrincipal.getMnuClientes().addActionListener(l->menuPersonas());
    //TOOLBAR
    vistaPrincipal.getTlbClientes().addActionListener(l->menuPersonas());
    }
  
    
    
private void menuPersonas(){
        ModeloPersona modelo=new ModeloPersona();
        VistaPersonas vista=new VistaPersonas();
        vistaPrincipal.getDskPrincipal().add(vista);//Agrega la vista per a la Principal
        ControladorPersonas controlador=new ControladorPersonas(modelo, vista);
        controlador.iniciaControl();
}    
}


