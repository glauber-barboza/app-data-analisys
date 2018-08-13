package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.fatory.VendaFactory;
import com.glauber.appdataanalisys.model.Venda;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VendaParserTest {
    private ParserVenda parser;

    @Before
    public void before() {
        parser = new ParserVenda();
    }

    @Test
    public void testValidData() {
        Integer idExpected =1;
        BigDecimal total = new BigDecimal(1000);
        String vendedorLine = new VendaFactory.Builder()
                .semId()
                .build();
        Venda venda = parser.parse(vendedorLine,1);

        assertEquals(idExpected, venda.getId());
        assertNotNull(venda.getItensVenda());
        assertEquals(total, venda.getTotal());
        assertEquals("Diego", venda.getVendedor());
    }

    @Test
    public void testNomeInvalido() {
        Integer indexLinha =1;
        String vendedorLine = new VendaFactory.Builder()
                .semId()
                .semVendedor()
                .build();
        Venda venda = parser.parse(vendedorLine,indexLinha);

        assertEquals("campo 'vendedor' não pode ser vazio nem nem nulo", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }

    @Test
    public void testIdVendaInvalido() {
        Integer indexLinha =1;
        String vendedorLine = new VendaFactory.Builder()
                .semId()
                .semIdVenda()
                .build();
        Venda venda = parser.parse(vendedorLine,indexLinha);

        assertEquals("campo 'id' não pode ser vazio nem nem nulo", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }

    @Test(expected = RuntimeException.class)
    public void testItensVendaInvalido() {
        Integer indexLinha =1;
        String vendedorLine = new VendaFactory.Builder()
                .semId()
                .semItensVenda()
                .build();
        parser.parse(vendedorLine,indexLinha);
    }

    @Test
    public void testVendaErrorDelim() {
        Integer indexLinha =1;
        String vendedorLine = new VendaFactory.Builder()
                .semId()
                .semDelim()
                .build();
        Venda venda = parser.parse(vendedorLine,indexLinha);

        assertEquals("tokens da venda devem ser maior que 3", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }
}
