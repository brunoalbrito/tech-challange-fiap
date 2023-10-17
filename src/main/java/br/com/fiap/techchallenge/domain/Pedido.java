package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Pedido {

    private UUID id;

    private List<Combo> itemsPedido;
    private StatusPedido statusPedido;

    public static Pedido criaPedido(Combo combo) {
        UUID id = UUID.randomUUID();
        ArrayList<Combo> itensPedido = new ArrayList<>();
        itensPedido.add(combo);
        return new Pedido(id, itensPedido, StatusPedido.CRIADO);
    }

    public void adicionaItem(Combo combo) {
        this.itemsPedido.add(combo);
    }

    public void removeItem(UUID id) {
        this.itemsPedido = itemsPedido.stream()
                .filter(combo -> !combo.getId().equals(id))
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
        return this.itemsPedido.stream()
                .map(Combo::valorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
