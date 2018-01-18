/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Miguel Axier Lafuente Peñas
 */
public class ClienteBean {
    private SimpleStringProperty nif;
    private SimpleStringProperty nombre;
    private SimpleStringProperty direccion;
    private SimpleStringProperty telefono;
    private SimpleStringProperty email;
    private SimpleStringProperty web;
    private SimpleStringProperty nombreContacto;
    private SimpleStringProperty telefonoContacto;
    private SimpleStringProperty emailContacto;
    
    public ClienteBean(String nif, String nombre, String direccion, String telefono
                        ,String email, String web, String nombreContacto
                        ,String telefonoContacto, String emailContacto){
        
        this.nif=new SimpleStringProperty(nif);
        this.nombre=new SimpleStringProperty(nombre);
        this.direccion=new SimpleStringProperty(direccion);
        this.telefono=new SimpleStringProperty(telefono);
        this.email=new SimpleStringProperty(email);
        this.web=new SimpleStringProperty(web);
        this.nombreContacto=new SimpleStringProperty(nombreContacto);
        this.telefonoContacto=new SimpleStringProperty(telefonoContacto);
        this.emailContacto=new SimpleStringProperty(emailContacto);
        
        
    }

    public String getNombreContacto() {
        return this.nombreContacto.get();
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto.set(nombreContacto);
    }

    public String getTelefonoContacto() {
        return this.telefonoContacto.get();
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto.set(telefonoContacto);
    }

    public String getEmailContacto() {
        return this.emailContacto.get();
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto.set(emailContacto);
    }
    
    public String getNif() {
        return this.nif.get();
    }

    public void setNif(String nif) {
        this.nif.set(nif);
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return this.telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getWeb() {
        return this.web.get();
    }

    public void setWeb(String web) {
        this.web .set(web);
    }   
}
