package mocks;

import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.Lanche;

import java.math.BigDecimal;
import java.util.List;

public class ItemMock {
    public static Lanche createLanche() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        return Lanche.criaLanche("Lanche", "Lanche Descricao", List.of(ingrediente), BigDecimal.TEN);
    }
}
