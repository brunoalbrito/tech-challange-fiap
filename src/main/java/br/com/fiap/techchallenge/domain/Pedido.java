package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Pedido {

    private UUID id;

    @Getter
    private List<ItemPedido> itensPedido;

    private StatusPedido statusPedido;

    public static Pedido criaPedido(List<ItemPedido> itensPedido) throws Exception {
        if (itensPedido.isEmpty()) {
            throw new Exception("O pedido precisa conter pelo menos um item");
        }
        UUID id = UUID.randomUUID();
        return new Pedido(id, itensPedido, StatusPedido.CRIADO);
    }

    public void adicionaItem(ItemPedido itemPedido) {
        itensPedido.add(itemPedido);
    }

    public void removeItem(UUID id) {
        this.itensPedido = itensPedido.stream()
                .filter(itemPedido -> !itemPedido.getId().equals(id))
                .toList();
    }

    public void preparaPedido() {
        this.statusPedido = StatusPedido.EM_PREPARACAO;
    }

    public void concluiPedido() {
        this.statusPedido = StatusPedido.CONCLUIDO;
    }

    public void entregaPedido() {
        this.statusPedido = StatusPedido.ENTREGUE;
    }

    public BigDecimal valorTotalPedido() {
        return this.itensPedido.stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
