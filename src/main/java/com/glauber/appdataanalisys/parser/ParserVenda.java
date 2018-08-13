package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.model.Interface.IParser;
import com.glauber.appdataanalisys.model.ItemVenda;
import com.glauber.appdataanalisys.model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ParserVenda implements IParser<Venda> {

    public Venda parse(String line, Integer index) {
        StringTokenizer tokens = split(line);

        if (tokens.countTokens() < 3) return new Venda("tokens da venda devem ser maior que 3",index);

        String tokenId = tokens.nextToken().trim();
        Integer id = !isEmpty(tokenId) ? Integer.parseInt(tokenId) : null;
        List<ItemVenda> itens = parseItens(tokens.nextToken().trim(),index);
        String vendedor = tokens.nextToken().trim();

        return Venda.of(id,itens,vendedor,index);
    }

    private List<ItemVenda> parseItens(String token, Integer index) {
        List<ItemVenda> items = new ArrayList<>();

        if (token.length() < 1) throw new RuntimeException("Itens venda na linha "+ index+ " com erro ");
        String stringSemAsChaves = token.substring(1, token.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(stringSemAsChaves, ",");

        while (tokenizer.hasMoreTokens()) {
            items.add(new ParserItem().parse(tokenizer.nextToken(),index));
        }

        return items;
    }
}
