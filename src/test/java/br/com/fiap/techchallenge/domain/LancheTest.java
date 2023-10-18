package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.application.request.enums.TipoItem;
import mocks.IngredienteMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LancheTest {

    @Test
    public void deveCriarUmLanche() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        Lanche lanche = Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.TEN);

        assertThat(lanche.getId()).isNotNull();
        assertThat(lanche.getNome()).isEqualTo("X-Bacon");
        assertThat(lanche.getDescricao()).isEqualTo("Pão, hambúrguer, queijo, bacon e alface");
        assertThat(lanche.getIngredientes()).isEqualTo(ingredientes);
        assertThat(lanche.getValor()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void naoDeveCriarLancheComNomeNulo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche(null, "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComNomeVazio() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComDescricaoNula() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", null, ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComDescricaoVazia() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComIngredientesNulos() {
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", null, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComIngredientesVazios() {
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", List.of(), BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarLancheComValorNulo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, null)
        );
    }

    @Test
    public void naoDeveCriarLancheComValorNegativo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.valueOf(-10))
        );
    }

    @Test
    public void naoDeveCriarLancheComValorZero() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.ZERO)
        );
    }

    @Test
    public void deveRetornarTipoLanche() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        Lanche lanche = Lanche.criaLanche("X-Bacon", "Pão, hambúrguer, queijo, bacon e alface", ingredientes, BigDecimal.TEN);
        assertThat(lanche.getTipo().toString()).isEqualTo(TipoItem.LANCHE.toString());
    }
}
