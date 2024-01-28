package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Pedido {

    private final UUID id;

    private final Cliente cliente;

    private final List<Produto> produtos;

    private StatusPedido statusPedido;

    private Pagamento pagamento;

    private Pedido(UUID id, Cliente cliente, List<Produto> produtos, StatusPedido statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.statusPedido = statusPedido;
    }

    public static Pedido criaPedido(UUID id, Cliente cliente, List<Produto> produtos) {
        validateProdutos(produtos);
        return new Pedido(id, cliente, produtos, StatusPedido.AGUARDANDO_PAGAMENTO);
    }

    public static Pedido criaPedido(UUID id, Cliente cliente, List<Produto> produtos, StatusPedido status, Pagamento pagamento) {
        return new Pedido(id, cliente, produtos, status, pagamento);
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

    public void registaPagamento(Pagamento pagamento) {
        validatePagamento(pagamento);
        this.pagamento = pagamento;
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
