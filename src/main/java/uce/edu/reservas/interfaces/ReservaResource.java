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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.reservas.application.ReservaService;
import uce.edu.reservas.application.representation.LinkDTO;
import uce.edu.reservas.application.representation.ReservaRepresentation;

@Path("/reservas")
public class ReservaResource {

    @Inject
    private ReservaService reservaService;

    @Context
    private UriInfo info;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservaRepresentation> encontrarTodos() {
        return this.construirLinks(this.reservaService.listarTodos());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ReservaRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.reservaService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(ReservaRepresentation reserva) {
        this.reservaService.crear(reserva);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(@PathParam("id") Integer id, ReservaRepresentation reserva) {
        this.reservaService.actualizar(id, reserva);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcial(@PathParam("id") Integer id, ReservaRepresentation reserva) {
        this.reservaService.actualizarParcial(id, reserva);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.reservaService.eliminar(id);
    }

    private ReservaRepresentation construirLinks(ReservaRepresentation reserva) {
        String self = this.info.getBaseUriBuilder().path(ReservaResource.class).path(String.valueOf(reserva.getId()))
                .build().toString();

        reserva.setLinks(List.of(new LinkDTO(self, "self")));
        return reserva;
    }

    private List<ReservaRepresentation> construirLinks(List<ReservaRepresentation> reservas) {
        for (ReservaRepresentation reserva : reservas) {
            String self = this.info.getBaseUriBuilder()
                    .path(ReservaResource.class)
                    .path(String.valueOf(reserva.getId()))
                    .build()
                    .toString();

            reserva.setLinks(List.of(new LinkDTO(self, "self")));
        }
        return reservas;
    }
}
