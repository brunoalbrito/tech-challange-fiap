package br.com.fiap.techchallenge.domain.services;

import java.util.UUID;

import br.com.fiap.techchallenge.domain.Pagamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PagamentoService {

    public Pagamento cria(UUID pedidoId) {
        log.info("Qrcode de pagamento para pedido {} criado", pedidoId);
        return Pagamento.criaPagamento(UUID.randomUUID(), UUID.randomUUID().toString());
    }

    public boolean estaPago(UUID pedidoId) {
        log.info("Sempre devolve a verificacao do pagamento como feito.");
        return true;
    }
}
