package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.model.Interface.IParser;
import com.glauber.appdataanalisys.model.Vendedor;

import java.math.BigDecimal;
import java.util.StringTokenizer;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ParserVendedor implements IParser<Vendedor> {

    public Vendedor parse(String line,Integer index) {
        StringTokenizer tokens = split(line);

        if (tokens.countTokens() < 3) return new Vendedor("tokens do vendedor devem ser maior que 3",index);

        String cpf = tokens.nextToken().trim();
        String nome = tokens.nextToken().trim();
        String tokenSalario = tokens.nextToken().trim();
        BigDecimal salario = !isEmpty(tokenSalario) ? new BigDecimal(tokenSalario) : null;

        return Vendedor.of(cpf,nome,salario,index);
    }
}
