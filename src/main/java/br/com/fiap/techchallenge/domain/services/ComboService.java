//package br.com.fiap.techchallenge.domain.services;
//
//import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
//import br.com.fiap.techchallenge.domain.Combo;
//import br.com.fiap.techchallenge.domain.Lanche;
//import br.com.fiap.techchallenge.infrastructure.repository.ItemRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ComboService {
//
//    private ComboRepository comboRepository;
//    private ItemRepository itemRepository;
//    public Combo criaCombo(ComboRequest comboRequest) {
//
//        Lanche lanche = itemRepository.findById(comboRequest.getLanche().getId()).toDomain();
//        Bebida bebida = itemRepository.findById(comboRequest.getBebida().getId()).toDomain();
//        Sobremesa sobremesa = itemRepository.findById(comboRequest.getSobremesa().getId()).toDomain();
//
//        Combo combo = Combo.criaCombo(Optional.of(lanche), Optional.of(bebida), Optional.of(sobremesa));
//        comboRepository.save(combo.toEntity());
//
//        return combo;
//    }
//}
