package br.com.fiap.techchallenge.application.controllers.request;

import br.com.fiap.techchallenge.application.controllers.request.enums.TipoRequest;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ProdutoRequest {

    private String id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private List<String> ingredientes;
    private TipoRequest tipo;
}
