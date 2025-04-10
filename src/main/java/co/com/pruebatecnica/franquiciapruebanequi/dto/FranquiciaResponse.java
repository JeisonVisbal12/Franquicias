package co.com.pruebatecnica.franquiciapruebanequi.dto;
import co.com.pruebatecnica.franquiciapruebanequi.model.Sucursal;
import software.amazon.awssdk.services.dynamodb.endpoints.internal.Value;

import java.util.ArrayList;
import java.util.List;
public class FranquiciaResponse {
    private String Id_Franquicia;
    private String nombreFranquicia;
    private String mensaje;


    public FranquiciaResponse () {}

    public FranquiciaResponse(String nombreFranquicia, String Id_Franquicia, String mensaje) {
        this.nombreFranquicia = nombreFranquicia;
        this.Id_Franquicia = Id_Franquicia;
        this.mensaje = mensaje;
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


}
