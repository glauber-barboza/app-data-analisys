package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.model.Cliente;
import com.glauber.appdataanalisys.model.Interface.IParser;

import java.util.StringTokenizer;

public class ParserCliente implements IParser<Cliente> {

    @Override
    public Cliente parse(String line,Integer index) {
        StringTokenizer tokens = split(line);

        if (tokens.countTokens() < 3) return  new Cliente("tokens do cliente devem ser maior que 3",index);

        String cnpj = tokens.nextToken().trim();
        String nome = tokens.nextToken().trim();
        String businessArea = tokens.nextToken().trim();

        return Cliente.of(cnpj, nome, businessArea,index);
    }
}
