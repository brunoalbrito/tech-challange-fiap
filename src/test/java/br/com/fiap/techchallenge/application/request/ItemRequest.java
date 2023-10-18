package br.com.fiap.techchallenge.application.request;

import br.com.fiap.techchallenge.application.request.enums.TipoItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequest {
    private String nome;

    private String descricao;

    private BigDecimal valor;

    private TipoItem tipo;

    private List<IngredienteRequest> ingredientes;

    public static ItemRequest criaItemRequest(String nome, String descricao, BigDecimal valor, TipoItem tipo, List<IngredienteRequest> ingredientes) {
        return new ItemRequest(nome, descricao, valor, tipo, ingredientes);
    }
}
