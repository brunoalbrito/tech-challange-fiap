package br.com.fiap.techchallenge.application.controllers.response;

import br.com.fiap.techchallenge.application.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.application.controllers.request.ProdutoRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoRequest {
    private List<ProdutoRequest> produtos;

    private ClienteRequest  cliente;
}
