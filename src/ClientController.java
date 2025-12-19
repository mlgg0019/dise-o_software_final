package src;

import java.util.List;

public class ClientController implements Observador {

	private PedidoDAO _pedidoDAO;
	private EstrategiaValidacion_ _validador;
	public ProveedorFacade _unnamed_ProveedorFacade_; // Fachada
	public VentanaGestionarPedidos _unnamed_VentanaGestionarPedidos_; // Vista Principal

	public ClientController(PedidoDAO dao, ProveedorFacade facade, EstrategiaValidacion_ validador, VentanaGestionarPedidos vista) {
		this._pedidoDAO = dao;
		this._unnamed_ProveedorFacade_ = facade;
		this._validador = validador;
		this._unnamed_VentanaGestionarPedidos_ = vista;
	}

	// SOLICITAR PEDIDO
	public void solicitarPedido(PedidoDTO aDatosPedido) {
		//Validar (Estrategia)
		if (_validador.ValidacionDatosPedido(aDatosPedido)) {

			//Crear (Modelo)
			Pedido nuevoPedido = new Pedido(aDatosPedido);

			//Persistir inicial (DAO)
			_pedidoDAO.insertar(nuevoPedido);

			//Comunicar Proveedor (Fachada)
			_unnamed_ProveedorFacade_.enviarSolicitud(nuevoPedido);

			//Confirmar y Actualizar (DAO)
			nuevoPedido.setEstado("CONFIRMADO");
			_pedidoDAO.actualizar(nuevoPedido);

			//Actualizar Vista
			refrescarVista("Pedido creado con éxito");
		}
	}

	//BAJA PEDIDO
	public void procesarBajaPedido(int aId) {
		// Buscar (DAO)
		Pedido pedido = _pedidoDAO.buscarPedido(aId);

		if (pedido != null) {
			// Avisar Proveedor (Fachada)
			_unnamed_ProveedorFacade_.enviarBajaPedido(pedido);

			// Cambiar Estado (Modelo)
			pedido.setEstado("CANCELADO");

			// Guardar Cambios (DAO)
			_pedidoDAO.actualizar(pedido);

			//Actualizar Vista
			refrescarVista("Pedido cancelado correctamente");
		}
	}

	//MODIFICAR PEDIDO
	public void procesarModificacionPedido(int aId, PedidoDTO aDatos) {
		//Buscar (DAO)
		Pedido pedido = _pedidoDAO.buscarPedido(aId);

		//Validar (Estrategia)
		if (pedido != null && _validador.ValidacionDatosPedido(aDatos)) {

			// Modificar Memoria
			pedido.setDatos(aDatos);

			// Avisar Proveedor (Fachada)
			_unnamed_ProveedorFacade_.enviarSolicitud(pedido);

			// Guardar (DAO)
			_pedidoDAO.actualizar(pedido);

			// Actualizar Vista
			refrescarVista("Pedido modificado con éxito");
		}
	}


	private void refrescarVista(String mensaje) {
		// Actualizar tabla
		List<Pedido> listaActualizada = _pedidoDAO.obtenerTodos();
		_unnamed_VentanaGestionarPedidos_.actualizaListaPedidos(listaActualizada);

		// Mostrar mensaje (suponiendo que existe un metodo para mostrar mensaje en la vista)
		System.out.println("VISTA: " + mensaje);
	}

	@Override
	public void actualizar() {
		// Implementación de observer si fuera necesaria
	}
}