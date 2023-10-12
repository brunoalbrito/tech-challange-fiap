package mocks;

import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public class ItemMock {
    public static Item createLanche() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        return Item.criaLanche("Lanche", "Lanche Descricao", List.of(ingrediente), BigDecimal.TEN);
    }
}
