package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.repository.IngredienteRepository;
import br.com.fiap.techchallenge.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final IngredienteRepository ingredienteRepository;

    public Produto criaProduto(ProdutoRequest produtoRequest) {
        List<IngredienteEntity> ingredientesEntity = produtoRequest.getIngredientes().stream().
                map(ingredienteId -> ingredienteRepository.findById(UUID.fromString(ingredienteId))
                        .orElseThrow(() -> new IllegalArgumentException("Ingrediente não encontrado."))).
                collect(Collectors.toList());

        List<Ingrediente> ingredientes = ingredientesEntity.stream().map(IngredienteEntity::toDomain).collect(Collectors.toList());

        Produto produto = Produto.criaProduto(UUID.randomUUID(), produtoRequest.getNome(), produtoRequest.getPreco(), produtoRequest.getDescricao(), ingredientes, Tipo.fromValue(produtoRequest.getTipo().getValue()));
        produtoRepository.save(ProdutoEntity.criaEntity(produto, ingredientesEntity));
        return produto;
    }
}
