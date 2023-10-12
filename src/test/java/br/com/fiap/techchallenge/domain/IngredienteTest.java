package br.com.fiap.techchallenge.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredienteTest {
    @Test
    public void testValidIngredienteCreation() {
        String descricao = "Ingredient Description";
        Ingrediente ingrediente = Ingrediente.criaIngrediente(descricao);

        assertEquals(ingrediente.getDescricao(), descricao);
    }

    @Test
    public void testInvalidIngredienteCreation() {
        assertThrows(IllegalArgumentException.class, () ->
                Ingrediente.criaIngrediente("")
        );
    }
}
