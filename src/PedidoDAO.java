package src;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class PedidoDAO {
	// Simulación de BBDD
	private Map<Integer, Pedido> bdd = new HashMap<>();
	private int idGen = 1;

	public void insertar(Pedido aPedido) {
		aPedido.setID(idGen++);
		bdd.put(aPedido.getID(), aPedido);
		System.out.println("DAO: Insertado pedido ID " + aPedido.getID());
	}

	public Pedido buscarPedido(int aId) {
		return bdd.get(aId);
	}

	public void actualizar(Pedido aPedido) {
		if (bdd.containsKey(aPedido.getID())) {
			bdd.put(aPedido.getID(), aPedido);
			System.out.println("DAO: Actualizado pedido ID " + aPedido.getID());
		}
	}

	// Método necesario para refrescar la lista
	public List<Pedido> obtenerTodos() {
		return new ArrayList<>(bdd.values());
	}
}