package com.glauber.appdataanalisys.domain;

import com.glauber.appdataanalisys.model.Interface.IParser;
import com.glauber.appdataanalisys.parser.ParserCliente;
import com.glauber.appdataanalisys.parser.ParserVenda;
import com.glauber.appdataanalisys.parser.ParserVendedor;

import java.util.Objects;
import java.util.stream.Stream;

public enum DataObjectType {

    VENDEDOR("001"){
        @Override
        public IParser getParser() {
            return new ParserVendedor();
        }
    },
    CLIENTE("002"){
        @Override
        public IParser getParser() {
            return new ParserCliente();
        }
    },
    VENDA("003"){
        @Override
        public IParser getParser() {
            return new ParserVenda();
        }
    };

    private final String id;

    DataObjectType(String id) {
        this.id = id;
    }

    public static DataObjectType of(String id) {
        Objects.requireNonNull(id, "id não pode ser nullo!");
        if (id.trim().isEmpty()) throw new IllegalArgumentException("id não pode ser vazio!");

        return Stream.of(DataObjectType.values())
                .filter(dt -> dt.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("tipo de dado para id: '" + id + "' não encontrado!"));
    }

    public abstract IParser getParser();
}
