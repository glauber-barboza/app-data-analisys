package com.glauber.appdataanalisys.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ItemVenda extends ModelLineFile {
    private Integer id;
    private Integer quantidade;
    private BigDecimal valor;

    private ItemVenda(Integer id, Integer quantidade, BigDecimal valor, Integer index, List<String> erros) {
        super.indexLinha = index;
        super.erros = erros;
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItemVenda(String error ,Integer index) {
        indexLinha = index;
        erros.add(error);
    }

    public static ItemVenda of(Integer id, Integer quantidade, BigDecimal valor,Integer index) {
        List<String> err= new ArrayList<>();
        if(isEmpty(id)){ err.add("campo 'id' não pode ser vazio nem nem nulo"); }
        if(isEmpty(quantidade)){ err.add("campo 'quantidade' não pode ser vazio nem nem nulo"); }
        if(isEmpty(valor)){ err.add("campo 'valor' não pode ser vazio nem nem nulo"); }

        return new ItemVenda(id, quantidade, valor,index, err);
    }

    public BigDecimal getTotal() {
        return this.valor.multiply(new BigDecimal(quantidade));
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
