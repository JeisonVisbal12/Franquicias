package co.com.pruebatecnica.franquiciapruebanequi.dto;

import co.com.pruebatecnica.franquiciapruebanequi.model.Sucursal;

import java.util.List;
public class Franquicias {
    private String nombreFranquicia;
    private List<Sucursales> sucursales;

    public Franquicias() {}

    public Franquicias(String nombreFranquicia, List<Sucursales> sucursales) {
        this.nombreFranquicia = nombreFranquicia;
        this.sucursales = sucursales;
    }

    public String getNombreFranquicia() {
        return nombreFranquicia;
    }
    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
    public List<Sucursales> getSucursales() {
        return sucursales;
    }
    public void setSucursales(List<Sucursales> sucursales) {
        this.sucursales = sucursales;
    }


}
