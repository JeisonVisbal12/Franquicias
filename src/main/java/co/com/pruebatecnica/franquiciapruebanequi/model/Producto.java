package co.com.pruebatecnica.franquiciapruebanequi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@DynamoDBTable(tableName = "PRODUCTOS")
public class Producto {
    @JsonProperty("id_Producto")
    private String Id_Producto;

    private String nombreProducto;

    private String Id_Sucursal;

    private int stock;

    @DynamoDBHashKey(attributeName = "Id_Producto")
    public String getId(){return Id_Producto;}

    public void setId(String id_Producto) {
        this.Id_Producto = id_Producto;
    }

    @DynamoDBAttribute(attributeName = "nombreProducto")
    public String getNombreProducto(){return nombreProducto;}

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @DynamoDBAttribute(attributeName = "Id_Sucursal")
    public String getId_Sucursal(){return Id_Sucursal;}

    public void setId_Sucursal(String Id_Sucursal) {
        this.Id_Sucursal = Id_Sucursal;
    }

    @DynamoDBAttribute(attributeName = "stock")
    public int getStock(){return stock;}

    public void setStock(int stock) {
        this.stock = stock;
    }
}
