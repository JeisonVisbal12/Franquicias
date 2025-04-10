package co.com.pruebatecnica.franquiciapruebanequi.controller;

import co.com.pruebatecnica.franquiciapruebanequi.dto.*;
import co.com.pruebatecnica.franquiciapruebanequi.model.Franquicia;
import co.com.pruebatecnica.franquiciapruebanequi.model.Producto;
import co.com.pruebatecnica.franquiciapruebanequi.model.Sucursal;
import co.com.pruebatecnica.franquiciapruebanequi.services.ProcesamientoFranquiciaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/franquiciasServices")
public class FranquiciasController {
    //@Autowirred
    private final ProcesamientoFranquiciaServices franquiciaServices;

    public FranquiciasController(ProcesamientoFranquiciaServices franquiciaServices) {
        this.franquiciaServices = franquiciaServices;
    }

    @PostMapping(value = "/franquiciaRegistry", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FranquiciaResponse> franquiciaRegistry(@RequestBody Franquicias franquicia) {
    ResponseEntity<FranquiciaResponse> response = franquiciaServices.guardarDatos(franquicia);
        FranquiciaResponse franquiciaResponse = new FranquiciaResponse();

            return response;

    }

    @PostMapping(value = "/crearSucursal", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SucursalResponse> sucursalRegistry(@RequestBody Sucursales sucursales) {
        if (sucursales.getId_Franquicia() == null || sucursales.getId_Franquicia().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SucursalResponse(null, null, "El Id_Franquicia es obligatorio",null));
        }
        ResponseEntity<SucursalResponse> response = franquiciaServices.guardarNuevaSucursal(sucursales);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SucursalResponse(null, null, "No se encontró la franquicia con el Id proporcionado",null));
        }


        return response;
    }
    @PostMapping(value = "/crearProducto",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductoResponse> productRegistry(@RequestBody Producto producto){
        ResponseEntity<ProductoResponse> response = franquiciaServices.guardarProductos(producto);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductoResponse(null, null, "No se encontró la sucursal con el Id proporcionado",null));
        }
        return response;
    }
    @PostMapping(value = "/eliminarProducto",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductoResponse> productDelete(@RequestBody Producto producto){
        if (producto.getId() == null || producto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, null, "El Id_Producto es obligatorio",null));
        }
        ResponseEntity<ProductoResponse> response = franquiciaServices.eliminarProductos(producto);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductoResponse(null, null, "No se encontró el producto con el Id proporcionado",null));
        }
        return response;
    }
    @PostMapping(value = "/actualizarStockProducto",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductoResponse> productStockUpdate(@RequestBody Producto producto){
        if (producto.getId() == null || producto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, null, "El Id_Producto es obligatorio",null));
        }
        ResponseEntity<ProductoResponse> response = franquiciaServices.modificarStockProductos(producto);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductoResponse(null, null, "No se encontró el producto con el Id proporcionado",null));
        }
        return response;
    }
    @GetMapping("/getMayorStock/{id}")
    public ResponseEntity<FranquiciaResponseMayorStock> obtenerPorId(@PathVariable("id") String id) {
        ResponseEntity<FranquiciaResponseMayorStock> franquicia= franquiciaServices.obtenerMAyorStock(id);
        if (franquicia != null) {
            return franquicia;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
