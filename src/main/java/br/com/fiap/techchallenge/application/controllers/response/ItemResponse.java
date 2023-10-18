package br.com.fiap.techchallenge.application.controllers.response;

import br.com.fiap.techchallenge.application.controllers.response.enums.TipoItemResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemResponse {
    private String id;
    private String descricao;
    private BigDecimal valor;
    private List<IngredienteResponse> ingredientesResponse;
    private TipoItemResponse tipo;

}
