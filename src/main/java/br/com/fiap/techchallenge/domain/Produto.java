package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Produto {
    private UUID id;

    private String nome;

    private BigDecimal preco;

    private String descricao;

    private List<Ingrediente> ingredientes;

    private Tipo tipo;

    public static Produto criaProduto(UUID id, String nome, BigDecimal preco, String descricao, List<Ingrediente> ingredientes, Tipo tipo) {
        validateNome(nome);
        validatePreco(preco);
        validateDescricao(descricao);
        validateIngredientes(ingredientes);
        validateTipo(tipo);
        return new Produto(id, nome, preco, descricao, ingredientes, tipo);
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não deve ser nulo ou vazio.");
        }
    }

    private static void validatePreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
    }

    private static void validateDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao cannot be null or empty.");
        }
    }

    private static void validateIngredientes(List<Ingrediente> ingredientes) {
        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new IllegalArgumentException("Ingredientes não pode ser nulo ou vazio.");
        }
    }

    private static void validateTipo(Tipo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo não pode ser nulo.");
        }
    }

    public static Produto toDomain(ProdutoEntity produtoEntity) {
        var ingredientesEntity = produtoEntity.getIngredientes().stream().map(IngredienteEntity::toDomain).toList();
        return Produto.criaProduto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getDescricao(), ingredientesEntity, Tipo.fromValue(produtoEntity.getTipo().getValue()));
    }
}
