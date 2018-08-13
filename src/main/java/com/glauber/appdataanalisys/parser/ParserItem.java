package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.model.Interface.IParser;
import com.glauber.appdataanalisys.model.ItemVenda;

import java.math.BigDecimal;
import java.util.StringTokenizer;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ParserItem implements IParser<ItemVenda> {
    private final String delim = "-";

    @Override
    public ItemVenda parse(String line, Integer index) {
        StringTokenizer tokens = split(line, delim);

        if (tokens.countTokens() < 3) return new ItemVenda("tokens do itemVenda devem ser maior que 3",index);

        String tokenId = tokens.nextToken().trim();
        String tokenQuantidade = tokens.nextToken().trim();
        String tokenPreco = tokens.nextToken().trim();

        Integer id = !isEmpty(tokenId) ? Integer.parseInt(tokenId) : null;
        Integer quantidade = !isEmpty(tokenQuantidade) ? Integer.parseInt(tokenQuantidade) : null;
        BigDecimal preco = !isEmpty(tokenPreco) ? new BigDecimal(tokenPreco) : null;

        return ItemVenda.of(id, quantidade, preco,index);
    }
}
