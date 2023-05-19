/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio
 */
public class ConeccionPG {
    
    Connection con;
    
    String cadenaConeccion="jdbc:postgresql://localhost:5432/mvc";
    String usuarioPG="postgres";
    String contraPG="apecs";

    public ConeccionPG() {
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeccionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          con=DriverManager.getConnection(cadenaConeccion, usuarioPG, contraPG);
           System.out.println("Coneccion OK");
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ResultSet consultaBD(String sql){
    
        try {
            Statement st= con.createStatement();
            return st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionPG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public boolean accionBD(String sql){
    
    
        try {
            Statement st=con.createStatement();
            st.execute(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionPG.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
