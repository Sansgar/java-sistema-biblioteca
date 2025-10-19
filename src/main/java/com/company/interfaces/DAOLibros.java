/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.interfaces;

import com.mycompany.models.LibrosM;
import java.util.List;

/**
 *
 * @author sbeck
 */
public interface DAOLibros {
    public void registrar(LibrosM book);
    public void modificar(LibrosM book);
    public void eliminar(int bookId);
    public List<LibrosM> listar(String title);
}
