package br.com.fiap.techchallenge.application;

import br.com.fiap.techchallenge.application.controllers.ItemController;
import br.com.fiap.techchallenge.application.controllers.request.ItemRequest;
import br.com.fiap.techchallenge.application.request.ItemRequestTest;
import br.com.fiap.techchallenge.application.request.enums.TipoItem;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.services.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import mocks.ItemMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    @Test
    public void deveCriarItemValido() throws Exception {

        ItemRequestTest itemRequestTest = ItemRequestTest
                .criaItemRequest("X-Bacon", "Hamburguer de bacon com queijo", BigDecimal.TEN, TipoItem.BEBIDA, List.of());

        Item itemMock = ItemMock.criaLanche();

        when(itemService.criaItem(any(ItemRequest.class)))
                .thenReturn(itemMock);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemRequestTest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(itemMock.getDescricao()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(itemMock.getValor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ingredientesResponse").isNotEmpty());


        verify(itemService, times(1)).criaItem(any(ItemRequest.class));
    }
}
