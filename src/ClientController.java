package src;

import java.util.List;

public class ClientController implements Observador {

	// Atributos del diagrama de clases (con tu nomenclatura)
	private PedidoDAO _pedidoDAO;
	private EstrategiaValidacion_ _validador;
	public ProveedorFacade _unnamed_ProveedorFacade_; // Fachada
	public VentanaGestionarPedidos _unnamed_VentanaGestionarPedidos_; // Vista Principal

	// Constructor para Inyección de Dependencias (Vital para el Test)
	public ClientController(PedidoDAO dao, ProveedorFacade facade, EstrategiaValidacion_ validador, VentanaGestionarPedidos vista) {
		this._pedidoDAO = dao;
		this._unnamed_ProveedorFacade_ = facade;
		this._validador = validador;
		this._unnamed_VentanaGestionarPedidos_ = vista;
	}

	// --- CASO 1: SOLICITAR PEDIDO (ALTA) ---
	// En el diagrama: solicitarAlta(datos)
	public void solicitarPedido(PedidoDTO aDatosPedido) {
		// 1. Validar (Estrategia)
		if (_validador.ValidacionDatosPedido(aDatosPedido)) {

			// 2. Crear (Modelo)
			Pedido nuevoPedido = new Pedido(aDatosPedido);

			// 3. Persistir inicial (DAO)
			_pedidoDAO.insertar(nuevoPedido);

			// 4. Comunicar Proveedor (Fachada)
			_unnamed_ProveedorFacade_.enviarSolicitud(nuevoPedido);

			// 5. Confirmar y Actualizar (DAO)
			nuevoPedido.setEstado("CONFIRMADO");
			_pedidoDAO.actualizar(nuevoPedido);

			// 6. Actualizar Vista
			refrescarVista("Pedido creado con éxito");
		}
	}

	// --- CASO 2: BAJA PEDIDO (CANCELAR) ---
	// En el diagrama: procesarBajaPedido(id)
	public void procesarBajaPedido(int aId) {
		// 1. Buscar (DAO)
		Pedido pedido = _pedidoDAO.buscarPedido(aId);

		if (pedido != null) {
			// 2. Avisar Proveedor (Fachada)
			_unnamed_ProveedorFacade_.enviarBajaPedido(pedido);

			// 3. Cambiar Estado (Modelo)
			pedido.setEstado("CANCELADO");

			// 4. Guardar Cambios (DAO)
			_pedidoDAO.actualizar(pedido);

			// 5. Actualizar Vista
			refrescarVista("Pedido cancelado correctamente");
		}
	}

	// --- CASO 3: MODIFICAR PEDIDO ---
	// En el diagrama: procesarModificacion(id, datos)
	public void procesarModificacionPedido(int aId, PedidoDTO aDatos) {
		// 1. Buscar (DAO)
		Pedido pedido = _pedidoDAO.buscarPedido(aId);

		// 2. Validar (Estrategia)
		if (pedido != null && _validador.ValidacionDatosPedido(aDatos)) {

			// 3. Modificar Memoria
			pedido.setDatos(aDatos);

			// 4. Avisar Proveedor (Fachada)
			// Asumimos que enviarSolicitud sirve para actualizar o existe un metodo específico
			_unnamed_ProveedorFacade_.enviarSolicitud(pedido);

			// 5. Guardar (DAO)
			_pedidoDAO.actualizar(pedido);

			// 6. Actualizar Vista
			refrescarVista("Pedido modificado con éxito");
		}
	}

	// Método auxiliar para no repetir código de UI
	private void refrescarVista(String mensaje) {
		// Actualizar tabla
		List<Pedido> listaActualizada = _pedidoDAO.obtenerTodos();
		_unnamed_VentanaGestionarPedidos_.actualizaListaPedidos(listaActualizada);

		// Mostrar mensaje (suponiendo que existe un método para mostrar mensaje en la vista genérica)
		// _unnamed_VentanaGestionarPedidos_.mostrarMensaje(mensaje);
		System.out.println("VISTA: " + mensaje);
	}

	@Override
	public void actualizar() {
		// Implementación de observer si fuera necesaria
	}
}