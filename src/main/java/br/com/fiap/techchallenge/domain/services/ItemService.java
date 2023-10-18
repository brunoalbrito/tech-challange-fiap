package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.application.controllers.request.ItemRequest;
import br.com.fiap.techchallenge.application.controllers.request.enums.TipoItemRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.Lanche;
import br.com.fiap.techchallenge.infrastructure.entity.ItemEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private IngredienteService ingredienteService;

    private ItemRepository itemRepository;

    public Item criaItem(ItemRequest itemRequest) {
        List<Ingrediente> ingredientes = itemRequest.getIngredientes().stream()
                .map(IngredienteRequest::getId)
                .map(UUID::fromString)
                .map(ingredienteService::buscaPorId)
                .toList();

        if(itemRequest.getTipo().equals(TipoItemRequest.LANCHE)) {
            Lanche lanche = Lanche.criaLanche(itemRequest.getNome(), itemRequest.getDescricao(), ingredientes, itemRequest.getValor());
            ItemEntity itemEntity = ItemEntity.criaLanche(lanche);
            itemRepository.save(itemEntity);
            return lanche;
        }

        return Lanche.criaLanche(itemRequest.getNome(), itemRequest.getDescricao(), ingredientes, itemRequest.getValor());
    }
}
