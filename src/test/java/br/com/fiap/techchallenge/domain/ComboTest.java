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
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "Coca-Cola", BigDecimal.TEN, "Bebida", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")), Tipo.BEBIDA);
        Produto sobremesa = Produto.criaProduto(UUID.randomUUID(), "Sorvete", BigDecimal.TEN, "Sobremesa", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")), Tipo.ACOMPANHAMENTO);

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, sobremesa));

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getProdutos().size()).isEqualTo(3);

    }

    @Test
    public void naoDeveCriarComboComIdNulo() {
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "Coca-Cola", BigDecimal.TEN, "Bebida", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")), Tipo.BEBIDA);
        Produto sobremesa = Produto.criaProduto(UUID.randomUUID(), "Sorvete", BigDecimal.TEN, "Sobremesa", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")), Tipo.ACOMPANHAMENTO);

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
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "Coca-Cola", BigDecimal.TEN, "Bebida", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")), Tipo.BEBIDA);

        assertThrows(IllegalArgumentException.class, () ->
                Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida))
        );
    }

    @Test
    public void deveCalcularValorTotalDoCombo() {
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "X-Bacon", BigDecimal.TEN, "Lanche", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Hamburguer")), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "Coca-Cola", BigDecimal.TEN, "Bebida", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")), Tipo.BEBIDA);
        Produto sobremesa = Produto.criaProduto(UUID.randomUUID(), "Sorvete", BigDecimal.TEN, "Sobremesa", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Creme de sorvete")), Tipo.ACOMPANHAMENTO);

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, sobremesa));

        assertThat(combo.valorTotal()).isEqualTo(BigDecimal.valueOf(30));
    }
}