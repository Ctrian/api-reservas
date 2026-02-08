package uce.edu.reservas.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import uce.edu.reservas.application.AvionService;
import uce.edu.reservas.application.representation.AvionRepresentation;

@Path("/aviones")
@Consumes("application/json")
public class AvionResource {

    @Inject
    private AvionService avionService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionRepresentation> encontrarTodos() {
        return this.avionService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AvionRepresentation encontrarPorId(@PathParam("id") Integer id) {
        return this.avionService.consultarPorId(id);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(AvionRepresentation avion) {
        this.avionService.crear(avion);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(@PathParam("id") Integer id, AvionRepresentation avion) {
        this.avionService.actualizar(id, avion);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcial(@PathParam("id") Integer id, AvionRepresentation avion) {
        this.avionService.actualiarParcial(id, avion);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.avionService.eliminar(id);
    }

    @GET
    @Path("/aerolinea")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionRepresentation> buscarPorAerolinea(@QueryParam("aerolinea") String aerolinea) {
        return this.avionService.buscarPorAerolinea(aerolinea);
    }

}
