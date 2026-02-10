package uce.edu.reservas.application.representation;

import java.util.List;

import uce.edu.reservas.domain.Avion;

public class AvionRepresentation {
    private Integer id;
    private String modelo;
    private Integer capacidad;
    private String aerolinea;
    private String clase;
    private String espacioEquipaje;
    private List<LinkDTO> links;

    public static AvionRepresentation mapper(Avion avion) {
        AvionRepresentation aviR = new AvionRepresentation();
        aviR.setId(avion.getId());
        aviR.setModelo(avion.getModelo());
        aviR.setCapacidad(avion.getCapacidad());
        aviR.setAerolinea(avion.getAerolinea());
        aviR.setClase(avion.getClase());
        aviR.setEspacioEquipaje(avion.getEspacioEquipaje());
        return aviR;
    }

    public static Avion mappearAvion(AvionRepresentation avion) {
        Avion avi = new Avion();
        avi.setId(avion.getId());
        avi.setModelo(avion.getModelo());
        avi.setCapacidad(avion.getCapacidad());
        avi.setAerolinea(avion.getAerolinea());
        avi.setClase(avion.getClase());
        avi.setEspacioEquipaje(avion.getEspacioEquipaje());
        return avi;
    }

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
