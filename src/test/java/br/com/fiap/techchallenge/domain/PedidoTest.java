package br.com.fiap.techchallenge.domain;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PedidoTest {
    @Test
    public void deveCriarUmPedidoComApenasUmItem() throws Exception {
        Ingrediente ingrediente = Ingrediente.criaIngrediente("Hamburguer");
        Item item = Lanche.criaLanche(1L, "BigMac", "O melhor do mundo", List.of(ingrediente), BigDecimal.TEN);
        ItemPedido itemPedido = ItemPedido.criaItemPedido(item, 2);

        Pedido pedido = Pedido.criaPedido(List.of(itemPedido));

        assertNotNull(pedido);
        assertEquals(1, pedido.getItensPedido().size());
        assertEquals(BigDecimal.valueOf(20), pedido.valorTotalPedido());
    }

}
