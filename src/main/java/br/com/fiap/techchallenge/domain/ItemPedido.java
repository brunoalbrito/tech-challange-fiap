package br.com.fiap.techchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ItemPedido {

    private UUID id;
    private Item item;
    private Integer quantidade;

    public static ItemPedido criaItemPedido(Item item, Integer quantidade) {
        return new ItemPedido(UUID.randomUUID(), item, quantidade);
    }

    public BigDecimal getValorTotal() {
        return this.item
                .getValor()
                .multiply(BigDecimal.valueOf(quantidade));
    }
}
