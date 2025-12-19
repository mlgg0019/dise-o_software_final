package src;

public class DatosProduto {

	private int _id;
	private String _nombre;
	private String _descripcion;
	private int _cantidad;
	private int _stockMinimo;

	// Referencia al DAO
	public ProductoDAO _unnamed_ProductoDAO_;

	// Constructor vacío
	public DatosProduto() {
	}

	//Constructor con argumentos
	public DatosProduto(int id, String nombre, int cantidad) {
		this._id = id;
		this._nombre = nombre;
		this._cantidad = cantidad;
	}

	//Getters y Setters

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String descripcion) {
		this._descripcion = descripcion;
	}

	public int getCantidad() {
		return _cantidad;
	}

	public void setCantidad(int cantidad) {
		this._cantidad = cantidad;
	}

	public int getStockMinimo() {
		return _stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this._stockMinimo = stockMinimo;
	}
}