package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaPedidoInteractorTest {

    private BuscaPedidoInteractor buscaPedidoInteractor;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    public void setUp() {
        pedidoGateway = mock(PedidoGateway.class);
        buscaPedidoInteractor = new BuscaPedidoInteractor(pedidoGateway);
    }

    @Test
    public void deveBuscarPedidoDadoUUIDValido() {
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

        Pedido expectedPedido = Pedido.criaPedido(pedidoId, cliente, List.of(produto), pagamento);

        when(pedidoGateway.buscaPorUUID(any(UUID.class))).thenReturn(expectedPedido);

        Pedido result = buscaPedidoInteractor.execute(pedidoId);

        assertNotNull(result);
        assertEquals(expectedPedido, result);
        verify(pedidoGateway, times(1)).buscaPorUUID(pedidoId);
    }
}
