package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Ingrediente;

import java.util.List;
import java.util.UUID;

public interface IngredienteGateway {

    List<Ingrediente> buscaIngredientesPorId(List<UUID> ids);

    Ingrediente salva(Ingrediente ingrediente);
}
