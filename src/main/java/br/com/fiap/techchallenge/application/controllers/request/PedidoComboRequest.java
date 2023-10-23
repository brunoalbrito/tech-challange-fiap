package br.com.fiap.techchallenge.application.controllers.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoComboRequest {
    private String lancheId;

    private String bebidaId;

    private String sobremesaId;
}
