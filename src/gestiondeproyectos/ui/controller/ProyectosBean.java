/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.ui.controller;

import java.beans.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.Collation;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iker
 */
@XmlRootElement(name="proyecto")
public class ProyectosBean implements Serializable {
    
    private final SimpleIntegerProperty nProyecto;
    private final SimpleObjectProperty<ClienteBean> cliente;
    private final SimpleStringProperty concepto;
    private final SimpleIntegerProperty horasEstimadas;
    private final SimpleIntegerProperty horasFinales;
    private final SimpleFloatProperty importe;
    private final SimpleFloatProperty importeFinal;
    private final SimpleStringProperty fechaEntrega;
    private final SimpleStringProperty fechaFinal;
    private Collection<ServicioBean> servicios;
    
    public ProyectosBean(Integer nProyecto, ClienteBean cliente, String concepto, Integer horasEstimadas, Integer horasFinales,
    float importe, float importeFinal, String fechaEntrega, String fechaFinal) {
        
        this.nProyecto = new SimpleIntegerProperty(nProyecto);
        this.cliente = new SimpleObjectProperty<>(cliente);
        this.concepto = new SimpleStringProperty(concepto);
        this.horasEstimadas = new SimpleIntegerProperty(horasEstimadas);
        this.horasFinales = new SimpleIntegerProperty(horasFinales);
        this.importe = new SimpleFloatProperty(importe);
        this.importeFinal = new SimpleFloatProperty(importeFinal);
        this.fechaEntrega = new SimpleStringProperty(fechaEntrega);
        this.fechaFinal = new SimpleStringProperty(fechaFinal);
        
    }
    
    public ProyectosBean(){
        
        this.nProyecto = new SimpleIntegerProperty();
        this.cliente = new SimpleObjectProperty();
        this.concepto = new SimpleStringProperty();
        this.horasEstimadas = new SimpleIntegerProperty();
        this.horasFinales = new SimpleIntegerProperty();
        this.importe = new SimpleFloatProperty();
        this.importeFinal = new SimpleFloatProperty();
        this.fechaEntrega = new SimpleStringProperty();
        this.fechaFinal = new SimpleStringProperty();
       
    }
    
    @XmlElement(name="id")
    public Integer getNProyecto(){
        return this.nProyecto.get();
    }
    
    public void setNProyecto(Integer nProyecto){
        this.nProyecto.set(nProyecto);
    }
    
    @XmlElement(name="cliente")
    public ClienteBean getCliente(){
        return cliente.get();
    }
    
    public void setCliente(ClienteBean cliente){
        this.cliente.set(cliente);
    }
    
    public String getConcepto(){
        return this.concepto.get();
    }
    
    public void setConcepto(String concepto){
        this.concepto.set(concepto);
    }
    
    @XmlElement(name="servicio")
    public Collection<ServicioBean> getServicios(){
        return servicios;
    }
    
    public void setServicios(Collection<ServicioBean> servicios){
        this.servicios = servicios;
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
    
    @XmlElement(name="importeEstimado")
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
    
    @XmlElement(name="fechaEstimada")
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