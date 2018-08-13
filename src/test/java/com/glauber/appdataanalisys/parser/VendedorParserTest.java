package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.fatory.VendedorFactory;
import com.glauber.appdataanalisys.model.Vendedor;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendedorParserTest {
    private ParserVendedor parser;

    @Before
    public void before() {
        parser = new ParserVendedor();
    }

    @Test
    public void testValidData() {
        String vendedorLine = new VendedorFactory.Builder()
                .semId()
                .build();
        Vendedor vendedor = parser.parse(vendedorLine,1);

        assertEquals("1234567891234", vendedor.getCpf());
        assertEquals("Diego", vendedor.getNome());
        assertEquals(new BigDecimal("50000"), vendedor.getSalario());
    }

    @Test
    public void testInvalidCpfValidData() {
        Integer indexLinha = 1;
        String vendedorLine = new VendedorFactory.Builder()
                .semId()
                .cpfInvalido()
                .build();
        Vendedor vendedor = parser.parse(vendedorLine,indexLinha);

        assertEquals("campo 'cpf' não pode ser vazio nem nem nulo", vendedor.getErros().get(0));
        assertEquals(indexLinha, vendedor.getIndexLinha());
    }

    @Test
    public void testInvalidNomeValidData() {
        Integer indexLinha = 1;
        String vendedorLine = new VendedorFactory.Builder()
                .semId()
                .nomeInvalido()
                .build();
        Vendedor vendedor = parser.parse(vendedorLine,1);

        assertEquals("campo 'nome' não pode ser vazio nem nem nulo", vendedor.getErros().get(0));
        assertEquals(indexLinha, vendedor.getIndexLinha());
    }

    @Test
    public void testInvalidSalarioValidData() {
        Integer indexLinha = 1;
        String vendedorLine = new VendedorFactory.Builder()
                .semId()
                .salarioInvalido()
                .build();
        Vendedor vendedor = parser.parse(vendedorLine,1);

        assertEquals("campo 'salario' não pode ser vazio nem nem nulo", vendedor.getErros().get(0));
        assertEquals(indexLinha, vendedor.getIndexLinha());
    }

    @Test
    public void testVendedorErrorDelim() {
        Integer indexLinha = 1;
        String vendedorLine = new VendedorFactory.Builder()
                .semId()
                .semDelim()
                .build();
        Vendedor vendedor = parser.parse(vendedorLine,1);

        assertEquals("tokens do vendedor devem ser maior que 3", vendedor.getErros().get(0));
        assertEquals(indexLinha, vendedor.getIndexLinha());
    }
}
