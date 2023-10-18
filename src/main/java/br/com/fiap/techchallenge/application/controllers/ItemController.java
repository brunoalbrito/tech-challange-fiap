package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ItemRequest;
import br.com.fiap.techchallenge.application.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.application.controllers.response.ItemResponse;
import br.com.fiap.techchallenge.application.controllers.response.enums.TipoItemResponse;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/items")
public class ItemController {
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> criaItem(@RequestBody ItemRequest itemRequest) {
        Item item = itemService.criaItem(itemRequest);

        List<IngredienteResponse> ingredientesResponse = item.getIngredientes().stream()
                .map(ingrediente -> IngredienteResponse.builder()
                        .id(ingrediente.getId().toString())
                        .descricao(ingrediente.getDescricao())
                        .build())
                .toList();

        ItemResponse itemResponse = ItemResponse.builder()
                .id(item.getId().toString())
                .descricao(item.getDescricao())
                .valor(item.getValor())
                .ingredientesResponse(ingredientesResponse)
                .tipo(TipoItemResponse.valueOf(item.getTipo().toString()))
                .build();

        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }
}
