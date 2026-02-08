package uce.edu.reservas.domain;

import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "avion")
@SequenceGenerator(name = "avion_seq", sequenceName = "avion_sequence", allocationSize = 1)
public class Avion extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    private Integer id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "aerolinea")
    private String aerolinea;

    @Column(name = "clase")
    private String clase;

    @Column(name = "espacio_equipaje")
    private String espacioEquipaje;

    @OneToMany(mappedBy = "avion")
    private Set<Reserva> reservas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getEspacioEquipaje() {
        return espacioEquipaje;
    }

    public void setEspacioEquipaje(String espacioEquipaje) {
        this.espacioEquipaje = espacioEquipaje;
    }
}
