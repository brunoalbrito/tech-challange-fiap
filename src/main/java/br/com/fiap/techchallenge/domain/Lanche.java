package br.com.fiap.techchallenge.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Lanche extends Item {

    private Lanche(UUID id, String nome, String descricao, List<Ingrediente> ingredientes,
                   BigDecimal valor) {
        super(id, nome, descricao, ingredientes, valor);
    }

    public static Lanche criaLanche(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        UUID uuid = UUID.randomUUID();
        return new Lanche(uuid, nome, descricao, ingredientes, valor);
    }
}
