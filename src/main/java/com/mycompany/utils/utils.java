/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author sbeck
 */
public class utils {
    public static boolean esNumero(String srtNum){
        if(srtNum == null){
            return false;
        }
        try{
            int d = Integer.parseInt(srtNum);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    public static boolean validarIds(String folioId, String bookId){
        if(folioId.isEmpty() || bookId.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!", "AVISO", 0);
            return false;
        } else if (!utils.esNumero(folioId) ||!utils.esNumero(bookId)) {
            JOptionPane.showMessageDialog(null, "Ambos campos deben ser Numeros!", "AVISO", 0);
            return false;
        } else if (Integer.parseInt(folioId) <= 0 || Integer.parseInt(bookId) <= 0 ) {
            JOptionPane.showMessageDialog(null, "Ambos campos deben ser mayores que 0!", "AVISO", 0);
            return false;
        }
        return true;
    }
    
    public static String fechaActual(){
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }
}
