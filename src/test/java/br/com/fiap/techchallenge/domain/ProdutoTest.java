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

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();


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

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .preco(BigDecimal.TEN)
                .descricao("Bebida")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.BEBIDA)
                .build();


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

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Doce")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        assertNotNull(produto);
        assertNotNull(produto.getId());
        assertNotNull(produto.getNome());
        assertNotNull(produto.getPreco());
        assertNotNull(produto.getDescricao());
        assertNotNull(produto.getIngredientes());
        assertEquals(produto.getTipo().getValue(), Tipo.ACOMPANHAMENTO.getValue());
    }

    @Test
    public void naoDeveCriarProdutoQuandoNomeVazio() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(List.of(ingrediente))
                        .tipo(Tipo.LANCHE)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoPrecoMenorQueZero() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.valueOf(-1))
                        .descricao("Lanche")
                        .ingredientes(List.of(ingrediente))
                        .tipo(Tipo.LANCHE)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoDescricaoVazia() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("")
                        .ingredientes(List.of(ingrediente))
                        .tipo(Tipo.LANCHE)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoIngredientesVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(List.of())
                        .tipo(Tipo.LANCHE)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoIngredientesNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(null)
                        .tipo(Tipo.LANCHE)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoNulo() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(List.of(ingrediente))
                        .tipo(null)
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoVazio() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(List.of(ingrediente))
                        .tipo(Tipo.valueOf(""))
                        .build()
        );
    }

    @Test
    public void naoDeveCriarProdutoQuandoTipoInvalido() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Pão");
        assertThrows(IllegalArgumentException.class, () ->
                Produto.builder()
                        .id(UUID.randomUUID())
                        .nome("X-Bacon")
                        .preco(BigDecimal.TEN)
                        .descricao("Lanche")
                        .ingredientes(List.of(ingrediente))
                        .tipo(Tipo.valueOf("INVALIDO"))
                        .build()
        );
    }
}
