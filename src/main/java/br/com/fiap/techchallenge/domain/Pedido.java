package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.entity.PedidoEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pedido {

    private UUID id;

    private Cliente cliente;

    private List<Produto> produtos;
    private StatusPedido statusPedido;

    private Pagamento pagamento;

    public static Pedido criaPedido(UUID id, Cliente cliente, List<Produto> produtos, Pagamento pagamento) {
        validateProdutos(produtos);
        validatePagamento(pagamento);
        return new Pedido(id, cliente, produtos, StatusPedido.CRIADO, pagamento);
    }

    private static void validateProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Produtos não pode ser nulo ou vazio.");
        }
    }

    private static void validatePagamento(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
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

    public PedidoEntity toEntity() {
        return PedidoEntity.criaPedidoEntity(this);
    }
}
