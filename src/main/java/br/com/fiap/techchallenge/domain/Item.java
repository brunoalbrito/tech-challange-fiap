package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoDeItem;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Item {


    private String nome;
    private String descricao;
    private List<Ingrediente> ingredientes;
    private BigDecimal valor;
    private TipoDeItem tipoDeItem;

    private Item(String nome, String descricao, List<Ingrediente> ingredientes,
                 BigDecimal valor, TipoDeItem tipoDeItem) {

        validateNome(nome);
        validateDescricao(descricao);
        validateIngredientes(ingredientes);
        validateValor(valor);
        validateTipoDeItem(tipoDeItem);
        this.nome = nome;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.valor = valor;
        this.tipoDeItem = tipoDeItem;
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

    private static void validateTipoDeItem(TipoDeItem tipoDeItem) {
        if (tipoDeItem == null) {
            throw new IllegalArgumentException("TipoDeItem cannot be null.");
        }
    }

    public static Item criaLanche(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        return new Item(nome, descricao, ingredientes, valor, TipoDeItem.LANCHE);
    }
}