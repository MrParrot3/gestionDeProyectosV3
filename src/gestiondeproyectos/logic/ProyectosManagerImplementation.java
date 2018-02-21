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
    private ServicioRESTClient swebClient;
    private ClienteRESTClient cwebClient;
    private static final Logger LOGGER=Logger.getLogger("javafxapplicationud3example");
    
    
    public ProyectosManagerImplementation(){
        pwebClient = new ProyectoRESTClient();
        swebClient = new ServicioRESTClient();
        cwebClient = new ClienteRESTClient();
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
        ProyectosBean proyecto = null;
        proyecto.setCliente(cliente);
        proyecto.setConcepto(concepto);
        proyecto.setServicios(servicios);      
        proyecto.setHorasEstimadas(horasEstimadas);
        proyecto.setHorasFinales(horasFinales);
        proyecto.setImporte(importe);
        proyecto.setImporteFinal(importeFinal);
        proyecto.setFechaEntrega(fechaEntrega);
        proyecto.setFechaFinal(fechaFinal);
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
