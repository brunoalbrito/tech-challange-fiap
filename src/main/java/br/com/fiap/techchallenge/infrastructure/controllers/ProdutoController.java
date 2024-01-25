package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.ingrediente.BuscaIngredientesPorIdInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.CriaProdutoInteractor;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.mappers.ProdutoRequestMapper;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final CriaProdutoInteractor criaProduto;

    private final BuscaIngredientesPorIdInteractor buscaIngredientesPorId;

    private final ProdutoRequestMapper produtoRequestMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cria(@RequestBody final ProdutoRequest produtoRequest) {

        List<Ingrediente> ingredientes = buscaIngredientesPorId.execute(produtoRequest.getIngredientes());

        final Produto produto = produtoRequestMapper.toProduto(produtoRequest, ingredientes);

        final Produto produtoSalvo = criaProduto.execute(produto);

        ProdutoResponse produtoResponse = produtoRequestMapper.toResponse(produtoSalvo);

        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }
}
