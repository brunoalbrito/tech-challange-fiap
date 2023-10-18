package br.com.fiap.techchallenge.application.controllers.request;

import br.com.fiap.techchallenge.application.controllers.request.enums.TipoItemRequest;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ItemRequest {
    private String nome;
    private String descricao;
    private List<IngredienteRequest> ingredientes;
    private BigDecimal valor;
    private TipoItemRequest tipo;
}
