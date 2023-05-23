/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc.modelo.ModeloPersona;
import mvc.modelo.Persona;
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
        cargaPersonas();
    }
    
    public void iniciaControl(){
    vista.getBtnConsultar().addActionListener(o->cargaPersonas());
    vista.getBtnCrear().addActionListener(l->abrirDialogo("Crear"));
    vista.getBtnEditar().addActionListener(l->abrirDialogo("Editar"));
    //Acciones del Dialogo
    vista.getBntAceptar().addActionListener(l->crearEditarPersona());
    }
    
    private void crearEditarPersona(){
    if(vista.getDlgCrearEditar().getTitle().contentEquals("Crear")){
        //Acciones de Validacion
        ModeloPersona p=new ModeloPersona();
        p.setIdPersona(vista.getTxtCedula().getText());
        p.setNombrePersona(vista.getTxtNombres().getText());
        p.setApellidosPersona(vista.getTxtApellidos().getText());
       
        Date fechan= Date.valueOf(LocalDate.of(
                vista.getCalFechaNac().getDate().getYear()+1900,
                vista.getCalFechaNac().getDate().getMonth()+1,
                vista.getCalFechaNac().getDate().getDate()));
        p.setFechaNacimiento(fechan);
       if(p.grabarPersona()){
           JOptionPane.showMessageDialog(vista, "Datos creados Satisfactoriamente");
           vista.getDlgCrearEditar().setVisible(false);
           cargaPersonas();
       }else{
           JOptionPane.showMessageDialog(vista, "Error al grabar datos");
       }
    }else{
    
    }
    }
    private void abrirDialogo(String ce){
        vista.getDlgCrearEditar().setLocationRelativeTo(vista);
        vista.getDlgCrearEditar().setSize(500,300);
        vista.getDlgCrearEditar().setTitle(ce);     
        vista.getDlgCrearEditar().setVisible(true);
    }
    
    private void mensaje(){
       vista.getLblPersonas().setText("Mensaje del Click"); 
    }
   
    private void cargaPersonas(){
     DefaultTableModel mJTable;//Estructura del JTable.
     mJTable=(DefaultTableModel)vista.getTblPersonas().getModel();
     mJTable.setNumRows(0);
     List<Persona> listaP= ModeloPersona.listarPersonas();
     listaP.stream().forEach(p->{
         String[] rowData={p.getIdPersona(),p.getNombrePersona(),
         p.getApellidosPersona(),String.valueOf(p.getEdadPersona())};
         mJTable.addRow(rowData);
     });
    }
    
            
    
}
