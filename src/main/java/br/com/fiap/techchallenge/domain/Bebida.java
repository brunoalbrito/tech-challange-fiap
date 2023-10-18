package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Bebida extends Item {

    private Bebida(UUID id, String nome, String descricao, List<Ingrediente> ingredientes,
                   BigDecimal valor) {
        super(id, nome, descricao, ingredientes, valor);
    }

    public static Bebida criaBebida(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        UUID id = UUID.randomUUID();
        return new Bebida(id, nome, descricao, ingredientes, valor);
    }
}
