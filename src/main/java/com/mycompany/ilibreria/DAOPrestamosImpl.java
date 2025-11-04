/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ilibreria;

import com.company.interfaces.DAOPrestamos;
import com.mycompany.db.Database;
import com.mycompany.models.PrestamosM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sbeck
 */
public class DAOPrestamosImpl extends Database implements DAOPrestamos {

    @Override
    public void prestar(PrestamosM prestamo) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("INSERT INTO lendings(user_id, book_id, date_out) VALUES(?,?,?)");
            pst.setInt(1, prestamo.getUser_id());
            pst.setInt(2, prestamo.getBook_id());
            pst.setString(3, prestamo.getDate_out());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void devolver(PrestamosM prestamo) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("UPDATE lendings SET date_return = ? WHERE id = ?");
            pst.setString(1, prestamo.getDate_return());
            pst.setInt(2, prestamo.getId());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<PrestamosM> listar() {
        List<PrestamosM> lista = null;
        try {
            this.Conectar();
            PreparedStatement st = this.coneccion.prepareStatement("SELECT * FROM lendings;");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                PrestamosM prestamo = new PrestamosM();
                prestamo.setId(rs.getInt("id"));
                prestamo.setUser_id(rs.getInt("user_id"));
                prestamo.setBook_id(rs.getInt("book_id"));
                prestamo.setDate_out(rs.getString("date_out"));
                prestamo.setDate_return(rs.getString("date_return"));
                lista.add(prestamo);
            }
            rs.close();
            st.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        } finally{
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public PrestamosM obtenerPrestamo(int userId, int bookId) {
        PrestamosM prestamo = null;
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("SELECT * FROM lendings WHERE user_id = ? AND book_id = ? AND date_return IS NULL ORDER BY id DESC LIMIT 1;");
            pst.setInt(1, userId);
            pst.setInt(2, bookId);
            ResultSet rs = pst.executeQuery();
            prestamo = new PrestamosM();
            while(rs.next()){
                prestamo.setId(rs.getInt("id"));
                prestamo.setUser_id(rs.getInt("user_id"));
                prestamo.setBook_id(rs.getInt("book_id"));
                prestamo.setDate_out(rs.getString("date_out"));
                prestamo.setDate_return(rs.getString("date_return"));
            }
            rs.close();
            pst.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        } finally{
            this.Cerrar();
        }
        return prestamo;
    }

    @Override
    public int obtenerPrestamosTotales(int BookId) {
        int totalPrestamos = 0;
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("SELECT COUNT(*) AS totalP FROM `lendings` WHERE `book_id` = ? AND `date_return` IS NULL;");
            pst.setInt(1, BookId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            totalPrestamos = rs.getInt("totalP");
            rs.close();
            pst.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        } finally{
            this.Cerrar();
        }
        return totalPrestamos;
    }
    
}
