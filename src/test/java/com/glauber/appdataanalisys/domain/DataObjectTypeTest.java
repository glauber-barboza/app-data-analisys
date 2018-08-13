package com.glauber.appdataanalisys.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataObjectTypeTest {

    @Test(expected = NullPointerException.class)
    public void testNullType() {
        DataObjectType.of(null);
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyType() {
        DataObjectType.of(" ");
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidType() {
        DataObjectType.of("aaa");
    }

    @Test
    public void testValidVendedorType() {
        DataObjectType type = DataObjectType.of("001");
        assertEquals(DataObjectType.VENDEDOR, type);
    }

    @Test
    public void testValidClienteType() {
        DataObjectType type = DataObjectType.of("002");
        assertEquals(DataObjectType.CLIENTE, type);
    }

    @Test
    public void testValidVendaType() {
        DataObjectType type = DataObjectType.of("003");
        assertEquals(DataObjectType.VENDA, type);
    }
}
