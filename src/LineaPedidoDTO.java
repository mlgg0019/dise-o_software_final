package src;

public class LineaPedidoDTO {
    private int idProducto;
    private int cantidad;

    // Constructor vacío y con campos
    public LineaPedidoDTO() {}
    public LineaPedidoDTO(int id, int cant) {
        this.idProducto = id;
        this.cantidad = cant;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int id) { this.idProducto = id; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cant) { this.cantidad = cant; }
}