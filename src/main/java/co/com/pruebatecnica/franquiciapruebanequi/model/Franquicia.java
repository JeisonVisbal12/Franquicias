package co.com.pruebatecnica.franquiciapruebanequi.model;

import co.com.pruebatecnica.franquiciapruebanequi.dto.SucursalIds;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;

import java.util.List;

@DynamoDBTable(tableName = "FRANQUICIAS")
public class Franquicia {

    private String Id_Franquicia;

    private String nombreFranquicia;


    private List<String> sucursalList;

    @DynamoDBHashKey(attributeName = "Id_Franquicia")
    public String getId(){return Id_Franquicia;}
    public void setId(String Id_Franquicia){
        this.Id_Franquicia = Id_Franquicia;
    }
    @DynamoDBAttribute(attributeName = "nombreFranquicia")
    public String getNombreFranquicia(){return nombreFranquicia;}
    public void setNombreFranquicia(String nombreFranquicia){
        this.nombreFranquicia = nombreFranquicia;
    }
    @DynamoDBAttribute(attributeName = "sucursalList")
    @DynamoDBTypeConvertedJson
    public List<String> getSucursalList(){return sucursalList;}
    public void setSucursalList(List<String> sucursalList){
        this.sucursalList = sucursalList;
    }
}
