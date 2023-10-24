package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.PagamentoRequest;
import br.com.fiap.techchallenge.application.controllers.request.QrCodeRequest;
import br.com.fiap.techchallenge.domain.services.PagamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private PagamentoService service;

    @PostMapping
    public ResponseEntity<String> criaQrCode(QrCodeRequest request) {
        return new ResponseEntity(service.geraQrCode(request), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<String> notificaPagamento(PagamentoRequest request) {
        return new ResponseEntity(service.confirmaPagamento(request), HttpStatus.OK);
    }
}
