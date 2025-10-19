/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author sbeck
 */
public class UsuariosM {
    private int id;
    private String name;
    private String last_name;
    private String domicilio;
    private String tel;
    private int sanctions;
    private int sanc_money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getSanctions() {
        return sanctions;
    }

    public void setSanctions(int sanctions) {
        this.sanctions = sanctions;
    }

    public int getSanc_money() {
        return sanc_money;
    }

    public void setSanc_money(int sanc_money) {
        this.sanc_money = sanc_money;
    }
    
}
