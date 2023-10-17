package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;

public class Sobremesa extends Item {

    private Sobremesa(String nome, String descricao, List<Ingrediente> ingredientes,
                      BigDecimal valor) {
        super(nome, descricao, ingredientes, valor);
    }

    public static Sobremesa criaSobremesa(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        return new Sobremesa(nome, descricao, ingredientes, valor);
    }
}
