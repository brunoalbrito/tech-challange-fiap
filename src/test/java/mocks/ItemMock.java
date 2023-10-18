package mocks;

import br.com.fiap.techchallenge.domain.Bebida;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Lanche;
import br.com.fiap.techchallenge.domain.Sobremesa;

import java.math.BigDecimal;
import java.util.List;

public class ItemMock {
    public static Lanche criaLanche() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        return Lanche.criaLanche("Lanche", "Lanche Descricao", List.of(ingrediente), BigDecimal.TEN);
    }

    public static Bebida criaBebida() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        return Bebida.criaBebida("Bebida", "Bebida Descricao", List.of(ingrediente), BigDecimal.TEN);
    }

    public static Sobremesa criaSobremesa() {
        Ingrediente ingrediente = IngredienteMock.criaIngrediente();
        return Sobremesa.criaSobremesa("Sobremesa", "Sobremesa Descricao", List.of(ingrediente), BigDecimal.TEN);
    }
}
