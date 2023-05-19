/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.modelo.ModeloPersona;
import mvc.vista.VistaPersonas;

/**
 *
 * @author Patricio
 */
public class ControladorPersonas {
    
    private ModeloPersona modelo;
    private VistaPersonas vista;

    public ControladorPersonas(ModeloPersona modelo, VistaPersonas vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }
    
    public void iniciaControl(){
    vista.getBtnConsultar().addActionListener(o->mensaje());
    
    }
    private void mensaje(){
       vista.getLblPersonas().setText("Mensaje del Click"); 
    }
    
    
    
}
