package com.glauber.appdataanalisys.model;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class Cliente extends ModelLineFile {

    private String cnpj;
    private String nome;
    private String businessArea;

    private Cliente(String cnpj, String nome, String salario, Integer index, List<String> erros) {
        super.indexLinha = index;
        super.erros = erros;
        this.cnpj = cnpj;
        this.nome = nome;
        this.businessArea = salario;
    }

    public Cliente(String error ,Integer index) {
        indexLinha = index;
        erros.add(error);
    }

    public static Cliente of(String cnpj, String name, String businessArea,Integer index) {
        List<String> err= new ArrayList<>();
        if(isEmpty(cnpj)){ err.add("campo 'cnpj' não pode ser vazio nem nem nulo"); }
        if(isEmpty(name)){ err.add("campo 'nome' não pode ser vazio nem nem nulo"); }
        if(isEmpty(businessArea)){ err.add("campo 'businessArea' não pode ser vazio nem nem nulo"); }

        return new Cliente(cnpj, name, businessArea,index,err);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getBusinessArea() {
        return businessArea;
    }
}
