package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Sobremesa extends Item {

    private Sobremesa(UUID id, String nome, String descricao, List<Ingrediente> ingredientes,
                      BigDecimal valor) {
        super(id, nome, descricao, ingredientes, valor);
    }

    public static Sobremesa criaSobremesa(String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        UUID id = UUID.randomUUID();
        return new Sobremesa(id, nome, descricao, ingredientes, valor);
    }

    @Override
    public TipoItem getTipo() {
        return TipoItem.SOBREMESA;
    }
}
