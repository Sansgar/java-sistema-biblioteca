/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sbeck
 */
public class Database {
    protected Connection coneccion;
//  private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ilib";
    
    private final String USER = "root";
    private final String PASS = "";
    
    public void Conectar() throws ClassNotFoundException{
        try {
            coneccion = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException("uncaught", e);
        }
        
    }
    
    public void Cerrar(){
        if (coneccion != null){
            try {
                if(!coneccion.isClosed()){
                    coneccion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
