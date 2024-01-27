package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.produto.CriaProdutoInteractor;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final CriaProdutoInteractor criaProduto;

    private final ProdutoRequestMapper produtoRequestMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cria(@RequestBody final ProdutoRequest produtoRequest) {

        final Produto produto = criaProduto.execute(produtoRequest);

        ProdutoResponse produtoResponse = produtoRequestMapper.toResponse(produto);

        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }
}
