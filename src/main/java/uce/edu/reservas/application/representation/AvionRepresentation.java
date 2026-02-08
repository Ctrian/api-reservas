package uce.edu.reservas.application.representation;

import java.util.List;

public class AvionRepresentation {
    private Integer id;
    private String modelo;
    private Integer capacidad;
    private String aerolinea;
    private String clase;
    private String espacioEquipaje;
    private List<LinkDTO> links;

    // Getters and Setters

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

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }
}
