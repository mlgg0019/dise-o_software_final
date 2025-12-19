package src;

public class ProveedorFacade {
	public ClientController _unnamed_ClientController_;

	/**
	 * Comunica al proveedor un nuevo pedido o una modificación.
	 * @param aPedido El pedido completo (Entidad).
	 */
	public void enviarSolicitud(Object aPedido) { // Mantengo Object por compatibilidad con tu archivo, pero hacemos cast
		if (aPedido instanceof Pedido) {
			Pedido p = (Pedido) aPedido;
			System.out.println("FACADE (PROVEEDOR): Recibida solicitud de pedido ID: " + p.getID() + ". Procesando envío...");
		}
	}

	/**
	 * Comunica al proveedor la cancelación de un pedido.
	 * @param aPedido El pedido a cancelar.
	 */
	public void enviarBajaPedido(Pedido aPedido) {
		if (aPedido != null) {
			System.out.println("FACADE (PROVEEDOR): Solicitud de CANCELACIÓN recibida para el pedido ID: " + aPedido.getID());
			System.out.println("FACADE (PROVEEDOR): Stock liberado y envío detenido.");
		} else {
			System.err.println("FACADE: Error, intento de cancelar un pedido nulo.");
		}
	}
}