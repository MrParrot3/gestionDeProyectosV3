/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.ProyectosBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Iker
 */
public class ProyectosManagerTestDataGenerator implements ProyectosManager {
    
    private static final Logger logger= Logger.getLogger("Javafxapplicationud3example");
    private ArrayList<ProyectosBean> proyectos;
    
    /**
     * Crea datos de prueba 
     */
    public ProyectosManagerTestDataGenerator(){
        proyectos = new ArrayList();
        for(int i=1; i<=25; i++){
            if(i<=9){
                proyectos.add(new ProyectosBean(i, "cliente"+i, "concepto"+i, "servicios"+1, i, i, i, i,"0"+i+"-05-2017", "0"+i+"-06-2017"));
            }
            else{
                proyectos.add(new ProyectosBean(i, "cliente"+i, "concepto"+i, "servicios"+1, i, i, i, i, i+"-05-2017", i+"-06-2017"));
            }
       }
    }
    
    /**
     * 
     * @return devuelve todos los proyectos
     */
    public Collection getAllProyectos() {
        logger.info("todos los proeyectos para UI");
        return proyectos;
    }
    
    /**
     * Comprueva si el nuemro de proyecto existe
     * @param nProyecto
     * @return 
     */
    public Boolean existsNProyecto(Integer nProyecto){
        Boolean existe=false;
        if(proyectos.stream()
                .filter(p -> p.getNProyecto().equals(nProyecto))
                .count()!=0)
            existe=true;
        return existe;
                
    }
    
    /**
     * Crea un numero aleatorio para el proyecto 
     * @return 
     */
    public Integer getNProyecto(){
        Integer n;
        Boolean valido=false;
        Random generador = new Random();
        do{
            
            n= generador.nextInt();
       
        }while(existsNProyecto(n));
        return n;
    }
    
    /**
     * devuelve el servico que le corresponde
     * @return 
     */
    public String getServicios(){
        String servicos="desarrollo";
        return servicos;
    }
    
    /**
     * Añade un proyecto nuevo y lo devuelve para cargarlo en la tabla
     * @param cliente
     * @param concepto
     * @param horasEstimadas
     * @param horasFinales
     * @param importe
     * @param importeFinal
     * @param fechaEntrega
     * @param fechaFinal
     * @return 
     */
    public ProyectosBean setNuevoProyecto(String cliente, String concepto,
            Integer horasEstimadas, Integer horasFinales, float importe,
            float importeFinal, String fechaEntrega, String fechaFinal){
            
       
        proyectos.add(new ProyectosBean(getNProyecto(), cliente, concepto,
                getServicios(), horasEstimadas, horasFinales, importe,
                importeFinal, fechaEntrega, fechaFinal));
        
        return new ProyectosBean(getNProyecto(), cliente, concepto,
                getServicios(), horasEstimadas, horasFinales, importe,
                importeFinal, fechaEntrega, fechaFinal);
    }
    
    /**
     * devuelve los proyectos por los fintros introducidos
     * @param clienteF
     * @param pf
     * @param psf
     * @return 
     */
    public Collection getProyectosFiltrados(String clienteF, Boolean pf, Boolean psf){
        Collection proyectosFiltro = this.proyectos;
        if(clienteF.equals("")){
            proyectosFiltro=proyectos.stream().collect(Collectors.toList());
        }
        else{
            proyectosFiltro=proyectos.stream().filter(p->p.getCliente().contains(clienteF)).collect(Collectors.toList());
        }
        if(pf && psf){
            proyectosFiltro=proyectos.stream().collect(Collectors.toList());
        }
        else if(pf){
           proyectosFiltro = proyectos.stream().filter(p->p.getHorasFinales()!=0)
                                               .filter(p->p.getImporteFinal()!=0)
                                               .filter(p->!p.getFechaFinal().equals(""))
                                               .collect(Collectors.toList());
        }
        else if(psf){
            proyectosFiltro = proyectos.stream().filter(p->p.getHorasFinales()==0 || p.getImporteFinal()==0 || p.getFechaFinal().equals("")).collect(Collectors.toList());
            /*
            proyectosFiltro = proyectos.stream().filter(p->p.getHorasFinales()==0)
                                                .filter(p->p.getImporteFinal()==0)
                                                .filter(p->p.getFechaFinal().equals(""))
                                                .collect(Collectors.toList());
            */
                    
        }
        
        return proyectosFiltro;
    }

    @Override
    public void eliminarProyecto(ProyectosBean proyecto) {
        proyectos.remove(proyecto);
    }
    
    
    
    
    
    
    
}
