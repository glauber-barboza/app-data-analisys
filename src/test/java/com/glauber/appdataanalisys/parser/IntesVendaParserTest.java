package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.fatory.ItensVendaFactory;
import com.glauber.appdataanalisys.model.ItemVenda;
import com.glauber.appdataanalisys.model.Venda;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class IntesVendaParserTest {
    private ParserItem parser;

    @Before
    public void before() {
        parser = new ParserItem();
    }

    @Test
    public void testValidData() {
        Integer id =1;
        Integer quantidade =10;
        BigDecimal valor = new BigDecimal(100);
        String vendedorLine = new ItensVendaFactory.Builder()
                .build();
        ItemVenda venda = parser.parse(vendedorLine,1);

        assertEquals(id, venda.getId());
        assertEquals(quantidade,venda.getQuantidade());
        assertEquals(valor, venda.getValor());
        assertEquals(valor.multiply(new BigDecimal(quantidade)), venda.getTotal());
    }

    @Test
    public void testIdInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ItensVendaFactory.Builder()
                .semId()
                .build();
        ItemVenda venda = parser.parse(vendedorLine,1);

        assertEquals("campo 'id' não pode ser vazio nem nem nulo", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }

    @Test
    public void testQuantidadeInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ItensVendaFactory.Builder()
                .semQuantidade()
                .build();
        ItemVenda venda = parser.parse(vendedorLine,1);

        assertEquals("campo 'quantidade' não pode ser vazio nem nem nulo", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }

    @Test
    public void testValorInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ItensVendaFactory.Builder()
                .semValor()
                .build();
        ItemVenda venda = parser.parse(vendedorLine,1);

        assertEquals("campo 'valor' não pode ser vazio nem nem nulo", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }

    @Test
    public void testVendaErrorDelim() {
        Integer indexLinha =1;
        String vendedorLine = new ItensVendaFactory.Builder()
                .semDelim()
                .build();
        ItemVenda venda = parser.parse(vendedorLine,1);

        assertEquals("tokens do itemVenda devem ser maior que 3", venda.getErros().get(0));
        assertEquals(indexLinha, venda.getIndexLinha());
    }
}
