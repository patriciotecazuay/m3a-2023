/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio
 */
public class ModeloPersona  extends Persona{
    
    ConeccionPG cpg=new ConeccionPG();

    public ModeloPersona() {
    }
    
   public List<Persona> listarPersonas(){
          List<Persona> listaPersonas=new ArrayList<Persona>();
        try {
            String sql="select id, nombre,apellidos,fechanacimiento from personas";
            ResultSet rs= cpg.consultaBD(sql);
            while(rs.next()){
                Persona persona=new Persona();
                persona.setIdPersona(rs.getString("cedula"));
                persona.setNombrePersona(rs.getString("nombre"));
                persona.setApellidosPersona(rs.getString("apellidos"));
                //persona.setIdPersona(rs.getString("id"));
                listaPersonas.add(persona);
            }
        return listaPersonas;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
        return null;    
        }
    }
   
   public boolean grabarPersona(){
   String sql;
   sql="INSERT INTO persona(id,nombre,apellidos) ";
   sql+=" VALUES('"+getIdPersona()+"','"+getNombrePersona()+"','"+getApellidosPersona()+"')";
   
   return cpg.accionBD(sql);
   }
}
