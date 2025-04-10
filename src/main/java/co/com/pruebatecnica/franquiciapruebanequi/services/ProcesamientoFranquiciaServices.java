package co.com.pruebatecnica.franquiciapruebanequi.services;

import co.com.pruebatecnica.franquiciapruebanequi.dto.*;
import co.com.pruebatecnica.franquiciapruebanequi.model.Franquicia;

import java.util.*;

import co.com.pruebatecnica.franquiciapruebanequi.model.Producto;
import co.com.pruebatecnica.franquiciapruebanequi.model.Sucursal;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcesamientoFranquiciaServices {
    private final DynamoDBMapper mapper;
    

    public ProcesamientoFranquiciaServices(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }
    //Funcion principal para guardar la franquicia inicial
public ResponseEntity<FranquiciaResponse> guardarDatos(Franquicias franquicia){
    Franquicia fran = new Franquicia();
    List<String> sucursales = new ArrayList<>();
    String randomId = UUID.randomUUID().toString();
    if (franquicia.getSucursales() != null) {
        List<String> IdSucursales = null;
        franquicia.getSucursales().forEach(sucursal -> {
            sucursal.setId_Sucursal(UUID.randomUUID().toString());
            sucursal.setId_Franquicia(randomId);

            sucursales.add(sucursal.getId_Sucursal());
        });}
    
try{
    fran.setSucursalList(sucursales);
    fran.setId(randomId);
    fran.setNombreFranquicia(franquicia.getNombreFranquicia());
    guardarDatosFranquicias(fran);
    guardarDatosSucursales(franquicia.getSucursales());
    FranquiciaResponse franquiciaResponse = new FranquiciaResponse();
    franquiciaResponse.setNombreFranquicia(franquicia.getNombreFranquicia());
    franquiciaResponse.setId_Franquicia(randomId);
    franquiciaResponse.setMensaje("Franquicia creada exitosamente");

    return new ResponseEntity<FranquiciaResponse>(franquiciaResponse, HttpStatus.OK);} catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
//Funcion para guardar en base de datos la franquicia
    public void guardarDatosFranquicias(Franquicia franquicia) {
        franquicia.setSucursalList(franquicia.getSucursalList());
        mapper.save(franquicia);
    }
//funcion para guardar cada sucursal de un listado de sucursales de una franquicia
    public void guardarDatosSucursales(List<Sucursales> sucursales) {
        Sucursal sucursal = new Sucursal();
        for (Sucursales sucursalElement : sucursales) {
            guardarSucursal(sucursalElement);

        }
    }
//funcion para guardar una sucursal en tabla SUCURSALES de Dynamo
    public void guardarSucursal(Sucursales sucursales){
        Sucursal sucursal = new Sucursal();
        List<String> productosId = new ArrayList<>();
        if (sucursales.getProductoList() != null) {
            sucursales.getProductoList().forEach(producto -> {
                String randomProductId = UUID.randomUUID().toString();
                producto.setId(randomProductId);
                producto.setId_Sucursal(sucursales.getId_Sucursal());
                productosId.add(randomProductId);
                mapper.save(producto);
            });}
            sucursal.setProductoList(productosId);
            sucursal.setId_Franquicia(sucursales.getId_Franquicia());
            sucursal.setId(sucursales.getId_Sucursal());
            sucursal.setNombreSucursal(sucursales.getNombreSucursal());
            mapper.save(sucursal);
    }
//Funcion para agregar una sucursal a Franquicia ya existente
    public ResponseEntity<SucursalResponse> guardarNuevaSucursal(Sucursales sucursales) {
        String fran = sucursales.getId_Franquicia();
        String idSucursal = UUID.randomUUID().toString();
        Franquicia franquicia = consultaFranquicia(fran);
        if (franquicia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SucursalResponse(null, sucursales.getId_Franquicia(), "El Id_Franquicia proporcionado no existe",null));
        }
        List<String> newSucursal=franquicia.getSucursalList();
        newSucursal.add(idSucursal);
        franquicia.setSucursalList(newSucursal);
        guardarDatosFranquicias(franquicia);
        sucursales.setId_Sucursal(idSucursal);
        guardarSucursal(sucursales);
        SucursalResponse sucursalResponse = new SucursalResponse();
        sucursalResponse.setNombreSucursal(sucursales.getNombreSucursal());
        sucursalResponse.setId_Franquicia(sucursales.getId_Franquicia());
        sucursalResponse.setId_Sucursal(idSucursal);
        sucursalResponse.setMensaje("Sucursal creada exitosamente");
        return new ResponseEntity<SucursalResponse>(sucursalResponse, HttpStatus.OK);
    }
//consulta datos de franquicia
    public Franquicia consultaFranquicia (String Id_Franquicia){
        return mapper.load(Franquicia.class, Id_Franquicia);
    }
//Consulta datos de Sucursal
    public Sucursal consultaSucursal(String Id_Sucursal){
        return mapper.load(Sucursal.class, Id_Sucursal);
    }
//Consulta de datos de producto
    public Producto consultaProducto(String Id_Producto){
        return mapper.load(Producto.class, Id_Producto);
    }
//Funcion para guardar productos en sucursal existente
    public ResponseEntity<ProductoResponse> guardarProductos (Producto producto ){

        String sucurId = producto.getId_Sucursal();
        String idProducto = UUID.randomUUID().toString();
        Sucursal sucursal = consultaSucursal(sucurId);
        if (sucursal == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, sucurId, "El Id_Sucursal proporcionado no existe",null));
        }
        List<String> newProducts = sucursal.getProductoList();
        newProducts.add(idProducto);
        sucursal.setProductoList(newProducts);
        mapper.save(sucursal);
        producto.setId(idProducto);
        mapper.save(producto);
        ProductoResponse productoResponse = new ProductoResponse();
        productoResponse.setId_Producto(idProducto);
        productoResponse.setNombreProducto(producto.getNombreProducto());
        productoResponse.setId_Sucursal(producto.getId_Sucursal());
        productoResponse.setMensaje("Producto creado exitosamente!");
        return new ResponseEntity<ProductoResponse>(productoResponse, HttpStatus.OK);

    }
//funcion para eliminar productos de una sucursal
    public ResponseEntity<ProductoResponse> eliminarProductos (Producto producto ){

        String sucurId = producto.getId_Sucursal();
        String idProducto = producto.getId();
        Sucursal sucursal = consultaSucursal(sucurId);
        if (sucursal == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, sucurId, "El Id_Sucursal proporcionado no existe",null));
        }
        Producto product = consultaProducto(idProducto);
        if(product!=null){
        List<String> newProducts = sucursal.getProductoList();
        newProducts.remove(idProducto);
        sucursal.setProductoList(newProducts);
        mapper.save(sucursal);
        producto.setId(idProducto);
        mapper.delete(producto);
        ProductoResponse productoResponse = new ProductoResponse();
        productoResponse.setId_Producto(idProducto);
        productoResponse.setNombreProducto(producto.getNombreProducto());
        productoResponse.setId_Sucursal(producto.getId_Sucursal());
        productoResponse.setMensaje("Producto eliminado exitosamente!");
        return new ResponseEntity<ProductoResponse>(productoResponse, HttpStatus.OK);}
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, idProducto, "El Id_Producto proporcionado no existe",null));
        }

    }
//funcion para actualizar el stock de un producto
    public ResponseEntity<ProductoResponse> modificarStockProductos (Producto producto ){

        String sucurId = producto.getId_Sucursal();
        String idProducto = producto.getId();

        Producto product = consultaProducto(idProducto);
        if(product!=null){

            producto.setId(idProducto);
            producto.setNombreProducto(product.getNombreProducto());
            producto.setId_Sucursal(product.getId_Sucursal());
            producto.setStock(producto.getStock());
            mapper.save(producto);
            ProductoResponse productoResponse = new ProductoResponse();
            productoResponse.setId_Producto(idProducto);
            productoResponse.setNombreProducto(producto.getNombreProducto());
            productoResponse.setId_Sucursal(producto.getId_Sucursal());
            productoResponse.setMensaje("Stock de producto actualizado exitosamente!");
            return new ResponseEntity<ProductoResponse>(productoResponse, HttpStatus.OK);}
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductoResponse(null, idProducto, "El Id_Producto proporcionado no existe",null));
        }

    }
//funcion de ordenamiento de productos con mayor stock de cada sucursal de una franquicia
    public ResponseEntity<FranquiciaResponseMayorStock> obtenerMAyorStock(String Id_Franquicia){
        Franquicia franquicia = consultaFranquicia(Id_Franquicia);
        if(franquicia!=null) {
            List<String> sucursales = franquicia.getSucursalList();
            Sucursal sucursal = null;
            List<Producto> productosMayorStock = new ArrayList<>();

            if (sucursales != null && !sucursales.isEmpty()) {
                for (String idSucursal : sucursales) {
                    sucursal = consultaSucursal(idSucursal);
                    List<String> productos = sucursal.getProductoList();
                    if (productos != null && !productos.isEmpty()) {
                        List<Producto> productoList = new ArrayList<>();
                        for (String idProducto : productos) {
                            productoList.add(consultaProducto(idProducto));
                        }

                        if (!productoList.isEmpty()) {
                            Producto productoConMayorStock = productoList.stream()
                                    .max(Comparator.comparingInt(Producto::getStock))
                                    .orElse(null);

                            if (productoConMayorStock != null) {
                                productosMayorStock.add(productoConMayorStock);
                            }
                        }
                    }
                }
            }

            FranquiciaResponseMayorStock response = new FranquiciaResponseMayorStock();
            response.setSucursales(productosMayorStock);
            response.setId_Franquicia(Id_Franquicia);
            response.setMensaje("Productos con mayor stock obtenidos exitosamente");
            response.setNombreFranquicia(franquicia.getNombreFranquicia());


            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new FranquiciaResponseMayorStock(null, Id_Franquicia, "El Id_Franquicia proporcionado no existe", null));
        }
    }



}
