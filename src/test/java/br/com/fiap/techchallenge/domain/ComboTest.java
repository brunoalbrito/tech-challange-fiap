package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComboTest {

    @Test
    public void deveCriarUmComboCompleto() {
        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .preco(BigDecimal.TEN)
                .descricao("Bebida")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.BEBIDA)
                .build();

        Produto sobremesa = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Sorvete")
                .preco(BigDecimal.TEN)
                .descricao("Sobremesa")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, sobremesa));

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getProdutos().size()).isEqualTo(3);

    }

    @Test
    public void naoDeveCriarComboComIdNulo() {
        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .preco(BigDecimal.TEN)
                .descricao("Bebida")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.BEBIDA)
                .build();

        Produto sobremesa = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Sorvete")
                .preco(BigDecimal.TEN)
                .descricao("Sobremesa")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                Combo.criaCombo(null, List.of(lanche, bebida, sobremesa))
        );
    }


    @Test
    public void naoDeveCriarComboComProdutosNulos() {
        assertThrows(IllegalArgumentException.class, () ->
                Combo.criaCombo(UUID.randomUUID(), null)
        );
    }

    @Test
    public void naoDeveCriarComboComProdutosVazios() {
        assertThrows(IllegalArgumentException.class, () ->
                Combo.criaCombo(UUID.randomUUID(), List.of())
        );
    }

    @Test
    public void naoDeveCriarComboComProdutosIncompletos() {
        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .preco(BigDecimal.TEN)
                .descricao("Bebida")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.BEBIDA)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida))
        );
    }

    @Test
    public void deveCalcularValorTotalDoCombo() {
        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Lanche")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .preco(BigDecimal.TEN)
                .descricao("Bebida")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.BEBIDA)
                .build();

        Produto sobremesa = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Sorvete")
                .preco(BigDecimal.TEN)
                .descricao("Sobremesa")
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, sobremesa));

        assertThat(combo.valorTotal()).isEqualTo(BigDecimal.valueOf(30));
    }
}