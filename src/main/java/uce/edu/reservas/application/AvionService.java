package uce.edu.reservas.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.reservas.application.representation.AvionRepresentation;
import uce.edu.reservas.infraestructure.AvionRepository;

@Transactional
@ApplicationScoped
public class AvionService {

    @Inject
    private AvionRepository avionRepository;

    public List<AvionRepresentation> listarTodos() {
        return this.avionRepository.listAll().stream().map(AvionRepresentation::mapper).toList();
    }

    public AvionRepresentation consultarPorId(Integer id) {
        return AvionRepresentation.mapper(this.avionRepository.findById(id.longValue()));
    }

    public AvionRepresentation consultarPorAerolinea(String aerolinea) {
        return AvionRepresentation.mapper(this.avionRepository.find("aerolinea", aerolinea).firstResult());
    }

    public void crear(AvionRepresentation avion) {
        this.avionRepository.persist(AvionRepresentation.mappearAvion(avion));
    }

    @Transactional
    public void actualizar(Integer id, AvionRepresentation avion) {
        AvionRepresentation avi = this.consultarPorId(id);
        avi.setModelo(avion.getModelo());
        avi.setCapacidad(avion.getCapacidad());
        avi.setAerolinea(avion.getAerolinea());
        avi.setClase(avion.getClase());
        avi.setEspacioEquipaje(avion.getEspacioEquipaje());

        this.avionRepository.getEntityManager().merge(AvionRepresentation.mappearAvion(avi));
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

        this.avionRepository.getEntityManager().merge(AvionRepresentation.mappearAvion(avi));
    }

    @Transactional
    public void eliminar(Integer id) {
        this.avionRepository.deleteById(id.longValue());
    }

}
