package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;

public class Lanche extends Item {

    private Lanche(String nome, String descricao, List<Ingrediente> ingredientes,
                   BigDecimal valor) {
        super(nome, descricao, ingredientes, valor);
    }

    public static Lanche criaLanche(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        return new Lanche(nome, descricao, ingredientes, valor);
    }
}
