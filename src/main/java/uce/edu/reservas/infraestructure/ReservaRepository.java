package uce.edu.reservas.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.reservas.domain.Reserva;

@ApplicationScoped
public class ReservaRepository implements PanacheRepository<Reserva> {

}
