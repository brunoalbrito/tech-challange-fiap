package br.com.fiap.techchallenge.application.usecases.pedido;


import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConfirmaEntregaPedidoInteractorTest {

    private ConfirmaEntregaPedidoInteractor confirmaEntregaPedidoInteractor;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    public void setUp() {
        pedidoGateway = mock(PedidoGateway.class);
        confirmaEntregaPedidoInteractor = new ConfirmaEntregaPedidoInteractor(pedidoGateway);
    }

    @Test
    public void deveConfirmarEntregaPedidoDadoUUIDValido() {
        UUID pedidoId = UUID.randomUUID();
        Cliente cliente = Cliente.criaCliente("48336661115");
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qr-code");

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .descricao("Refrigerante de cola")
                .preco(BigDecimal.TEN)
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.LANCHE)
                .build();

        Pedido pedido = Pedido.criaPedido(pedidoId, cliente, List.of(produto), pagamento);

        when(pedidoGateway.buscaPorUUID(any(UUID.class))).thenReturn(pedido);
        when(pedidoGateway.salva(any(Pedido.class))).thenReturn(pedido);

        Pedido result = confirmaEntregaPedidoInteractor.execute(pedidoId);

        assertNotNull(result);
        assertEquals(pedido, result);
        assertEquals(result.getStatusPedido(), StatusPedido.ENTREGUE);
        verify(pedidoGateway, times(1)).buscaPorUUID(pedidoId);
        verify(pedidoGateway, times(1)).salva(pedido);
    }
}
