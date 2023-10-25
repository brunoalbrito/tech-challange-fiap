package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProdutoTest {
    @Test
    public void deveCriarLanche() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.LANCHE);

        assertNotNull(produto);
        assertNotNull(produto.getId());
        assertNotNull(produto.getNome());
        assertNotNull(produto.getPreco());
        assertNotNull(produto.getDescricao());
        assertNotNull(produto.getIngredientes());
        assertEquals(produto.getTipo(), Tipo.LANCHE);
    }

    @Test
    public void deveCriarBebida() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Refrigerante");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Coca-Cola", BigDecimal.TEN, "Bebida", List.of(ingrediente), Tipo.BEBIDA);

        assertNotNull(produto);
        assertNotNull(produto.getId());
        assertNotNull(produto.getNome());
        assertNotNull(produto.getPreco());
        assertNotNull(produto.getDescricao());
        assertNotNull(produto.getIngredientes());
        assertEquals(produto.getTipo(), Tipo.BEBIDA);
    }

    @Test
    public void deveCriarSobremesa() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Chocolate");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Doce", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.SOBREMESA);

        assertNotNull(produto);
        assertNotNull(produto.getId());
        assertNotNull(produto.getNome());
        assertNotNull(produto.getPreco());
        assertNotNull(produto.getDescricao());
        assertNotNull(produto.getIngredientes());
        assertEquals(produto.getTipo(), Tipo.SOBREMESA);
    }

    @Test
    public void naoDeveCriarProdutoQuandoNomeVazio() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.LANCHE)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoPrecoMenorQueZero() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.valueOf(-1), "Lanche", List.of(ingrediente), Tipo.LANCHE)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoDescricaoVazia() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "", List.of(ingrediente), Tipo.LANCHE)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoIngredientesVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(), Tipo.LANCHE)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoIngredientesNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", null, Tipo.LANCHE)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoNulo() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), null)
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoVazio() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.valueOf(""))
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoInvalido() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(ingrediente), Tipo.valueOf("INVALIDO"))
        );
    }
}
