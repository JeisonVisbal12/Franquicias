package co.com.pruebatecnica.franquiciapruebanequi.dto;
import co.com.pruebatecnica.franquiciapruebanequi.model.Producto;
import co.com.pruebatecnica.franquiciapruebanequi.model.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class FranquiciaResponseMayorStock {
    private String Id_Franquicia;
    private String nombreFranquicia;
    private String mensaje;
    private List<Producto> sucursales = new ArrayList<>();

    public FranquiciaResponseMayorStock() {}

    public FranquiciaResponseMayorStock(String nombreFranquicia, String Id_Franquicia, String mensaje, List<Producto> sucursales) {
        this.nombreFranquicia = nombreFranquicia;
        this.Id_Franquicia = Id_Franquicia;
        this.mensaje = mensaje;
        this.sucursales = sucursales;
    }

    public String getNombreFranquicia() {
        return nombreFranquicia;
    }
    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
    public String getId_Franquicia() {
        return Id_Franquicia;
    }
    public void setId_Franquicia(String Id_Franquicia) {
        this.Id_Franquicia = Id_Franquicia;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Producto> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Producto> sucursales) {
        this.sucursales = sucursales;
    }
}
