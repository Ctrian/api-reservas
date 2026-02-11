package uce.edu.reservas.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pasajero")
@SequenceGenerator(name = "pasajero_seq", sequenceName = "pasajero_sequence", allocationSize = 1)
public class Pasajero extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pasajero_seq")
    private Integer id;

    private String nombre;

    private String apellido;

    private String cedula;

    private LocalDateTime fechaNacimiento;

    private String telefono;

    private String correo;

    @OneToMany(mappedBy = "pasajero", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
