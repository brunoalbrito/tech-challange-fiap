package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
        return new Pedido(id, cliente, produtos, StatusPedido.AGUARDANDO_PAGAMENTO, pagamento);
    }

    public static Pedido criaPedido(UUID id, Cliente cliente, List<Produto> produtos,
        StatusPedido statusPedido, Pagamento pagamento) {
        validateProdutos(produtos);
        validatePagamento(pagamento);
        return new Pedido(id, cliente, produtos, statusPedido, pagamento);
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

    public void pagamentoRecebido() {
        // TODO validar se o status anterior é o correto para poder mudar para o status mencionado abaixo
        this.statusPedido = StatusPedido.EM_PREPARACAO;
    }


    public void preparoFinalizado() {
        // TODO validar se o status anterior é o correto para poder mudar para o status mencionado abaixo
        this.statusPedido = StatusPedido.PREPARO_FINALIZADO;
    }

    public void entregue() {
        // TODO validar se o status anterior é o correto para poder mudar para o status mencionado abaixo
        this.statusPedido = StatusPedido.ENTREGUE;
    }

    public BigDecimal valorTotalPedido() {
        return this.produtos.stream()
            .map(Produto::getPreco)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
