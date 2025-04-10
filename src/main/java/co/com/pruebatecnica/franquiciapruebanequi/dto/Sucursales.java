package co.com.pruebatecnica.franquiciapruebanequi.dto;

import co.com.pruebatecnica.franquiciapruebanequi.model.Producto;
import lombok.Setter;

import java.util.List;

public class Sucursales {

    private String Id_Sucursal;
    private String nombreSucursal;
    private String Id_Franquicia;
    private List<Producto> productoList;

    public Sucursales() {}

    public Sucursales(String Id_Sucursal, String nombreSucursal, String Id_Franquicia, List<Producto> productoList) {
        this.Id_Sucursal = Id_Sucursal;
        this.nombreSucursal= nombreSucursal;
        this.Id_Franquicia=Id_Franquicia;
        this.productoList=productoList;

    }
    public String getId_Sucursal(){return Id_Sucursal;}
    public void setId_Sucursal(String Id_Sucursal) {this.Id_Sucursal = Id_Sucursal;}

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
    
    public String getId_Franquicia() {
        return Id_Franquicia;
    }

    public void setId_Franquicia(String Id_Franquicia) {
        this.Id_Franquicia = Id_Franquicia;
    }

    public List<Producto> getProductoList(){return productoList;}

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }
}
