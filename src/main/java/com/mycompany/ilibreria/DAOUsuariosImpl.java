/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ilibreria;

import com.company.interfaces.DAOUsuarios;
import com.mycompany.db.Database;
import com.mycompany.models.UsuariosM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sbeck
 */
public class DAOUsuariosImpl extends Database implements DAOUsuarios{
    final int DUPLICATE_KEY_ERROR_CODE = 1062;

    @Override
    public void registrar(UsuariosM user) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("INSERT INTO users(name, last_name, domicilio, tel) VALUES(?,?,?,?)");
            pst.setString(1, user.getName());
            pst.setString(2, user.getLast_name());
            pst.setString(3, user.getDomicilio());
            pst.setString(4, user.getTel());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Registrado Exitosamente", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
             if ( ((SQLException)e).getErrorCode() == DUPLICATE_KEY_ERROR_CODE) {
                JOptionPane.showMessageDialog(null, "No puede haber numeros de telefono duplicado!", "AVISO", 0);
             }
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(UsuariosM user) {
        try {
            this.Conectar();
            
            PreparedStatement pst = this.coneccion.prepareStatement("UPDATE users SET name = ?, last_name = ?, domicilio = ?, tel = ? WHERE id = ?");
            pst.setString(1, user.getName());
            pst.setString(2, user.getLast_name());
            pst.setString(3, user.getDomicilio());
            pst.setString(4, user.getTel());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Modificado Exitosamente", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException e) {
            if ( ((SQLException)e).getErrorCode() == DUPLICATE_KEY_ERROR_CODE) {
                JOptionPane.showMessageDialog(null, "No puede haber numeros de telefono duplicado!", "AVISO", 0);
            }
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(int userId) {
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("DELETE FROM users WHERE id=?");
            pst.setInt(1, userId);
            pst.executeUpdate();
            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<UsuariosM> listar(String name) {
        List<UsuariosM> lista = null;
        try {
            this.Conectar();
            String query = name.isEmpty() ? "SELECT * FROM users;" : "SELECT * FROM users WHERE name LIKE '%" + name + "%';";
            PreparedStatement st = this.coneccion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                UsuariosM user = new UsuariosM();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name(rs.getString("last_name"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
                lista.add(user);
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

    @Override
    public UsuariosM obtenerUsuarioPorId(int id) {
        UsuariosM user = new UsuariosM();
        try {
            this.Conectar();
            PreparedStatement pst = this.coneccion.prepareStatement("SELECT * FROM users WHERE id=?;");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name(rs.getString("last_name"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
            }
            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            this.Cerrar();
        }
        return user;
    }
}
