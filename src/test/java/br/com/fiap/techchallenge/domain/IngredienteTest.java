package br.com.fiap.techchallenge.domain;

import mocks.IngredienteMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredienteTest {
    @Test
    public void deveCriarIngredienteValido() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        String descricao = ingrediente.getDescricao();

        assertEquals(ingrediente.getDescricao(), descricao);
    }

    @Test
    public void naoDeveCriarIngredienteQuandoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                Ingrediente.criaIngrediente("")
        );
    }
}
