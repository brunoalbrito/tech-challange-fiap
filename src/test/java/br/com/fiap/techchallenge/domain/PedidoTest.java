package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import mocks.PedidoMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PedidoTest {

    @Test
    public void deveCriarPedido() {
        Pedido pedido = PedidoMock.criaPedido();

        assertNotNull(pedido.getId());
        assertNotNull(pedido.getItensPedido());
        assertThat(pedido.getStatusPedido()).isEqualTo(StatusPedido.CRIADO);
        assertNotNull(pedido.valorTotalPedido());
    }

    @Test
    public void deveCalcularValorTotal(){
        Pedido pedido = PedidoMock.criaPedido();

        assertThat(pedido.valorTotalPedido()).isEqualTo(BigDecimal.valueOf(40L));
    }
}