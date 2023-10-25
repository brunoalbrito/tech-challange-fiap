package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.domain.Pagamento;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoService {

    public Pagamento cria() {
        return Pagamento.criaPagamento(UUID.randomUUID(), UUID.randomUUID().toString());
    }
}
