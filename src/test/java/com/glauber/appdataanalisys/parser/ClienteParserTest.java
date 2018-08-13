package com.glauber.appdataanalisys.parser;

import com.glauber.appdataanalisys.fatory.ClienteFactory;
import com.glauber.appdataanalisys.model.Cliente;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ClienteParserTest {
    private ParserCliente parser;

    @Before
    public void before() {
        parser = new ParserCliente();
    }

    @Test
    public void testValidData() {
        String vendedorLine = new ClienteFactory.Builder()
                .semId()
                .build();
        Cliente cliente = parser.parse(vendedorLine,1);

        assertEquals("2345675434544345", cliente.getCnpj());
        assertEquals("Jose da Silva", cliente.getNome());
        assertEquals("Rural", cliente.getBusinessArea());
    }

    @Test
    public void testCnpjInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ClienteFactory.Builder()
                .semId()
                .cnpjInvalido()
                .build();
        Cliente cliente = parser.parse(vendedorLine,1);

        assertEquals("campo 'cnpj' não pode ser vazio nem nem nulo", cliente.getErros().get(0));
        assertEquals(indexLinha, cliente.getIndexLinha());
    }

    @Test
    public void testNomeInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ClienteFactory.Builder()
                .semId()
                .nomeInvalido()
                .build();
        Cliente cliente = parser.parse(vendedorLine,1);

        assertEquals("campo 'nome' não pode ser vazio nem nem nulo", cliente.getErros().get(0));
        assertEquals(indexLinha, cliente.getIndexLinha());
    }

    @Test
    public void testBusinesAreaInvalido() {
        Integer indexLinha = 1;
        String vendedorLine = new ClienteFactory.Builder()
                .semId()
                .businesAreaInvalido()
                .build();
        Cliente cliente = parser.parse(vendedorLine,1);

        assertEquals("campo 'businessArea' não pode ser vazio nem nem nulo", cliente.getErros().get(0));
        assertEquals(indexLinha, cliente.getIndexLinha());
    }

    @Test
    public void testeSemDelim() {
        Integer indexLinha = 1;
        String vendedorLine = new ClienteFactory.Builder()
                .semId()
                .semDelim()
                .build();
        Cliente cliente = parser.parse(vendedorLine,1);

        assertEquals("tokens do cliente devem ser maior que 3", cliente.getErros().get(0));
        assertEquals(indexLinha, cliente.getIndexLinha());
    }
}
