/*
 * CLASE FACTURA
 */
package gestiondeproyectos.ui.controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arantzazu Azkona Villaverde
 */
public class FacturaBean {
/*
    Atributos clase Factura
    */
    private final SimpleIntegerProperty nFactura;
    private final SimpleStringProperty fechaEmision;
    private final SimpleFloatProperty importe;
    private final SimpleStringProperty estado;
    private final SimpleStringProperty fechaVencimiento;
    private final SimpleStringProperty nif;
/*
    CONSTRUCTOR CLASE FACTURA
    @param nFactura define el número de factura
    @param fechaEmision define la fecha de emisión de la factura
    @param importe define el importe de la factura
    @param estado define el estado de la factura a "pagada" o "no pagada"
    @param fechaVencimiento define la fecha de vencimiento de la factura
    @param nif define el nif del cliente
      */    
    public FacturaBean(Integer nFactura, String fechaEmision, Float importe, String estado, String fechaVencimiento, String nif) {
        this.nFactura = new SimpleIntegerProperty (nFactura);
        this.fechaEmision = new SimpleStringProperty(fechaEmision);
        this.importe = new SimpleFloatProperty(importe);
        this.estado = new SimpleStringProperty(estado);
        this.fechaVencimiento = new SimpleStringProperty(fechaVencimiento);
        this.nif = new SimpleStringProperty(nif);
    }
    /*
    MÉTODOS GET Y SET CLASE FACTURA
    */
    /**
     * Método geNFactura
     * @return devuelve el número de la factura
     */
    public Integer getNFactura() {
        return this.nFactura.get();
    }
    /**
     * Método setNFactura
     * @param nFactura define el número de factura
     */
    public void setNFactura(Integer nFactura){
        this.nFactura.set(nFactura);
    }
    /**
    * Método getFechaEmision
    * @return fechaEmision devuelve la fecha de emisión de la factura
     */
    public String getFechaEmision() {
        return this.fechaEmision.get();
    }
    /**
     * Método setFechaEmision  
     * @param fechaEmision define la fecha de emisión de la factura
    */
    public void setFechaEmision(String fechaEmision){
        this.fechaEmision.set(fechaEmision);
    }
    /**
     * Método getImporte
     * @return importe devuelve el importe de la factura
     */
    public Float getImporte() {
        return this.importe.get();
    }
    /**
     * Método setImporte
     * @param importe define el importe de la factura
     */
    public void setImporte(Float importe){
        this.importe.set(importe);
    }
    /**
     * Método getEstado
     * @return estado devuelve el estado de la factura, "pagada" o "no pagada"
     */
    public String getEstado() {
        return this.estado.get();
    }
    /**
     * Método setEstado
     * @param estado define el estado de la factura a "pagada" o "no pagada"
     */
    public void setEstado(String estado){
        this.estado.set(estado);
    }
    /**
     * Método getFechaVencimiento
     * @return fechaVencimiento devuelve la fecha de vencimiento de la factura
     */
    public String getFechaVencimiento() {
        return this.fechaVencimiento.get();
    }
    /**
     * Método setFechaVencimiento
     * @param fechaVencimiento define la fecha de vencimiento de la factura
     */
    public void setFechaVencimiento(String fechaVencimiento){
        this.fechaVencimiento.set(fechaVencimiento);
    }
    /**
     * Método getNif
     * @return nif devuelve el nif del cliente
     */
    public String getNif() {
        return this.nif.get();
    }
    /**
     * Método setNif
     * @param nif define el nif del cliente
     */
    public void setNif(String nif){
        this.nif.set(nif);
    }
   
    
    
}
