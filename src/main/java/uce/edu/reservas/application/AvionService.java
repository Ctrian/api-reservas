package uce.edu.reservas.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.reservas.application.representation.AvionRepresentation;
import uce.edu.reservas.domain.Avion;
import uce.edu.reservas.infraestructure.AvionRepository;

@Transactional
@ApplicationScoped
public class AvionService {

    @Inject
    private AvionRepository avionRepository;

    public List<AvionRepresentation> listarTodos() {
        return this.avionRepository.listAll().stream().map(this::mapper).toList();
    }

    public AvionRepresentation consultarPorId(Integer id) {
        return this.mapper(this.avionRepository.findById(id.longValue()));
    }

    public void crear(AvionRepresentation avion) {
        this.avionRepository.persist(this.mappearAvion(avion));
    }

    @Transactional
    public void actualizar(Integer id, AvionRepresentation avion) {
        AvionRepresentation avi = this.consultarPorId(id);
        avi.setModelo(avion.getModelo());
        avi.setCapacidad(avion.getCapacidad());
        avi.setAerolinea(avion.getAerolinea());
        avi.setClase(avion.getClase());
        avi.setEspacioEquipaje(avion.getEspacioEquipaje());

        this.avionRepository.getEntityManager().merge(this.mappearAvion(avi));
    }

    @Transactional
    public void actualiarParcial(Integer id, AvionRepresentation avion) {
        AvionRepresentation avi = this.consultarPorId(id);
        if (avion.getModelo() != null) {
            avi.setModelo(avion.getModelo());
        }
        if (avion.getCapacidad() != null) {
            avi.setCapacidad(avion.getCapacidad());
        }
        if (avion.getAerolinea() != null) {
            avi.setAerolinea(avion.getAerolinea());
        }
        if (avion.getClase() != null) {
            avi.setClase(avion.getClase());
        }
        if (avion.getEspacioEquipaje() != null) {
            avi.setEspacioEquipaje(avion.getEspacioEquipaje());
        }

        this.avionRepository.getEntityManager().merge(this.mappearAvion(avi));
    }

    @Transactional
    public void eliminar(Integer id) {
        this.avionRepository.deleteById(id.longValue());
    }

    public List<AvionRepresentation> buscarPorAerolinea(String aerolinea) {
        return this.avionRepository.list("aerolinea", aerolinea).stream().map(this::mapper).toList();
    }

    private AvionRepresentation mapper(Avion avion) {
        AvionRepresentation aviR = new AvionRepresentation();
        aviR.setId(avion.getId());
        aviR.setModelo(avion.getModelo());
        aviR.setCapacidad(avion.getCapacidad());
        aviR.setAerolinea(avion.getAerolinea());
        aviR.setClase(avion.getClase());
        aviR.setEspacioEquipaje(avion.getEspacioEquipaje());
        return aviR;
    }

    private Avion mappearAvion(AvionRepresentation avion) {
        Avion avi = new Avion();
        avi.setId(avion.getId());
        avi.setModelo(avion.getModelo());
        avi.setCapacidad(avion.getCapacidad());
        avi.setAerolinea(avion.getAerolinea());
        avi.setClase(avion.getClase());
        avi.setEspacioEquipaje(avion.getEspacioEquipaje());
        return avi;
    }

}
