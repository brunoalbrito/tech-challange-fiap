package br.com.fiap.techchallenge.infrastructure.network.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagamentoResponseClient {
    @JsonProperty("qr_data")
    private String qrData;

    public static PagamentoResponseClient of(String qrData) {
        return new PagamentoResponseClient(qrData);
    }
}
