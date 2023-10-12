package mocks;

import br.com.fiap.techchallenge.domain.Ingrediente;

public class IngredienteMock {
    public static Ingrediente criaIngrediente(){
        return Ingrediente.criaIngrediente("Ingrediente descricao");
    }
}
