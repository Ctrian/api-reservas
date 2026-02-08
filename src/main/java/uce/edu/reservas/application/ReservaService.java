package uce.edu.reservas.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.reservas.application.representation.ReservaRepresentation;
import uce.edu.reservas.domain.Avion;
import uce.edu.reservas.domain.Pasajero;
import uce.edu.reservas.domain.Reserva;
import uce.edu.reservas.infraestructure.AvionRepository;
import uce.edu.reservas.infraestructure.PasajeroRepository;
import uce.edu.reservas.infraestructure.ReservaRepository;

@Transactional
@ApplicationScoped
public class ReservaService {

    @Inject
    private ReservaRepository reservaRepository;

    @Inject
    private AvionRepository avionRepository;

    @Inject
    private PasajeroRepository pasajeroRepository;

    public List<ReservaRepresentation> listarTodos() {
        return this.reservaRepository.listAll().stream().map(this::mapper).toList();
    }

    public ReservaRepresentation consultarPorId(Integer id) {
        Reserva reserva = this.reservaRepository.findById(id.longValue());
        return reserva != null ? this.mapper(reserva) : null;
    }

    @Transactional
    public void crear(ReservaRepresentation reserva) {
        this.reservaRepository.persist(this.mappearReserva(reserva));
    }

    @Transactional
    public void actualizar(Integer id, ReservaRepresentation reserva) {
        Reserva entity = this.reservaRepository.findById(id.longValue());

        Avion avion = this.avionRepository.findById(reserva.getIdAvion());
        Pasajero pasajero = this.pasajeroRepository.findById(reserva.getIdPasajero());

        if (entity == null) {
            return;
        }
        entity.setFecha(reserva.getFecha());
        entity.setEstado(reserva.getEstado());
        entity.setPrecio(reserva.getPrecio());
        entity.setAsiento(reserva.getAsiento());
        entity.setAvion(avion);
        entity.setPasajero(pasajero);
        this.reservaRepository.getEntityManager().merge(this.mappearReserva(reserva));
    }

    @Transactional
    public void eliminar(Integer id) {
        Reserva entity = this.reservaRepository.findById(id.longValue());
        if (entity == null) {
            return;
        }
        this.reservaRepository.delete(entity);
    }

    private ReservaRepresentation mapper(Reserva reserva) {
        ReservaRepresentation resR = new ReservaRepresentation();
        resR.setId(reserva.getId());
        resR.setFecha(reserva.getFecha());
        resR.setHora(reserva.getHora());
        resR.setOrigen(reserva.getOrigen());
        resR.setDestino(reserva.getDestino());
        resR.setEstado(reserva.getEstado());
        resR.setPrecio(reserva.getPrecio());
        resR.setAsiento(reserva.getAsiento());
        resR.setIdAvion(reserva.getAvion().getId().longValue());
        resR.setIdPasajero(reserva.getPasajero().getId().longValue());
        return resR;
    }

    private Reserva mappearReserva(ReservaRepresentation reserva) {
        Avion avion = this.avionRepository.findById(reserva.getIdAvion());
        Pasajero pasajero = this.pasajeroRepository.findById(reserva.getIdPasajero());
        Reserva res = new Reserva();
        res.setId(reserva.getId());
        res.setFecha(reserva.getFecha());
        res.setHora(reserva.getHora());
        res.setOrigen(reserva.getOrigen());
        res.setDestino(reserva.getDestino());
        res.setEstado(reserva.getEstado());
        res.setPrecio(reserva.getPrecio());
        res.setAsiento(reserva.getAsiento());
        res.setAvion(avion);
        res.setPasajero(pasajero);
        return res;
    }
}
