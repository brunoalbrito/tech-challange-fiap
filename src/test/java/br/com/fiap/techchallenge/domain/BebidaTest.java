package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.application.request.enums.TipoItem;
import mocks.IngredienteMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BebidaTest {
    @Test
    public void deveCriarBebida() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        Bebida bebida = Bebida.criaBebida("Coca-Cola", "Refrigerante de Cola", ingredientes, BigDecimal.TEN);

        assertThat(bebida.getId()).isNotNull();
        assertThat(bebida.getNome()).isEqualTo("Coca-Cola");
        assertThat(bebida.getDescricao()).isEqualTo("Refrigerante de Cola");
        assertThat(bebida.getIngredientes()).isEqualTo(ingredientes);
        assertThat(bebida.getValor()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void naoDeveCriarBebidaComNomeNulo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida(null, "Refrigerante de Cola", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarBebidaComNomeVazio() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("", "Refrigerante de Cola", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarBebidaComDescricaoNula() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("Coca-Cola", null, ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarBebidaComDescricaoVazia() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("Coca-Cola", "", ingredientes, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarBebidaComIngredientesNulos() {
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("Coca-Cola", "Refrigerante de Cola", null, BigDecimal.TEN)
        );
    }

    @Test
    public void naoDeveCriarBebidaComValorNulo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("Coca-Cola", "Refrigerante de Cola", ingredientes, null)
        );
    }

    @Test
    public void naoDeveCriarBebidaComValorNegativo() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        assertThrows(IllegalArgumentException.class, () ->
                Bebida.criaBebida("Coca-Cola", "Refrigerante de Cola", ingredientes, BigDecimal.valueOf(-10))
        );
    }

    @Test
    public void deveRetornarTipoBebida() {
        List<Ingrediente> ingredientes = List.of(IngredienteMock.criaIngrediente());
        Bebida bebida = Bebida.criaBebida("Coca-Cola", "Refrigerante de Cola", ingredientes, BigDecimal.TEN);
        assertThat(bebida.getTipo().toString()).isEqualTo(TipoItem.BEBIDA.toString());
    }
}
