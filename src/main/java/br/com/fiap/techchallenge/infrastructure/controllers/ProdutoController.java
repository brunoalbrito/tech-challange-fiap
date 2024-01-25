package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.services.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cria(@RequestBody final ProdutoRequest produtoRequest) {
        Produto produto = produtoService.criaProduto(produtoRequest);

        ProdutoResponse produtoResponse = ProdutoResponse.builder()
                .id(produto.getId().toString())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .tipo(produto.getTipo().getValue())
                .ingredientes(produto.getIngredientes().stream().map(
                        ingrediente -> IngredienteResponse.builder()
                                .id(ingrediente.getId().toString())
                                .descricao(ingrediente.getDescricao())
                                .build()
                ).collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }
}
