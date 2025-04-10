package co.com.pruebatecnica.franquiciapruebanequi.dto;

public class ProductosIds {
    private String nombreProducto;
    private int stockProducto;

    public ProductosIds() {}

    public ProductosIds(String nombreProducto, int stockProducto) {
        this.setNombreProducto(nombreProducto);
        this.setStockProducto(stockProducto);
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

}
