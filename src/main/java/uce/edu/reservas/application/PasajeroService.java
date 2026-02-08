package uce.edu.reservas.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.reservas.application.representation.PasajeroRepresentation;
import uce.edu.reservas.domain.Pasajero;
import uce.edu.reservas.infraestructure.PasajeroRepository;

@Transactional
@ApplicationScoped
public class PasajeroService {

    @Inject
    private PasajeroRepository pasajeroRepository;

    public List<PasajeroRepresentation> listarTodos() {
        return this.pasajeroRepository.findAll().stream().map(this::toRepresentation).toList();
    }

    public PasajeroRepresentation consultarPorId(Integer id) {
        return this.toRepresentation(this.pasajeroRepository.findById(id.longValue()));
    }

    @Transactional
    public void createPasajero(PasajeroRepresentation pasajero) {
        this.pasajeroRepository.persist(this.toEntity(pasajero));
    }

    @Transactional
    public void updatePasajero(Integer id, PasajeroRepresentation pasajero) {
        PasajeroRepresentation entity = this.consultarPorId(id);
        if (entity == null) {
            return;
        }
        entity.setNombre(pasajero.getNombre());
        entity.setApellido(pasajero.getApellido());
        entity.setCedula(pasajero.getCedula());
        entity.setTelefono(pasajero.getTelefono());
        entity.setCorreo(pasajero.getCorreo());

        this.pasajeroRepository.getEntityManager().merge(this.toEntity(pasajero));
    }

    @Transactional
    public void actualiarParcial(Integer id, PasajeroRepresentation pasajero) {
        PasajeroRepresentation entity = this.consultarPorId(id);
        if (entity == null) {
            return;
        }
        if (pasajero.getNombre() != null) {
            entity.setNombre(pasajero.getNombre());
        }
        if (pasajero.getApellido() != null) {
            entity.setApellido(pasajero.getApellido());
        }
        if (pasajero.getCedula() != null) {
            entity.setCedula(pasajero.getCedula());
        }
        if (pasajero.getTelefono() != null) {
            entity.setTelefono(pasajero.getTelefono());
        }
        if (pasajero.getCorreo() != null) {
            entity.setCorreo(pasajero.getCorreo());
        }
        this.pasajeroRepository.getEntityManager().merge(entity);
    }

    @Transactional
    public void deletePasajero(Integer id) {
        this.pasajeroRepository.deleteById(id.longValue());
    }

    private PasajeroRepresentation toRepresentation(Pasajero pasajero) {
        PasajeroRepresentation representation = new PasajeroRepresentation();
        representation.setId(pasajero.getId());
        representation.setNombre(pasajero.getNombre());
        representation.setApellido(pasajero.getApellido());
        representation.setCedula(pasajero.getCedula());
        representation.setTelefono(pasajero.getTelefono());
        representation.setCorreo(pasajero.getCorreo());
        return representation;
    }

    private Pasajero toEntity(PasajeroRepresentation representation) {
        Pasajero entity = new Pasajero();
        entity.setId(representation.getId());
        entity.setNombre(representation.getNombre());
        entity.setApellido(representation.getApellido());
        entity.setCedula(representation.getCedula());
        entity.setTelefono(representation.getTelefono());
        entity.setCorreo(representation.getCorreo());
        return entity;
    }
}
