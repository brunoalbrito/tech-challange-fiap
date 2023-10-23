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

    private Cliente cliente;

    private List<Produto> produtos;
    private StatusPedido statusPedido;

    public static Pedido criaPedido(UUID id, Cliente cliente, List<Produto> produtos) {
        validateProdutos(produtos);
        return new Pedido(id, cliente, produtos, StatusPedido.CRIADO);
    }

    private static void validateProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Produtos não pode ser nulo ou vazio.");
        }
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
        return this.produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
