package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoDeItem;
import mocks.ItemMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {

    @Test
    public void deveCriarUmItemLanche() {
        Item item = ItemMock.createLanche();

        assertNotNull(item.getNome());
        assertNotNull(item.getDescricao());
        assertThat(item.getIngredientes().size()).isEqualTo(1);
        assertThat(item.getValor()).isEqualTo(BigDecimal.TEN);
        assertThat(item.getTipoDeItem()).isEqualTo(TipoDeItem.LANCHE);
    }

    @Test
    public void testInvalidItemCreation() {
        // Invalid item creation, missing name
        assertThrows(IllegalArgumentException.class, () ->
                Item.criaLanche("", "ItemDescription", Collections.emptyList(), new BigDecimal("10.00"))
        );

        // Invalid item creation, negative ID
        assertThrows(IllegalArgumentException.class, () ->
                Item.criaLanche("ItemName", "ItemDescription", Collections.emptyList(), new BigDecimal("10.00"))
        );
    }
}