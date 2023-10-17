package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;

public class Bebida extends Item {

    private Bebida(String nome, String descricao, List<Ingrediente> ingredientes,
                  BigDecimal valor) {
        super(nome, descricao, ingredientes, valor);
    }

    public Bebida criaBebida(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        return new Bebida(nome, descricao, ingredientes, valor);
    }
}
