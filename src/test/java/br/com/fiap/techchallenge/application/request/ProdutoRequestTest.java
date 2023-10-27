package br.com.fiap.techchallenge.application.request;

import br.com.fiap.techchallenge.application.controllers.request.enums.TipoRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "criaComboRequestTest")
public class ProdutoRequestTest {
    private String id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private List<String> ingredientes;
    private TipoRequest tipo;
}
