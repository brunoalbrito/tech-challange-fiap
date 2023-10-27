package br.com.fiap.techchallenge.infrastructure.entity;

import java.util.UUID;

import br.com.fiap.techchallenge.domain.Pagamento;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class PagamentoEmbedable {
    private UUID paymentId;

    private String paymentQrCode;


    public Pagamento toDomain() {
        return Pagamento.criaPagamento(getPaymentId(), getPaymentQrCode());
    }


    public static PagamentoEmbedable toEntity(Pagamento pagamento) {
        return new PagamentoEmbedable(pagamento.getId(), pagamento.getQrCode());
    }
}
