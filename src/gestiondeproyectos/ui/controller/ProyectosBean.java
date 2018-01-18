/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import java.beans.*;
import java.time.LocalDate;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author iker
 */
public class ProyectosBean  {
    
    private final SimpleIntegerProperty nProyecto;
    private final SimpleStringProperty cliente;
    private final SimpleStringProperty concepto;
    private final SimpleStringProperty servicios;
    private final SimpleIntegerProperty horasEstimadas;
    private final SimpleIntegerProperty horasFinales;
    private final SimpleFloatProperty importe;
    private final SimpleFloatProperty importeFinal;
    private final SimpleStringProperty fechaEntrega;
    private final SimpleStringProperty fechaFinal;
   
    
    public ProyectosBean(Integer nProyecto, String cliente, String concepto, String servicios, Integer horasEstimadas, Integer horasFinales,
    float importe, float importeFinal, String fechaEntrega, String fechaFinal ) {
        this.nProyecto = new SimpleIntegerProperty(nProyecto);
        this.cliente = new SimpleStringProperty(cliente);
        this.concepto = new SimpleStringProperty(concepto);
        this.servicios  = new SimpleStringProperty(servicios);
        this.horasEstimadas = new SimpleIntegerProperty(horasEstimadas);
        this.horasFinales = new SimpleIntegerProperty(horasFinales);
        this.importe = new SimpleFloatProperty(importe);
        this.importeFinal = new SimpleFloatProperty(importeFinal);
        this.fechaEntrega = new SimpleStringProperty(fechaEntrega);
        this.fechaFinal = new SimpleStringProperty(fechaFinal);
        
    }

    
    
    public Integer getNProyecto(){
        return this.nProyecto.get();
    }
    
    public void setNProyecto(Integer nProyecto){
        this.nProyecto.set(nProyecto);
    }

    public String getCliente() {
        return this.cliente.get();
    }
    
    public void setCliente(String cliente){
        this.cliente.set(cliente);
    }
    
    public String getConcepto(){
        return this.concepto.get();
    }
    
    public void setConcepto(String concepto){
        this.concepto.set(concepto);
    }
    
    public String getServicios(){
        return this.servicios.get();
    }
    
    public void setServicios(String servicios){
        this.servicios.set(servicios);
    }
    
    public Integer getHorasEstimadas(){
        return this.horasEstimadas.get();
    }
    
    public void setHorasEstimadas(Integer horasEstimadas){
        this.horasEstimadas.set(horasEstimadas);
    }
    
    public Integer getHorasFinales(){
        return this.horasFinales.get();
    }
    
    public void setHorasFinales(Integer horasFinales){
        this.horasFinales.set(horasFinales);
    }
    
    public Float getImporte(){
        return this.importe.get();
    }
    
    public void setImporte(Float importe){
        this.importe.set(importe);
    }
    
    public Float getImporteFinal(){
        return this.importeFinal.get();
    }
    
    public void setImporteFinal(Float importeFinal){
        this.importeFinal.set(importeFinal);
    }
    
    public String getFechaEntrega(){
        return this.fechaEntrega.get();
    }
    
    public void setFechaEntrega(String fechaEntrega){
        this.fechaEntrega.set(fechaEntrega);
    }
    
    public String getFechaFinal(){
        return this.fechaFinal.get();
    }
    
    public void setFechaFinal(String fechaFinal){
        this.fechaFinal.set(fechaFinal);
    }
    

    
    
    
    
    
    
}
