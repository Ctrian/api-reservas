package uce.edu.reservas.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.reservas.domain.Avion;

@ApplicationScoped
public class AvionRepository implements PanacheRepository<Avion> {

}
