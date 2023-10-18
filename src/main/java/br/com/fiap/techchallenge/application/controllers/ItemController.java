package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ItemRequest;
import br.com.fiap.techchallenge.application.controllers.response.ItemResponse;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/items")
public class ItemController {
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemRequest> criaItem(@RequestBody ItemRequest itemRequest) {
//        Item item = itemService.criaItem(itemRequest);
//
//        ItemResponse itemResponse = ItemResponse.builder()
//                .id(item.getId().toString())
//                .descricao(item.getDescricao())
//                .preco(item.getValor())
//                .build();

        return new ResponseEntity<>(itemRequest, HttpStatus.CREATED);
    }
}
