package uce.edu.reservas.application.representation;

import java.util.List;

import uce.edu.reservas.domain.Pasajero;

public class PasajeroRepresentation {
    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String correo;
    private List<LinkDTO> links;

    public static PasajeroRepresentation toRepresentation(Pasajero pasajero) {
        PasajeroRepresentation representation = new PasajeroRepresentation();
        representation.setId(pasajero.getId());
        representation.setNombre(pasajero.getNombre());
        representation.setApellido(pasajero.getApellido());
        representation.setCedula(pasajero.getCedula());
        representation.setTelefono(pasajero.getTelefono());
        representation.setCorreo(pasajero.getCorreo());
        return representation;
    }

    public static Pasajero toEntity(PasajeroRepresentation representation) {
        Pasajero entity = new Pasajero();
        entity.setId(representation.getId());
        entity.setNombre(representation.getNombre());
        entity.setApellido(representation.getApellido());
        entity.setCedula(representation.getCedula());
        entity.setTelefono(representation.getTelefono());
        entity.setCorreo(representation.getCorreo());
        return entity;
    }

    // Getters and Setters

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }
}
