package co.com.pruebatecnica.franquiciapruebanequi.dto;
import java.util.List;
public class SucursalIds {
    private String Id_Sucursal;


    public SucursalIds() {}

    public SucursalIds(String Id_Sucursal) {
        this.setNombreSucursal(Id_Sucursal);

    }

    public String getNombreSucursal() {
        return Id_Sucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.Id_Sucursal = Id_Sucursal;
    }



}
