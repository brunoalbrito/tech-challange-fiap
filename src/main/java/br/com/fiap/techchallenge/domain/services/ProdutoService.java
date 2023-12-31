package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final IngredienteService ingredienteService;

    public Produto criaProduto(ProdutoRequest produtoRequest) {


        List<Ingrediente> ingredientes = ingredienteService.buscaIngredientesPorId(produtoRequest.getIngredientes());

        if (ingredientes.isEmpty()) {
            throw new IllegalArgumentException("Ingredientes não pode ser nulo ou vazio.");
        }

        Produto produto = Produto.criaProduto(UUID.randomUUID(), produtoRequest.getNome(), produtoRequest.getPreco(), produtoRequest.getDescricao(), ingredientes, Tipo.fromValue(produtoRequest.getTipo().getValue()));
        produtoRepository.save(ProdutoEntity.toEntity(produto));
        return produto;
    }
}
