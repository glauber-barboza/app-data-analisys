package com.glauber.appdataanalisys.model;

import com.glauber.appdataanalisys.model.Interface.IRelatorio;

public class RelatorioLoja implements IRelatorio {
    private Integer quantidadeClientes;
    private Integer quantidadeVendedor;
    private Venda vendaMaisCara;
    private Vendedor piorVendedor;
    private final Boolean withError = false;

    public RelatorioLoja(Integer quantidadeClientes, Integer quantidadeVendedor, Venda vendaMaisCara, Vendedor piorVendedor) {
        this.quantidadeClientes = quantidadeClientes;
        this.quantidadeVendedor = quantidadeVendedor;
        this.vendaMaisCara = vendaMaisCara;
        this.piorVendedor = piorVendedor;
    }

    @Override
    public byte[] getDadosRelatorio() {
        return ("Quantidade de clientes: " + quantidadeClientes + "\n" +
                "Quantidade de vendedores: " + quantidadeVendedor + "\n" +
                "ID da venda mais cara: " + getIdVenda() + "\n" +
                "Pior vendedor: " + getPiorVendedor()).getBytes();
    }

    @Override
    public Boolean withError() {
        return withError;
    }

    private Object getIdVenda() {
        return vendaMaisCara != null ? vendaMaisCara.getId() : "n/d";
    }

    private String getPiorVendedor() {
        return piorVendedor != null ? piorVendedor.getNome() : "n/d";
    }
}
