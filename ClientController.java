import java.util.List;

public class ClientController implements Observador {
	private PedidoDAO _pedidoDAO;
	private ProductoDAO _productoDAO;
	private EstrategiaValidacion_ _validador;
	private Object _attribute;
	public ProveedorFacade _unnamed_ProveedorFacade_;
	public EstrategiaValidacion_ _unnamed_EstrategiaValidacion__;
	public VentanaConsultarProductos _unnamed_VentanaConsultarProductos_;
	public VentanaConfirmarModificacion _unnamed_VentanaConfirmarModificacion_;
	public VentanaSolicitarPedido _unnamed_VentanaSolicitarPedido_;
	public VentanaGestionarPedidos _unnamed_VentanaGestionarPedidos_;
	public VentanaModificarPedido _unnamed_VentanaModificarPedido_;
	public VentanaConfirmarEliminarPedido _unnamed_VentanaConfirmarEliminarPedido_;
	public VentanaConfirmarRecepcionPedido _unnamed_VentanaConfirmarRecepcionPedido_;
	public VentanaModificarProducto _unnamed_VentanaModificarProducto_;
	public VentanaVolverModificacion _unnamed_VentanaVolverModificacion_;
	public VentanaVistaProducto _unnamed_VentanaVistaProducto_;
	public Restaurante _unnamed_Restaurante_;
	public PedidoDAO _unnamed_PedidoDAO_;
	public ProductoDAO _unnamed_ProductoDAO_;

	public void verProductos() {
		throw new UnsupportedOperationException();
	}

	public void solicitarPedido(Object aDatosPedido) {
		throw new UnsupportedOperationException();
	}

	public void pedidoConfirmiado(Object aPedido) {
		throw new UnsupportedOperationException();
	}

	public List<ProductoDTO> obtenerListaProductos() {
		throw new UnsupportedOperationException();
	}

	public void procesarAltaProducto(ProductoDTO aDatos) {
		throw new UnsupportedOperationException();
	}

	public void eliminarProducto(int aId) {
		throw new UnsupportedOperationException();
	}

	public List<PedidoDTO> obtenerListaPedidos() {
		throw new UnsupportedOperationException();
	}

	public void pedidoConfirmado(int aId) {
		throw new UnsupportedOperationException();
	}

	public void procesarBajaPedido(int aId) {
		throw new UnsupportedOperationException();
	}

	public void procesarModificacionPedido(int aId, PedidoDTO aDatos) {
		throw new UnsupportedOperationException();
	}

	public void actualizar() {
		throw new UnsupportedOperationException();
	}
}