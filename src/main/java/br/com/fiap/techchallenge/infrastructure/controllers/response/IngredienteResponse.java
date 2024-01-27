package br.com.fiap.techchallenge.infrastructure.controllers.response;

import br.com.fiap.techchallenge.domain.Ingrediente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredienteResponse {

    private UUID id;

    private String descricao;

    public static IngredienteResponse fromDomain(Ingrediente ingrediente) {
        return new IngredienteResponse(ingrediente.getId(), ingrediente.getDescricao());
    }

}
