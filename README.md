# Franquicias
Se ha desarrollado una aplicación en spring boot, que permite la administración de franquicias, sucursales y productos. Para esto se ha desarrollado un proyecto que cuenta con 6 endpoints, expuestos a través de AWS en donde se ha almacenado en un repositorio en ECR, la imagen de docker generada del proyecto, y expuesta a traves de ECS Fargate, para su exposición a través de una IP publica, junto con un consumo hacia una Base de Datos Dynamo DB, que cuenta con tres tablas definidas FRANQUICIAS, SUCURSALES y PRODUCTOS.

Para la generación de las APIs se ha realizado lo siguiente:
- un controller a través del cual se definen los metodos Post y get, junto con las rutas para el consumo de las APIs
- un service, para el manejo de las logicas de consultas, cargues, actualización y eliminación de los datos de Franquicias, sucursales y productos.
- 2 models que definen la conexión y parametros de campos hacia la BD de Dynamo y sus tablas
- definiciones dto para manejo de respuestas y estructuras de datos de cada tipo de listas o campos definidos para cada franquicia, sucursal o producto.
- se estableció un archivo de configurarción application.yml para la configuración de lectura de variables de entorno para conexión hacia BD expuesta en AWS
- archivos Dockerfile y docker-compose para configuración de contenedor Docker para posterior cargue en nube

#Consumos
- method: Post
- endpoint:http://3.89.138.39:8080/franquiciasServices/franquiciaRegistry
- descripçión: API para registro de Franquicia, con sus sucursales y productos 
- bodyRequest: {
  "nombreFranquicia": "Relacionar nombre franquicia",
  "sucursales": [
    {
      "nombreSucursal": "sucursal 98",
      "productoList": [
        {
          "nombreProducto": "feooo",
          "stock": "10"
        },
        {
          "nombreProducto": "Producto 189",
          "stock": "120"
        }
      ]
    },
    {
      "nombreSucursal": "sucursal 8",
      "productoList": [
        {
          "nombreProducto": "fbbe",
          "stock": "19"
        }
      ]
    },
    {
      "nombreSucursal": "sucursal 7",
      "productoList": [
        {
          "nombreProducto": "feb",
          "stock": "100"
        }
      ]
    }
  ]
}
- Reponse:
{
  "nombreFranquicia": "prueba Ultima",
  "mensaje": "Franquicia creada exitosamente",
  "id_Franquicia": "50ddc397-750c-4241-b107-eb06dc351f65"
}


- method: Post
- endpoint:http://3.89.138.39:8080/franquiciasServices/crearSucursal
- descripçión: API para crear sucursal asociada a una Franquicia, actualiza tablas FRANQUICIAS(listado de ID de sucursales relacionadas) y SUCURSALES
- bodyRequest:{
  "id_Franquicia": "50ddc397-750c-4241-b107-eb06dc351f65",
  "nombreSucursal": "sucursal 79",
  "productoList": [
    {
      "nombreProducto": "feooo",
      "stock": "100"
    },
    {
      "nombreProducto": "Producto 1",
      "stock": "120"
    }
  ]
}
- Response:{
  "nombreSucursal": "sucursal 79",
  "mensaje": "Sucursal creada exitosamente",
  "id_Franquicia": "50ddc397-750c-4241-b107-eb06dc351f65",
  "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696"
}

- method: Post
- endpoint:http://3.89.138.39:8080/franquiciasServices/crearProducto
- descripçión: API para crear producto asociado a una Sucursal, se actualiza la tabla SUCURSALES(id de los productos) y PRODUCTOS
- bodyRequest:{
  "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696",
  "nombreProducto": "fejb",
  "stock": 1820
}
- Response:{
  "nombreProducto": "fejb",
  "mensaje": "Producto creado exitosamente!",
  "id_Producto": "a901827e-ed4f-4c36-886a-00e22fc8caab",
  "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696"
}

- method: Post
- endpoint:http://3.89.138.39:8080/franquiciasServices/eliminarProducto
- descripçión: API que elimina el producto relacionado con su id_Producto y id_Sucursal
- bodyRequest:{
  "id_Sucursal": "45fe7169-9805-4811-ace9-e7a8efe045b6",
  "nombreProducto": "fejb",
  "stock": 200,
  "id_Producto": "4ef32fec-b9ee-496d-b17f-ecc5344f568a"
}
- Response:
{
  "nombreProducto": "fejb",
  "mensaje": "Producto eliminado exitosamente!",
  "id_Producto": "4ef32fec-b9ee-496d-b17f-ecc5344f568a",
  "id_Sucursal": "45fe7169-9805-4811-ace9-e7a8efe045b6"
}

- method: Post
- endpoint:http://3.89.138.39:8080/franquiciasServices/actualizarStockProducto
- descripçión: API que permite actualizar el stock de un producto relacionando id_Producto y id_Sucursal
- bodyRequest:{
  "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696",
  "nombreProducto": "fejb",
  "stock": 100000,
  "id_Producto": "a901827e-ed4f-4c36-886a-00e22fc8caab"
}
- Response:{
  "nombreProducto": "fejb",
  "mensaje": "Stock de producto actualizado exitosamente!",
  "id_Producto": "a901827e-ed4f-4c36-886a-00e22fc8caab",
  "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696"
}

- method: Get
- endpoint:http://3.89.138.39:8080/franquiciasServices/getMayorStock/${Id_Franquicia}
- descripçión: Api que devuelve el producto con mayor stock de cada una de las sucursales de una Franquicia
- Response: {
  "nombreFranquicia": "prueba Ultima",
  "mensaje": "Productos con mayor stock obtenidos exitosamente",
  "sucursales": [
    {
      "nombreProducto": "Producto 189",
      "stock": 120,
      "id": "b681e87b-69dd-4dfc-81a0-f7d2db0d9f9a",
      "id_Sucursal": "f704555d-2e4c-4488-be9d-a66f79cf0e2e",
      "id_Producto": "b681e87b-69dd-4dfc-81a0-f7d2db0d9f9a"
    },
    {
      "nombreProducto": "fbbe",
      "stock": 19,
      "id": "c8737ff2-1c29-4e3a-b8ea-59bcbcd28e22",
      "id_Sucursal": "1b95e589-3ee9-4514-8fbb-a2b0e13c997d",
      "id_Producto": "c8737ff2-1c29-4e3a-b8ea-59bcbcd28e22"
    },
    {
      "nombreProducto": "fejb",
      "stock": 100000,
      "id": "a901827e-ed4f-4c36-886a-00e22fc8caab",
      "id_Sucursal": "4fec58fe-12bb-4b3d-a691-63d239ec1696",
      "id_Producto": "a901827e-ed4f-4c36-886a-00e22fc8caab"
    }
  ],
  "id_Franquicia": "50ddc397-750c-4241-b107-eb06dc351f65"
}
