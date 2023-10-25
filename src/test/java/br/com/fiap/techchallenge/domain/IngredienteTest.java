package br.com.fiap.techchallenge.domain;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredienteTest {
    @Test
    public void deveCriarIngredienteValido() {
        UUID id = UUID.randomUUID();
        Ingrediente ingrediente = Ingrediente.criaIngrediente(id, "Queijo");
        String descricao = ingrediente.getDescricao();

        assertEquals(ingrediente.getId(), id);
        assertEquals(ingrediente.getDescricao(), descricao);
    }

    @Test
    public void naoDeveCriarIngredienteQuandoDescricaoVazia() {
        UUID id = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () ->
                Ingrediente.criaIngrediente(id, "")
        );
    }
}