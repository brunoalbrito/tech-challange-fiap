package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoItem;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public abstract class Item {
    private UUID id;
    private String nome;
    private String descricao;
    private List<Ingrediente> ingredientes;
    private BigDecimal valor;

    protected Item(UUID id, String nome, String descricao, List<Ingrediente> ingredientes,
                   BigDecimal valor) {

        validateNome(nome);
        validateDescricao(descricao);
        validateIngredientes(ingredientes);
        validateValor(valor);
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.valor = valor;
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome cannot be null or empty.");
        }
    }

    private static void validateDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao cannot be null or empty.");
        }
    }

    private static void validateIngredientes(List<Ingrediente> ingredientes) {
        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new IllegalArgumentException("Ingredientes cannot be null or empty.");
        }
    }

    private static void validateValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor must be a positive value.");
        }
    }

    public abstract TipoItem getTipo();
}