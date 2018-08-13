package com.glauber.appdataanalisys.service;

import com.glauber.appdataanalisys.domain.DataObjectType;
import com.glauber.appdataanalisys.model.*;
import com.glauber.appdataanalisys.model.Interface.IModel;
import com.glauber.appdataanalisys.model.Interface.IRelatorio;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProcessaDados {

    public ProcessaDados() {
    }

    public IRelatorio processFile(Path path) throws IOException {
        IRelatorio relatorio;
        AtomicInteger index = new AtomicInteger();
        Map<Class, List<IModel>> modelsMap = Files.lines(path)
                .filter(line -> line != null && !line.trim().isEmpty())
                .map(t -> parseLineToModel(t, index.getAndIncrement()))
                .collect(Collectors.groupingBy(IModel::getClass));

        List<IModel> cliente = modelsMap.get(Cliente.class);
        List<IModel> vendedor = modelsMap.get(Vendedor.class);
        List<IModel> vendas = modelsMap.get(Venda.class);

        relatorio = buildRelatorioErro(cliente, vendedor, vendas);

        if(relatorio.withError()) return relatorio;

        return buildRelatorioOk(cliente, vendedor, vendas);
    }


    private IModel parseLineToModel(String line, Integer index) {
        String type = line.substring(0, 3);
        String data = line.substring(4);
        return DataObjectType.of(type)
                .getParser()
                .parse(data, index + 1);
    }

    private Vendedor piorVendedor(List<IModel> salesmans, List<IModel> sales) {
        Map<String, Double> collect = sales.stream()
                .map(model -> (Venda) model)
                .collect(Collectors.groupingBy(Venda::getVendedor,
                        Collectors.summingDouble(sale -> sale.getTotal().doubleValue())));

        String name = collect.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return salesmans.stream()
                .map(model -> (Vendedor) model)
                .filter(model -> model.getNome().equals(name))
                .findFirst().get();
    }

    private Venda vendaMaisCara(List<IModel> vendas) {
        return vendas.stream()
                .map(model -> (Venda) model)
                .max(Comparator.comparing(Venda::getTotal))
                .get();
    }

    private IRelatorio buildRelatorioErro(List<IModel> cliente, List<IModel> vendedor, List<IModel> vendas) {
        List<IModel> errorList = new ArrayList<>();
        errorList.addAll(cliente);
        errorList.addAll(vendedor);
        errorList.addAll(vendas);
        return RelatorioErro.of(errorList.stream()
                .map(model -> (ModelLineFile) model)
                .filter(entity -> !isEmpty(entity.getErros()))
                .map(t -> new FileError(t.getIndexLinha(), t.getErros()))
                .collect(Collectors.toList()));
    }

    private IRelatorio buildRelatorioOk(List<IModel> cliente, List<IModel> vendedor, List<IModel> vendas) {
        Integer quantidadeClientes = cliente != null ? cliente.size() : 0;
        Integer quantidadeVendedor = vendedor != null ? vendedor.size() : 0;
        Venda vendaMaisCara = vendas != null ? vendaMaisCara(vendas) : null;
        Vendedor piorVendedor = (vendedor != null && vendas != null) ? piorVendedor(vendedor, vendas) : null;

        return new RelatorioLoja(quantidadeClientes, quantidadeVendedor, vendaMaisCara, piorVendedor);
    }
}
