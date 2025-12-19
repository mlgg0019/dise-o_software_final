public class PedidoDTO {
	private int _idPedido;
	private Date _fecha;
	private String _proveedorNombre;
	private List<LineaPedidoDTO> _listaProductos;
	private String _estado;
	private Double _total;
}