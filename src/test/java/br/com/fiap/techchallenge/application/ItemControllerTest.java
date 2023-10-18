package br.com.fiap.techchallenge.application;

import br.com.fiap.techchallenge.application.request.ItemRequest;
import br.com.fiap.techchallenge.application.request.enums.TipoItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ItemControllerTest.class)
public class ItemControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveCriarItemValido() throws Exception {

        ItemRequest itemRequest = ItemRequest.criaItemRequest("X-Bacon", "Hamburguer de bacon com queijo", BigDecimal.TEN, TipoItem.BEBIDA, List.of());
        System.out.println(objectMapper.writeValueAsString(itemRequest));
    }
}
