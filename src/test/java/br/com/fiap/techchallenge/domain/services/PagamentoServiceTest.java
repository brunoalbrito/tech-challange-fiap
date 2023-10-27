package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.domain.Pagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    public void deveCriarPagamentoValido(){
        Pagamento pagamento =  pagamentoService.cria(UUID.randomUUID());

        assertNotNull(pagamento);
        assertNotNull(pagamento.getId());
        assertNotNull(pagamento.getQrCode());
    }

    public void deveRetornarPagamentoComoFeito(){
        boolean estaPago = pagamentoService.estaPago(UUID.randomUUID());

        assertTrue(estaPago);
    }
}
