package src;

import java.util.Vector;

public class Pedido {
	private int _iD;
	private String _estado;

	// Relaciones generadas
	public Observador _unnamed_Observador_;
	public PedidoDAO _unnamed_PedidoDAO_;
	public Proveedor _unnamed_Proveedor_;

	// Lista de productos
	public Vector<Producto> _productos = new Vector<Producto>();

	//Constructor vacio
	public Pedido() {
		this._estado = "PENDIENTE";
	}



	//Constructor que crea un Pedido a partir de un DTO.
	public Pedido(PedidoDTO datos) {
		this._estado = "PENDIENTE";
		// Aquí mapearíamos los productos si el DTO los trae

	}

	//  Getters y Setters

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

	// Métodos de Lógica de Negocio

	/**
	 * Actualiza los datos del pedido con la nueva información del DTO.
	 * Usado en la operación "Modificar Pedido".
	 */
	public void setDatos(PedidoDTO nuevosDatos) {
		// Actualizamos los campos permitidos

		// Ejemplo: Si el DTO tuviera lista de productos nueva, la actualizamos (this._productos.clear();)

		System.out.println("PEDIDO: Datos actualizados para el pedido ID " + this._iD);
	}

	// Metodo auxiliar para añadir productos
	public void agregarProducto(Producto p) {
		this._productos.add(p);
	}
}