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
        Cliente cliente = Cliente.criaCliente(UUID.randomUUID(), "26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto));

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getCliente());
        assertNotNull(pedido.getProdutos());
        assertEquals(pedido.getStatusPedido(), StatusPedido.CRIADO);
    }

    @Test
    public void deveCalcularValorTotalPedido() {
        Cliente cliente = Cliente.criaCliente(UUID.randomUUID(), "26311855879");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto, produto, produto));

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getCliente());
        assertNotNull(pedido.getProdutos());
        assertEquals(pedido.valorTotalPedido(), BigDecimal.valueOf(30));
    }

    @Test
    public void naoDeveCriarPedidoQuandoProdutosVazio() {
        Cliente cliente = Cliente.criaCliente(UUID.randomUUID(), "26311855879");
        assertThrows(IllegalArgumentException.class, () ->
                Pedido.criaPedido(UUID.randomUUID(), cliente, List.of())
        );
    }

    @Test
    public void naoDeveCriarPedidoQuandoProdutosNulo() {
        Cliente cliente = Cliente.criaCliente(UUID.randomUUID(), "26311855879");
        assertThrows(IllegalArgumentException.class, () ->
                Pedido.criaPedido(UUID.randomUUID(), cliente, null)
        );
    }
}
