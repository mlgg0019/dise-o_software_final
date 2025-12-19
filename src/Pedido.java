package src;

import java.util.Vector;

public class Pedido {
	// --- Atributos Originales (generados por Visual Paradigm) ---
	private int _iD;
	private String _estado;

	// Relaciones públicas generadas (las mantenemos para compatibilidad)
	public Observador _unnamed_Observador_;
	public PedidoDAO _unnamed_PedidoDAO_;
	public Proveedor _unnamed_Proveedor_;

	// Lista de productos (inicializada para evitar NullPointerException)
	public Vector<Producto> _productos = new Vector<Producto>();

	// --- Constructores ---

	/**
	 * Constructor vacío.
	 * Inicializa el estado por defecto.
	 */
	public Pedido() {
		this._estado = "PENDIENTE";
	}

	/**
	 * Constructor que crea un Pedido a partir de un DTO.
	 * Usado en la operación "Solicitar Pedido" (Alta).
	 */
	public Pedido(PedidoDTO datos) {
		this._estado = "PENDIENTE";
		// Aquí mapearíamos los productos si el DTO los trae
		// Por ejemplo: cargarProductosDesdeDTO(datos.getListaProductos());
	}

	// --- Getters y Setters ---

	public int getID() {
		return _iD;
	}

	public void setID(int id) {
		this._iD = id;
	}

	public String getEstado() {
		return _estado;
	}

	public void setEstado(String estado) {
		this._estado = estado;
	}

	public Vector<Producto> getProductos() {
		return _productos;
	}

	public void setProductos(Vector<Producto> productos) {
		this._productos = productos;
	}

	// --- Métodos de Lógica de Negocio ---

	/**
	 * Actualiza los datos del pedido con la nueva información del DTO.
	 * Usado en la operación "Modificar Pedido".
	 */
	public void setDatos(PedidoDTO nuevosDatos) {
		// Actualizamos los campos permitidos
		// Nota: El ID y el Estado generalmente no se cambian aquí, sino por separado

		// Ejemplo: Si el DTO tuviera lista de productos nueva, la actualizamos
		// this._productos.clear();
		// ... lógica para añadir nuevos productos ...

		System.out.println("PEDIDO: Datos actualizados para el pedido ID " + this._iD);
	}

	// Método auxiliar para añadir productos fácilmente (útil para tests)
	public void agregarProducto(Producto p) {
		this._productos.add(p);
	}
}