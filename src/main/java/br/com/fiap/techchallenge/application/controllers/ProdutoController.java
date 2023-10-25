package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.application.controllers.response.ProdutoResponse;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.services.ProdutoService;
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

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criaPedido(@RequestBody final ProdutoRequest produtoRequest) {
        Produto produto = produtoService.criaProduto(produtoRequest);

        ProdutoResponse produtoResponse = ProdutoResponse.builder()
                .id(produto.getId().toString())
                .build();
        return new ResponseEntity<ProdutoResponse>(produtoResponse, HttpStatus.CREATED);
    }
}
