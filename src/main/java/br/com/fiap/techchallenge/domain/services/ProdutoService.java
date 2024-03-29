package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Deprecated
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final IngredienteService ingredienteService;

    public Produto criaProduto(ProdutoRequest produtoRequest) {

        List<Ingrediente> ingredientes = ingredienteService.buscaIngredientesPorId(produtoRequest.getIngredientes());

        if (ingredientes.isEmpty()) {
            throw new IllegalArgumentException("Ingredientes informados não existem.");
        }

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome(produtoRequest.getNome())
                .preco(produtoRequest.getPreco())
                .descricao(produtoRequest.getDescricao())
                .ingredientes(ingredientes)
                .tipo(Tipo.fromValue(produtoRequest.getTipo().getValue()))
                .build();

        produtoRepository.save(ProdutoEntity.toEntity(produto));
        return produto;
    }
}
