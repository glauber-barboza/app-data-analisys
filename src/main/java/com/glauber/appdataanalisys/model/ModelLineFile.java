package com.glauber.appdataanalisys.model;

import com.glauber.appdataanalisys.model.Interface.IModel;

import java.util.ArrayList;
import java.util.List;

public class ModelLineFile implements IModel {

    protected List<String> erros = new ArrayList<>();

    protected Integer indexLinha;

    public List<String> getErros() {
        return erros;
    }

    public Integer getIndexLinha() {
        return indexLinha;
    }
}
