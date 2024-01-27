package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.Tipo;
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

    private static Produto criaProduto(UUID id, String nome, BigDecimal preco, String descricao, List<Ingrediente> ingredientes, Tipo tipo) {
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
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String nome;
        private BigDecimal preco;
        private String descricao;
        private List<Ingrediente> ingredientes;
        private Tipo tipo;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            Produto.validateNome(nome);
            this.nome = nome;
            return this;
        }

        public Builder preco(BigDecimal preco) {
            Produto.validatePreco(preco);
            this.preco = preco;
            return this;
        }

        public Builder descricao(String descricao) {
            Produto.validateDescricao(descricao);
            this.descricao = descricao;
            return this;
        }

        public Builder ingredientes(List<Ingrediente> ingredientes) {
            Produto.validateIngredientes(ingredientes);
            this.ingredientes = ingredientes;
            return this;
        }

        public Builder tipo(Tipo tipo) {
            Produto.validateTipo(tipo);
            this.tipo = tipo;
            return this;
        }

        public Produto build() {
            return Produto.criaProduto(id, nome, preco, descricao, ingredientes, tipo);
        }
    }
}
