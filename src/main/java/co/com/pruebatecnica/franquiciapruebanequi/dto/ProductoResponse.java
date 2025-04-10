package co.com.pruebatecnica.franquiciapruebanequi.dto;

public class ProductoResponse {
    private String Id_Producto;
    private String Id_Sucursal;
    private String nombreProducto;
    private String mensaje;


    public ProductoResponse() {}

    public ProductoResponse(String nombreProducto, String Id_Producto, String mensaje, String Id_Sucursal) {
        this.nombreProducto = nombreProducto;
        this.Id_Producto = Id_Producto;
        this.mensaje = mensaje;
        this.Id_Sucursal = Id_Sucursal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getId_Producto() {
        return Id_Producto;
    }
    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
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
