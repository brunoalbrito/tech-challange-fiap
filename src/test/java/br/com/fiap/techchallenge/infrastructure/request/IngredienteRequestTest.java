package br.com.fiap.techchallenge.infrastructure.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "criaIngredienteRequest")
public class IngredienteRequestTest {
    private String descricao;
}