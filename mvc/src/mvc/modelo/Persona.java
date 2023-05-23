/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Patricio
 */
public class Persona {
    private String idPersona;
    private String nombrePersona;
    private String apellidosPersona;
    private Date fechaNacimiento;
    private int edadPersona;

    public Persona() {
    }

    public Persona(String idPersona, String nombrePersona, String apellidosPersona, Date fechaNacimiento) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdadPersona() {
        return edadPersona;
    }

//    public void setEdadPersona(String edadPersona) {
//        
//        this.edadPersona = edadPersona;
//    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public void setApellidosPersona(String apellidosPersona) {
        this.apellidosPersona = apellidosPersona;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        LocalDate ldate= fechaNacimiento.toLocalDate();
        Period periodo=Period.between(ldate, LocalDate.now());
        this.edadPersona=periodo.getYears();
        
    }
    
    
    
    
}
