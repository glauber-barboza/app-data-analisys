package com.glauber.appdataanalisys.model;

import com.glauber.appdataanalisys.model.Interface.IRelatorio;

import java.util.List;
import java.util.stream.Collector;

import static org.springframework.util.CollectionUtils.isEmpty;

public class RelatorioErro implements IRelatorio {

    private List<FileError> errorsFile;

    private RelatorioErro(List<FileError> errorsFile) {
        this.errorsFile = errorsFile;
    }

    public static RelatorioErro of(List<FileError> errorsFile) {
        return new RelatorioErro(errorsFile);
    }

    @Override
    public byte[] getDadosRelatorio() {
        return errorsFile.stream().collect(
                Collector.of(StringBuilder::new,
                        (stringBuilder, str) -> stringBuilder.append(str.getErros()),
                        StringBuilder::append,
                        StringBuilder::toString)).getBytes();
    }

    @Override
    public Boolean withError() {
        return !isEmpty(errorsFile);
    }
}
