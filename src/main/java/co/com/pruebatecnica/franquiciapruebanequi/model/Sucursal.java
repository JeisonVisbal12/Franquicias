package co.com.pruebatecnica.franquiciapruebanequi.model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;

import java.util.List;

@DynamoDBTable(tableName = "SUCURSALES")
public class Sucursal {


    private String Id_Sucursal;

    private String nombreSucursal;

    private String Id_Franquicia;

    private List<String> productoList;

    @DynamoDBHashKey(attributeName = "Id_Sucursal")
    public String getId(){return Id_Sucursal;}

    public void setId(String id_Sucursal) {
        Id_Sucursal = id_Sucursal;
    }

    @DynamoDBAttribute(attributeName = "nombreSucursal")
    public String getNombreSucursal(){return nombreSucursal;}

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    @DynamoDBAttribute(attributeName = "Id_Franquicia")
    public String getId_Franquicia(){return Id_Franquicia;}

    public void setId_Franquicia(String id_Franquicia) {
        Id_Franquicia = id_Franquicia;
    }

    @DynamoDBAttribute(attributeName = "productoList")
    //@DynamoDBTypeConvertedJson
    public List<String> getProductoList(){return productoList;}
    public void setProductoList(List<String> productoList) {
        this.productoList = productoList;
    }

}
