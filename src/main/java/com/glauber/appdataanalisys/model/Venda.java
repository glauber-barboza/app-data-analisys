package com.glauber.appdataanalisys.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class Venda extends ModelLineFile {

    private Integer id;
    private List<ItemVenda> itensVenda;
    private String vendedor;

    private Venda(Integer id, List<ItemVenda> itensVenda, String vendedor, Integer index, List<String> erros) {
        super.indexLinha = index;
        super.erros = erros;
        this.id = id;
        this.itensVenda = itensVenda;
        this.vendedor = vendedor;
    }

    public Venda(String error ,Integer index) {
        indexLinha = index;
        erros.add(error);
    }

    public static Venda of(Integer id, List<ItemVenda> itemVendas, String vendedor,Integer index){
        List<String> err= new ArrayList<>();
        if(isEmpty(id)){ err.add("campo 'id' não pode ser vazio nem nem nulo"); }
        if(isEmpty(itemVendas)){ err.add("campo 'itemVendas' não pode ser vazio nem nem nulo"); }
        if(isEmpty(vendedor)){ err.add("campo 'vendedor' não pode ser vazio nem nem nulo"); }

        return new Venda(id, itemVendas, vendedor,index, err);
    }

    public BigDecimal getTotal() {
        return itensVenda.stream()
                .map(ItemVenda::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Integer getId() {
        return id;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public String getVendedor() {
        return vendedor;
    }
}
