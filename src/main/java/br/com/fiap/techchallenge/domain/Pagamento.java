package br.com.fiap.techchallenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pagamento {
    private UUID id;

    private String qrCode;

    public static Pagamento criaPagamento(UUID randomUUID, String qrCode) {
        validateQrCode(qrCode);
        return new Pagamento(randomUUID, qrCode);
    }

    private static void validateQrCode(String qrCode) {
        if (qrCode == null || qrCode.isEmpty()) {
            throw new IllegalArgumentException("QrCode não deve ser nulo ou vazio.");
        }
    }
}
