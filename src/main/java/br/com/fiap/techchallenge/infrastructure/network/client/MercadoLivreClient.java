package br.com.fiap.techchallenge.infrastructure.network.client;

import br.com.fiap.techchallenge.infrastructure.network.data.PagamentoClient;
import br.com.fiap.techchallenge.infrastructure.network.data.PagamentoResponseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

public class MercadoLivreClient {

    private final ObjectMapper objectMapper;

    public MercadoLivreClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean verificaPagamento(UUID pedidoId) {
        return true;
    }

    public String criaPagamento(PagamentoClient pagamentoClient) {

        String apiUrl = "https://api.mercadopago.com/instore/orders/qr/seller/collectors/64579943/pos/TECHCHALLENGEFIAP/qrs";

        String accessToken = "Bearer TEST-252309509037954-012815-07dc98cd6fb3240d4aac5706ada98674-64579943";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);


        HttpEntity<String> requestEntity = new HttpEntity<>(writeAsJson(pagamentoClient), headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PagamentoResponseClient> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, PagamentoResponseClient.class);

        return Objects.requireNonNull(responseEntity.getBody()).getQrData();
    }

    private String writeAsJson(PagamentoClient pagamentoClient) {
        try {
            return objectMapper.writeValueAsString(pagamentoClient);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
