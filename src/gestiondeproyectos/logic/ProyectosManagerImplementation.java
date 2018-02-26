/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.rest.ClienteRESTClient;
import gestiondeproyectos.rest.ProyectoRESTClient;
import gestiondeproyectos.rest.ServicioRESTClient;
import gestiondeproyectos.ui.controller.ClienteBean;
import gestiondeproyectos.ui.controller.ProyectosBean;
import gestiondeproyectos.ui.controller.ServicioBean;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.ws.rs.core.GenericType;

/**
 * Class that implements the business logic interface by returning data
 * obtained from the REST service for the Proyectos entity.
 * 
 * @author Iker Jon Mediavilla
 */
public class ProyectosManagerImplementation implements ProyectosManager{
    
     //REST proyectos web client
    private ProyectoRESTClient pwebClient;

    private static final Logger LOGGER=Logger.getLogger("javafxapplicationud3example");
    
    private static final Logger logger = Logger.getLogger("implementation.class");
    
    public ProyectosManagerImplementation(){
        pwebClient = new ProyectoRESTClient();

    }
    
    
    
    
    @Override
    public Collection <ProyectosBean> getAllProyectos() {
        LOGGER.info("ProyectosManager: Finding all proyectos from REST service (XML).");
        Collection<ProyectosBean> proyectos = pwebClient.findAll_XML(new GenericType<Collection<ProyectosBean>>() {});
        LOGGER.info("Tamaño de proyectos "+proyectos.size());
        return proyectos;
    }
    
    public Collection <ServicioBean> getAllServicios(){
        LOGGER.info("ProyectosManager: Finding all servicios from REST service (XML).");
        Collection<ServicioBean> servicios = pwebClient.findAll_XML(new GenericType<Collection<ServicioBean>>() {});
        return servicios;
    }
    
    public Collection <ClienteBean> getAllClientes(){
        LOGGER.info("ProyectosManager: Finding all clientes from REST service (XML).");
        Collection<ClienteBean> clientes = pwebClient.findAll_XML(new GenericType<Collection<ClienteBean>>() {});
        return clientes;
    }
    
    
    
    public ProyectosBean setNuevoProyecto(ClienteBean cliente, String concepto, Collection<ServicioBean> servicios, Integer horasEstimadas, Integer horasFinales, float importe, float importeFinal, String fechaEntrega, String fechaFinal) {
        ProyectosBean proyecto = new ProyectosBean();
        proyecto.setCliente(cliente);
        proyecto.setConcepto(concepto);
        proyecto.setServicios(servicios);      
        proyecto.setHorasEstimadas(horasEstimadas);
        proyecto.setHorasFinales(horasFinales);
        proyecto.setImporte(importe);
        proyecto.setImporteFinal(importeFinal);
        proyecto.setFechaEntrega(fechaEntrega);
        proyecto.setFechaFinal(fechaFinal);
        
        logger.info("PROYECTO Cliente nif: "+proyecto.getCliente().getNif());
        logger.info("PROYECTO Cliente direccion: "+proyecto.getCliente().getDireccion());
        logger.info("PROYECTO Cliente email: "+proyecto.getCliente().getEmail());
        logger.info("PROYECTO Cliente nombre: "+proyecto.getCliente().getNombre());
        logger.info("PROYECTO Cliente web: "+proyecto.getCliente().getWeb());
        logger.info("PROYECTO Cliente telefono: "+proyecto.getCliente().getTelefono());
        logger.info("PROYECTO Cliente contacto id: "+proyecto.getCliente().getContacto().getId());
        logger.info("PROYECTO Cliente contacto nombre: "+proyecto.getCliente().getContacto().getNombre());
        logger.info("PROYECTO Cliente contacto email: "+proyecto.getCliente().getContacto().getEmail());
        logger.info("PROYECTO Cliente contacto telefono: "+proyecto.getCliente().getContacto().getTelefono());
        logger.info("PROYECTO Concepto: "+proyecto.getConcepto());
        logger.info("PROYECTO Servicios tamaño: "+proyecto.getServicios().size());
        logger.info("PROYECTO Horas Estimadas: "+proyecto.getHorasEstimadas());
        logger.info("PROYECTO Horas Finales: "+proyecto.getHorasFinales());
        logger.info("PROYECTO Importe: "+proyecto.getImporte());
        logger.info("PROYECTO Importe Final: "+proyecto.getImporteFinal());
        logger.info("PROYECTO Fecha Entrega: "+proyecto.getFechaEntrega());
        logger.info("PROYECTO Fecha Final: "+proyecto.getFechaFinal());
        
        logger.info("ManagerImplementation añadir proyecto");
        
        
        LOGGER.log(Level.INFO,"ProyectosManager: Creating proyecto {0}.",proyecto.getNProyecto());
        pwebClient.create_XML(proyecto);
        return proyecto;
    }
    
    @Override
    public Collection getProyectosFiltrados(String clienteF, Boolean pf, Boolean psf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProyecto(ProyectosBean proyecto) {
       LOGGER.log(Level.INFO,"ProyectosManager: Deleting proyecto {0}.",proyecto.getNProyecto());
       pwebClient.delete(proyecto.getNProyecto().toString());
    }

    @Override
    public void modificarProyecto(ProyectosBean proyecto) {
        LOGGER.log(Level.INFO,"ProyectosManager: Editing proyecto {0}.",proyecto.getNProyecto());
        pwebClient.edit_XML(proyecto);
    }
    
    
}
