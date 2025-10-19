/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ilibreria;

import com.company.interfaces.DAOLibros;
import com.mycompany.db.Database;
import com.mycompany.models.LibrosM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sbeck
 */
public class DAOLibrosImpl extends Database implements DAOLibros {

    @Override
    public void registrar(LibrosM book) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("INSERT INTO books(title, date, author, category, edit, lang, pages, description, stock, available, ISBN) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getDate());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getCategory());
            pst.setString(5, book.getEdit());
            pst.setString(6, book.getLang());
            pst.setInt(7, (int) book.getPages());
            pst.setString(8, book.getDescription());
            pst.setInt(9, (int) book.getStock());
            pst.setInt(10, (int) book.getAvailable());
            pst.setString(11, book.getISBN());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(LibrosM book) {
        try {
            this.Conectar();
            
            PreparedStatement pst = this.coneccion.prepareStatement("UPDATE users SET title = ?, date = ?, author = ?, category = ?, edit = ?, lang = ?, pages = ?, description = ?, stock = ?, available = ?, ISBN = ? WHERE id = ?");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getDate());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getCategory());
            pst.setString(5, book.getEdit());
            pst.setString(6, book.getLang());
            pst.setInt(7, (int) book.getPages());
            pst.setString(8, book.getDescription());
            pst.setInt(9, (int) book.getStock());
            pst.setInt(10, (int) book.getAvailable());
            pst.setString(11, book.getISBN());
            pst.setInt(12, book.getId());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(int bookId) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("DELETE FROM books WHERE id=?");
            pst.setInt(1, bookId);
            pst.executeUpdate();
            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<LibrosM> listar(String title) {
        List<LibrosM> lista = null;
        try {
            this.Conectar();
            String query = title.isEmpty() ? "SELECT * FROM books;" : "SELECT * FROM books WHERE title LIKE '%" + title + "%';";
            PreparedStatement st = this.coneccion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                LibrosM libro = new LibrosM();
                libro.setId(rs.getInt("id"));
                libro.setTitle(rs.getString("title"));
                libro.setDate(rs.getString("date"));
                libro.setAuthor(rs.getString("author"));
                libro.setCategory(rs.getString("category"));
                libro.setEdit(rs.getString("edit"));
                libro.setLang(rs.getString("lang"));
                libro.setPages(rs.getInt("pages"));
                libro.setDescription(rs.getString("description"));
                libro.setStock(rs.getInt("stock"));
                libro.setAvailable(rs.getInt("available"));
                libro.setISBN(rs.getString("ISBN"));
                lista.add(libro);
            }
            rs.close();
            st.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            this.Cerrar();
        }
        return lista;
    }
    
}
