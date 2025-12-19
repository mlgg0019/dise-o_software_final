package Test;

import src.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private PedidoDAO pedidoDAOMock;

    @Mock
    private ProveedorFacade proveedorFacadeMock;

    @Mock
    private EstrategiaValidacion_ estrategiaMock;

    @Mock
    private VentanaGestionarPedidos ventanaMock;

    @InjectMocks
    private ClientController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inyección manual del controlador con todos los mocks
        controller = new ClientController(pedidoDAOMock, proveedorFacadeMock, estrategiaMock, ventanaMock);
    }

    // ==========================================
    // CASO 1: ALTA DE PEDIDO (Solicitar)
    // ==========================================

    @Test
    void testSolicitarPedido_ConDatosReales_Exito() {
        // ARRANGE: Preparamos un DTO con datos reales (Producto, Cantidad, Proveedor...)
        PedidoDTO datosReales = new PedidoDTO();

        // Creamos productos usando el constructor de DatosProduto
        DatosProduto p1 = new DatosProduto(101, "Monitor 24 pulgadas", 10);
        DatosProduto p2 = new DatosProduto(102, "Teclado Mecánico", 5);



        // El validador dice que ESTOS datos concretos son correctos
        when(estrategiaMock.ValidacionDatosPedido(datosReales)).thenReturn(true);

        // ACT
        controller.solicitarPedido(datosReales);

        // ASSERT
        // Verificamos que se guarda un pedido generado a partir de esos datos
        verify(pedidoDAOMock).insertar(any(Pedido.class));
        verify(proveedorFacadeMock).enviarSolicitud(any(Pedido.class));
        verify(ventanaMock).actualizaListaPedidos(anyList());
    }

    @Test
    void testSolicitarPedido_DatosInvalidos_Fallo() {
        // ARRANGE: Preparamos datos inválidos (ej. sin productos o cantidades negativas)
        PedidoDTO datosInvalidos = new PedidoDTO();
        // No le añadimos nada, o le ponemos datos erróneos

        // El validador rechaza explícitamente estos datos
        when(estrategiaMock.ValidacionDatosPedido(datosInvalidos)).thenReturn(false);

        // ACT
        controller.solicitarPedido(datosInvalidos);

        // ASSERT: Aseguramos que NO se procesa nada
        verify(pedidoDAOMock, never()).insertar(any(Pedido.class));
        verify(proveedorFacadeMock, never()).enviarSolicitud(any(Pedido.class));
    }

    // ==========================================
    // CASO 2: BAJA DE PEDIDO (Cancelar)
    // ==========================================

    @Test
    void testProcesarBajaPedido_Exito() {
        int idPedido = 500;
        Pedido pedidoEnBBDD = new Pedido();
        pedidoEnBBDD.setID(idPedido);
        pedidoEnBBDD.setEstado("CONFIRMADO");

        when(pedidoDAOMock.buscarPedido(idPedido)).thenReturn(pedidoEnBBDD);

        controller.procesarBajaPedido(idPedido);

        verify(proveedorFacadeMock).enviarBajaPedido(pedidoEnBBDD); // Avisa de la baja
        verify(pedidoDAOMock).actualizar(pedidoEnBBDD); // Actualiza la BBDD
         assertEquals("CANCELADO", pedidoEnBBDD.getEstado());
    }

    @Test
    void testProcesarBajaPedido_NoExiste() {
        // ARRANGE: Un ID que no está en la base de datos
        int idDesconocido = 9999;
        when(pedidoDAOMock.buscarPedido(idDesconocido)).thenReturn(null);

        // ACT
        controller.procesarBajaPedido(idDesconocido);

        // ASSERT: No debe explotar ni llamar a nada
        verify(proveedorFacadeMock, never()).enviarBajaPedido(any());
        verify(pedidoDAOMock, never()).actualizar(any());
    }

    // ==========================================
    // CASO 3: MODIFICAR PEDIDO
    // ==========================================

    @Test
    void testProcesarModificacionPedido_DatosReales_Exito() {
        // ARRANGE
        int id = 1;

        // 1. El pedido original que está en BBDD
        Pedido pedidoOriginal = new Pedido();
        pedidoOriginal.setID(id);
        pedidoOriginal.setEstado("PENDIENTE");

        // 2. Los NUEVOS datos que introduce el usuario (ej. cambia cantidad)
        PedidoDTO nuevosDatos = new PedidoDTO();
        // nuevosDatos.getProductos().add(new DatosProduto(101, "Monitor", 20)); // Cambia de 10 a 20

        // Simulamos comportamiento
        when(pedidoDAOMock.buscarPedido(id)).thenReturn(pedidoOriginal);
        when(estrategiaMock.ValidacionDatosPedido(nuevosDatos)).thenReturn(true);

        // ACT
        controller.procesarModificacionPedido(id, nuevosDatos);

        // ASSERT
        verify(proveedorFacadeMock).enviarSolicitud(pedidoOriginal); // Re-envía la solicitud
        verify(pedidoDAOMock).actualizar(pedidoOriginal); // Guarda cambios
        verify(ventanaMock).actualizaListaPedidos(anyList());
    }

    @Test
    void testProcesarModificacionPedido_FalloValidacion() {
        // ARRANGE
        int id = 1;
        Pedido pedidoOriginal = new Pedido();
        PedidoDTO datosCorruptos = new PedidoDTO(); // Datos vacíos o malos

        when(pedidoDAOMock.buscarPedido(id)).thenReturn(pedidoOriginal);
        // La validación falla
        when(estrategiaMock.ValidacionDatosPedido(datosCorruptos)).thenReturn(false);

        // ACT
        controller.procesarModificacionPedido(id, datosCorruptos);

        // ASSERT
        verify(pedidoDAOMock, never()).actualizar(any());
        verify(proveedorFacadeMock, never()).enviarSolicitud(any());
    }

    @Test
    void testProcesarModificacionPedido_NoExiste() {
        // ARRANGE
        int idFantasma = -5;
        PedidoDTO datosValidos = new PedidoDTO();

        // El pedido no se encuentra
        when(pedidoDAOMock.buscarPedido(idFantasma)).thenReturn(null);

        // ACT
        controller.procesarModificacionPedido(idFantasma, datosValidos);

        // ASSERT
        verify(pedidoDAOMock, never()).actualizar(any());
    }
}