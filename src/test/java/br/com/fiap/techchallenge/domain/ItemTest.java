package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoDeItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {

    @Test
    public void testValidItemCreation() {
        Long id = 1L;
        String nome = "ItemName";
        String descricao = "ItemDescription";
        List<Ingrediente> ingredientes = Collections.singletonList(Ingrediente.criaIngrediente("Sobremesa"));
        BigDecimal valor = new BigDecimal("10.00");
        TipoDeItem tipoDeItem = TipoDeItem.ACOMPANHAMENTO;

        Item item = new Item(id, nome, descricao, ingredientes, valor, tipoDeItem);

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getNome()).isEqualTo(nome);
        assertThat(item.getDescricao()).isEqualTo(descricao);
        assertThat(item.getIngredientes()).isEqualTo(ingredientes);
        assertThat(item.getValor()).isEqualTo(valor);
        assertThat(item.getTipoDeItem()).isEqualTo(tipoDeItem);
    }

    @Test
    public void testInvalidItemCreation() {
        // Invalid item creation, missing name
        assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "", "ItemDescription", Collections.emptyList(), new BigDecimal("10.00"), TipoDeItem.ACOMPANHAMENTO)
        );

        // Invalid item creation, negative ID
        assertThrows(IllegalArgumentException.class, () ->
                new Item(-1L, "ItemName", "ItemDescription", Collections.emptyList(), new BigDecimal("10.00"), TipoDeItem.ACOMPANHAMENTO)
        );
    }
}