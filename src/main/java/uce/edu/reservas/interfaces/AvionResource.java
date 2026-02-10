package uce.edu.reservas.interfaces;

import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
import uce.edu.reservas.application.AvionService;
import uce.edu.reservas.application.representation.AvionRepresentation;
import uce.edu.reservas.application.representation.LinkDTO;

@Path("/aviones")
@Consumes("application/json")
public class AvionResource {

    @Inject
    private AvionService avionService;

    @Context
    private UriInfo info;

    @GET
    @Path("")
    // @RolesAllowed("admin")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionRepresentation> encontrarTodos() {
        return this.construirLinks(this.avionService.listarTodos());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AvionRepresentation encontrarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.avionService.consultarPorId(id));
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

    private AvionRepresentation construirLinks(AvionRepresentation avion) {
        String self = this.info.getBaseUriBuilder().path(AvionResource.class).path(String.valueOf(avion.getId()))
                .build().toString();

        avion.setLinks(List.of(new LinkDTO(self, "self")));
        return avion;
    }

    private List<AvionRepresentation> construirLinks(List<AvionRepresentation> aviones) {
        for (AvionRepresentation avion : aviones) {
            String self = this.info.getBaseUriBuilder()
                    .path(AvionResource.class)
                    .path(String.valueOf(avion.getId()))
                    .build()
                    .toString();

            avion.setLinks(List.of(new LinkDTO(self, "self")));
        }
        return aviones;
    }

}
