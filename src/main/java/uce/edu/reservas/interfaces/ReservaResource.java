package uce.edu.reservas.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.reservas.application.ReservaService;
import uce.edu.reservas.application.representation.ReservaRepresentation;

@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    private ReservaService reservaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservaRepresentation> encontrarTodos() {
        return this.reservaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Long id) {
        try {
            return Response.ok(this.reservaService.consultarPorId(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response crear(ReservaRepresentation reserva) {
        try {
            this.reservaService.crear(reserva);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, ReservaRepresentation reserva) {
        try {
            this.reservaService.actualizar(id, reserva);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        try {
            this.reservaService.eliminar(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
