package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.Pagamento;

public interface PagamentoRepository {
    Pagamento save(Pagamento pagamento);
}
