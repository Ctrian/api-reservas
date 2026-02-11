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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.reservas.application.PasajeroService;
import uce.edu.reservas.application.representation.LinkDTO;
import uce.edu.reservas.application.representation.PasajeroRepresentation;

@Path("/pasajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasajeroResource {

    @Inject
    private PasajeroService pasajeroService;

    @Context
    private UriInfo info;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PasajeroRepresentation> encontrarTodos() {
        return this.construirLinks(this.pasajeroService.listarTodos());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PasajeroRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.pasajeroService.consultarPorId(id));
    }

    @GET
    @Path("/cedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public PasajeroRepresentation consultarPorCedula(@PathParam("cedula") String cedula) {
        return this.construirLinks(this.pasajeroService.consultarPorCedula(cedula));
    }

    @POST
    public void crear(PasajeroRepresentation pasajero) {
        this.pasajeroService.createPasajero(pasajero);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, PasajeroRepresentation pasajero) {
        this.pasajeroService.updatePasajero(id, pasajero);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.pasajeroService.deletePasajero(id);
    }

    private PasajeroRepresentation construirLinks(PasajeroRepresentation pasajero) {
        String self = this.info.getBaseUriBuilder().path(PasajeroResource.class).path(String.valueOf(pasajero.getId()))
                .build().toString();

        pasajero.setLinks(List.of(new LinkDTO(self, "self")));
        return pasajero;
    }

    private List<PasajeroRepresentation> construirLinks(List<PasajeroRepresentation> pasajeros) {
        for (PasajeroRepresentation pasajero : pasajeros) {
            String self = this.info.getBaseUriBuilder()
                    .path(PasajeroResource.class)
                    .path(String.valueOf(pasajero.getId()))
                    .build()
                    .toString();

            pasajero.setLinks(List.of(new LinkDTO(self, "self")));
        }
        return pasajeros;
    }
}
