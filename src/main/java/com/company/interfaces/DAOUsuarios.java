/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.interfaces;

import com.mycompany.models.UsuariosM;
import java.util.List;

/**
 *
 * @author sbeck
 */
public interface DAOUsuarios {
    public void registrar(UsuariosM user);
    public void modificar(UsuariosM user);
    public void eliminar(int userId);
    public List<UsuariosM> listar(String name);
    public UsuariosM obtenerUsuarioPorId(int id);
    
}
