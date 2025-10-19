/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.interfaces;

import com.mycompany.models.PrestamosM;
import java.util.List;

/**
 *
 * @author sbeck
 */
public interface DAOPrestamos {
    public void registrar(PrestamosM prestamo);
    public void modificar(PrestamosM prestamo);
    public List<PrestamosM> listar();
}
