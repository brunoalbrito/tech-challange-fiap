package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoDeItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

public class ItemPedidoTest {

    @Test
    public void testItemPedidoCreation() {

        List<Ingrediente> ingredientes = Collections.singletonList(Ingrediente.criaIngrediente("Sobremesa"));
        Item item = new Item(1L, "ItemName", "ItemDescription", ingredientes, new BigDecimal("10.00"), TipoDeItem.ACOMPANHAMENTO);
        Integer quantidade = 3;

        ItemPedido itemPedido = ItemPedido.criaItemPedido(item, quantidade);

        assertThat(itemPedido.getId()).isNotNull();
        assertThat(itemPedido.getItem()).isEqualTo(item);
        assertThat(itemPedido.getQuantidade()).isEqualTo(quantidade);
    }

    @Test
    public void testGetValorTotal() {
        List<Ingrediente> ingredientes = Collections.singletonList(Ingrediente.criaIngrediente("Sobremesa"));
        Item item = new Item(1L, "ItemName", "ItemDescription", ingredientes, new BigDecimal("10.00"), TipoDeItem.ACOMPANHAMENTO);
        Integer quantidade = 3;
        ItemPedido itemPedido = ItemPedido.criaItemPedido(item, quantidade);

        BigDecimal valorTotal = item.getValor().multiply(BigDecimal.valueOf(quantidade));

        assertThat(itemPedido.getValorTotal()).isEqualTo(valorTotal);
    }
}