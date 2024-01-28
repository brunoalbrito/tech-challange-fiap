package br.com.fiap.techchallenge.infrastructure.network.data;

import br.com.fiap.techchallenge.domain.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemClient {
    @JsonProperty("sku_number")
    private String skuNumber;

    private String category;

    private String title;

    private String description;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    private final Integer quantity = 1;

    @JsonProperty("unit_measure")
    private final String unitMeasure = "unit";

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    public static ItemClient fromProduto(Produto produto) {
        return new ItemClient(
                produto.getId().toString(),
                produto.getTipo().name(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getPreco());
    }
}
