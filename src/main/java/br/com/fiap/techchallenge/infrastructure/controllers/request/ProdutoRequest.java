package br.com.fiap.techchallenge.infrastructure.controllers.request;

import br.com.fiap.techchallenge.infrastructure.controllers.request.enums.TipoRequest;
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
public class ProdutoRequest {

    private String id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private List<String> ingredientes;
    private TipoRequest tipo;
}
