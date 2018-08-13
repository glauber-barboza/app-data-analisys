package com.glauber.appdataanalisys.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class Vendedor extends ModelLineFile {

    private String  cpf;
    private String nome;
    private BigDecimal salario;

    private Vendedor(String cpf, String nome, BigDecimal salario, Integer index, List<String> erros) {
        super.indexLinha = index;
        super.erros = erros;
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    public static Vendedor of(String cpf, String nome, BigDecimal salario, Integer index) {
        List<String> err= new ArrayList<>();
        if(isEmpty(cpf)){ err.add("campo 'cpf' não pode ser vazio nem nem nulo"); }
        if(isEmpty(nome)){ err.add("campo 'nome' não pode ser vazio nem nem nulo"); }
        if(isEmpty(salario)){ err.add("campo 'salario' não pode ser vazio nem nem nulo"); }

        return new Vendedor(cpf, nome, salario,index , err);
    }

    public Vendedor(String error ,Integer index) {
        indexLinha = index;
        erros.add(error);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
