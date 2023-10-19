//package br.com.fiap.techchallenge.application.controllers;
//
//import br.com.fiap.techchallenge.application.controllers.response.ComboResponse;
//import br.com.fiap.techchallenge.domain.Combo;
//import br.com.fiap.techchallenge.domain.Lanche;
//import br.com.fiap.techchallenge.domain.services.ItemService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("api/combos")
//public class ComboController {
//
//    private ComboService comboService;
//
//    private ItemService itemService;
//
//    @PostMapping
//    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
//        Lanche lanche = itemService.findById(comboRequest.getLanche().getId());
//        Combo combo = Combo.criaCombo(comboRequest);
//
//        Combo combo = comboService.criaCombo(comboRequest);
//
//        return null;
//    }
//}
//import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
