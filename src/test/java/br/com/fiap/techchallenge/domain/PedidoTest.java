package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoTest {

    @Test
    public void deveCriarPedidoValido() {
        Cliente cliente = Cliente.criaCliente("26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto));

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getCliente());
        assertNotNull(pedido.getProdutos());
        assertEquals(pedido.getStatusPedido(), StatusPedido.AGUARDANDO_PAGAMENTO);
    }

    @Test
    public void deveCalcularValorTotalPedido() {
        Cliente cliente = Cliente.criaCliente("26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto, produto, produto));

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getCliente());
        assertNotNull(pedido.getProdutos());
        assertEquals(pedido.valorTotalPedido(), BigDecimal.valueOf(30));
    }

    @Test
    public void naoDeveCriarPedidoQuandoProdutosVazio() {
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Cliente cliente = Cliente.criaCliente("26311855879");
        assertThrows(IllegalArgumentException.class, () ->
                Pedido.criaPedido(UUID.randomUUID(), cliente, List.of())
        );
    }

    @Test
    public void naoDeveCriarPedidoQuandoProdutosNulo() {
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Cliente cliente = Cliente.criaCliente("26311855879");
        assertThrows(IllegalArgumentException.class, () ->
                Pedido.criaPedido(UUID.randomUUID(), cliente, null)
        );
    }

    @Test
    public void deveAlterarStatusPedidoParaEmPreparacao() {
        Cliente cliente = Cliente.criaCliente("26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto));
        pedido.pagamentoRecebido();
        assertEquals(pedido.getStatusPedido(), StatusPedido.EM_PREPARACAO);
    }

    @Test
    public void deveAlterarStatusPedidoParaConcluido() {
        Cliente cliente = Cliente.criaCliente("26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto));
        pedido.preparoFinalizado();
        assertEquals(pedido.getStatusPedido(), StatusPedido.PREPARO_FINALIZADO);
    }

    @Test
    public void deveAlterarStatusPedidoParaEntregue() {
        Cliente cliente = Cliente.criaCliente("26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto));
        pedido.entregue();
        assertEquals(pedido.getStatusPedido(), StatusPedido.ENTREGUE);
    }
}
