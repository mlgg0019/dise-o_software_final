package src;

public class EstrategiaValidacion_ {
	public ClientController _unnamed_ClientController_;

	/**
	 * Valida si los datos del pedido cumplen las reglas de negocio.
	 * @param aDatos Los datos que vienen de la vista (DTO).
	 * @return true si son válidos, false si hay errores.
	 */
	public boolean ValidacionDatosPedido(PedidoDTO aDatos) {
		// 1. Verificar que el objeto no sea nulo
		if (aDatos == null) {
			System.err.println("VALIDACIÓN: El DTO es nulo.");
			return false;
		}

		// Verificar reglas específicas (Simulacion ) Por ejemplo: que tenga al menos un producto, que el total sea positivo, etc.

		System.out.println("VALIDACIÓN: Datos del pedido verificados correctamente.");
		return true;
	}
}