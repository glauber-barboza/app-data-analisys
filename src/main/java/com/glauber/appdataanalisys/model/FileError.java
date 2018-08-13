package com.glauber.appdataanalisys.model;

import java.util.List;
import java.util.stream.Collector;

import static org.springframework.util.ObjectUtils.isEmpty;

public class FileError {
    private Integer linhaErro;
    private List<String> error;
    private final Boolean withError = true;

    public FileError(Integer linhaErro, List<String> error) {
        this.linhaErro = linhaErro;
        this.error = error;
    }

    public String getErros() {
        if(isEmpty(error)) return "";
        return ("--------------------------------------------------------" + "\n" +
                "Linha do erro: " + linhaErro + "\n" +
                "Erros : " + getErrosInLine() + "\n");
    }

    private String getErrosInLine() {
        return error.stream().filter(entity -> !isEmpty(entity))
                .collect(Collector.of(StringBuilder::new,
                        (stringBuilder, str) -> stringBuilder.append(str).append(System.getProperty("line.separator")),
                        StringBuilder::append,
                        StringBuilder::toString));
    }

}
