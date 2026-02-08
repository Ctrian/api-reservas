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
    public PasajeroRepresentation createPasajero(PasajeroRepresentation pasajero) {
        this.pasajeroRepository.persist(this.toEntity(pasajero));
        return pasajero;
    }

    @Transactional
    public void updatePasajero(Long id, PasajeroRepresentation pasajero) {
        Pasajero entity = this.pasajeroRepository.findById(id.longValue());
        if (entity == null) {
            return;
        }
        entity.setNombre(pasajero.getNombre());
        entity.setApellido(pasajero.getApellido());
        entity.setCorreo(pasajero.getCorreo());
        this.pasajeroRepository.getEntityManager().merge(entity);
    }

    @Transactional
    public void actualiarParcial(Long id, PasajeroRepresentation pasajero) {
        Pasajero entity = this.pasajeroRepository.findById(id.longValue());
        if (entity == null) {
            return;
        }
        if (pasajero.getNombre() != null) {
            entity.setNombre(pasajero.getNombre());
        }
        if (pasajero.getApellido() != null) {
            entity.setApellido(pasajero.getApellido());
        }
        if (pasajero.getCorreo() != null) {
            entity.setCorreo(pasajero.getCorreo());
        }
        this.pasajeroRepository.getEntityManager().merge(entity);
    }

    @Transactional
    public void deletePasajero(Long id) {
        Pasajero entity = this.pasajeroRepository.findById(id.longValue());
        if (entity == null) {
            return;
        }
        entity.delete();
    }

    private PasajeroRepresentation toRepresentation(Pasajero pasajero) {
        PasajeroRepresentation representation = new PasajeroRepresentation();
        representation.setId(pasajero.getId());
        representation.setNombre(pasajero.getNombre());
        representation.setApellido(pasajero.getApellido());
        representation.setCorreo(pasajero.getCorreo());
        return representation;
    }

    private Pasajero toEntity(PasajeroRepresentation representation) {
        Pasajero entity = new Pasajero();
        entity.setId(representation.getId());
        entity.setNombre(representation.getNombre());
        entity.setApellido(representation.getApellido());
        entity.setCorreo(representation.getCorreo());
        return entity;
    }
}
