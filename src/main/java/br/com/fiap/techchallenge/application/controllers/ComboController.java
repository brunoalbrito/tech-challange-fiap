package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.application.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.application.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.application.controllers.response.ProdutoResponse;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.services.ComboService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/combos")
public class ComboController {

    private ComboService comboService;

    @PostMapping
    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
        Combo combo = comboService.criaCombo(comboRequest.getProdutos());
        var comboResponse = ComboResponse.builder();
        comboResponse.id(combo.getId().toString());
        comboResponse.precoTotal(combo.valorTotal());
        comboResponse.produtos(combo.getProdutos().stream().map(
                produto -> ProdutoResponse.builder()
                        .id(produto.getId().toString())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .tipo(produto.getTipo().toString())
                        .ingredientes(produto.getIngredientes().stream().map(
                                ingrediente -> IngredienteResponse.builder()
                                        .id(ingrediente.getId().toString())
                                        .descricao(ingrediente.getDescricao())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()));

        return new ResponseEntity<>(comboResponse.build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComboResponse>> listaCombos() {
        List<Combo> combos = comboService.listaCombos();

        var comboResponses = combos.stream().map(combo -> ComboResponse.builder()
                .id(combo.getId().toString())
                .precoTotal(combo.valorTotal())
                .produtos(combo.getProdutos().stream().map(
                        produto -> ProdutoResponse.builder()
                                .id(produto.getId().toString())
                                .nome(produto.getNome())
                                .descricao(produto.getDescricao())
                                .preco(produto.getPreco())
                                .tipo(produto.getTipo().toString())
                                .ingredientes(produto.getIngredientes().stream().map(
                                        ingrediente -> IngredienteResponse.builder()
                                                .id(ingrediente.getId().toString())
                                                .descricao(ingrediente.getDescricao())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList())).build()).collect(Collectors.toList());


        return new ResponseEntity<>(comboResponses, HttpStatus.CREATED);
    }
}

