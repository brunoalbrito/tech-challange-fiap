package br.com.fiap.techchallenge.infrastructure.network.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PagamentoResponseClient {
    @JsonProperty("in_store_order_id")
    private String inStoreOrderId;

    @JsonProperty("qr_data")
    private String qrData;
}
