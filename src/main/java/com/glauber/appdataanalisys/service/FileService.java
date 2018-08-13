package com.glauber.appdataanalisys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${app.data.directory.in}")
    private String inDirectory;

    @Value("${app.data.directory.out}")
    private String outDirectory;

    private List<String> filesDone = new ArrayList<>();
    private final String FILE_DONE = "done.dat";
    private final String FILE_DONE_ERROR = "error.done.dat";

    @PostConstruct
    private void init() {
        Path inPath = Paths.get(inDirectory);
        Path outPath = Paths.get(outDirectory);

        if (!inPath.toFile().exists()) throw new RuntimeException("diretório de entrada não existe: " + inDirectory);
        if (!outPath.toFile().exists()) throw new RuntimeException("diretório de saida não existe: " + outDirectory);

        try {
            Files.list(outPath)
                    .filter(path -> path.toString().endsWith(".dat"))
                    .forEach(this::done);
        } catch (IOException e) {
            LOGGER.error("Error ao verificar arquivos de saida ", e);
        }
    }

    public boolean foiExecutado(Path path) {
        return filesDone.contains(buscaNomeArquivoProcessado(path));
    }

    public void done(Path path) {
        filesDone.add(path.getFileName().toString());
    }

    public String getNameFileDone(Path path, Boolean withError) {
        String fileName = path.getFileName().toString();

        if (withError) return fileName.substring(0, fileName.length() - 3) + FILE_DONE_ERROR;

        return fileName.substring(0, fileName.length() - 3) + FILE_DONE;
    }

    public String buscaNomeArquivoProcessado(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.substring(0, fileName.length() - 3) + FILE_DONE;
    }
}
