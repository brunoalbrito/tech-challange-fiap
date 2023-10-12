package br.com.fiap.techchallenge.domain;

import mocks.ItemMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemPedidoTest {

    @Test
    public void deveCriarUmItemPedido() {

        Item item = ItemMock.createLanche();
        Integer quantidade = 1;

        ItemPedido itemPedido = ItemPedido.criaItemPedido(item, quantidade);

        assertThat(itemPedido.getId()).isNotNull();
        assertThat(itemPedido.getItem()).isEqualTo(item);
        assertThat(itemPedido.getQuantidade()).isEqualTo(quantidade);
    }

    @Test
    public void deveCalcularValorTotalDoItemPedido() {
        Item item = ItemMock.createLanche();
        Integer quantidade = 3;

        ItemPedido itemPedido = ItemPedido.criaItemPedido(item, quantidade);

        BigDecimal valorTotal = item.getValor().multiply(BigDecimal.valueOf(quantidade));

        assertThat(itemPedido.getValorTotal()).isEqualTo(valorTotal);
    }
}