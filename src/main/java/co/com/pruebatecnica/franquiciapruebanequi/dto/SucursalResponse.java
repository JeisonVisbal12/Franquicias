package co.com.pruebatecnica.franquiciapruebanequi.dto;

public class SucursalResponse {
    private String Id_Franquicia;
    private String Id_Sucursal;
    private String nombreSucursal;
    private String mensaje;
    //private List<Sucursal> sucursales;

    public SucursalResponse() {}

    public SucursalResponse(String nombreSucursal, String Id_Franquicia, String mensaje, String Id_Sucursal) {
        this.nombreSucursal = nombreSucursal;
        this.Id_Franquicia = Id_Franquicia;
        this.mensaje = mensaje;
        this.Id_Sucursal = Id_Sucursal;
    }

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
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId_Sucursal() {
        return Id_Sucursal;
    }

    public void setId_Sucursal(String id_Sucursal) {
        Id_Sucursal = id_Sucursal;
    }
}
