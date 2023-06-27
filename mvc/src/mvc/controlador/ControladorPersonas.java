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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc.modelo.ConexionPG;
import mvc.modelo.ModeloPersona;
import mvc.modelo.Persona;
import mvc.vista.VistaPersonas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    vista.getBtnImprimir().addActionListener(l->imprimePesonas());
    }
    
    private void imprimePesonas(){
    
        ConexionPG con=new ConexionPG();
        try {
            JasperReport jr=(JasperReport)JRLoader.loadObject(
                    getClass().getResource("/mvc/vista/reportes/PersonasCondicional.jasper")
            );
            Map<String,Object> parametros=new HashMap<String,Object>();
            parametros.put("minimo", 500);
            parametros.put("maximo", 1500);
            parametros.put("titulo", "REPORTE VENTAS JULIO 2023");
            JasperPrint jp=JasperFillManager.fillReport(jr,parametros,con.getCon());
            
            JasperViewer jv=new JasperViewer(jp);
            jv.setVisible(true);//Muetra el JViewer
        } catch (JRException ex) {
            Logger.getLogger(ControladorPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
