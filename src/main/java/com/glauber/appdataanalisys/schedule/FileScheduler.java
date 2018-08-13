package com.glauber.appdataanalisys.schedule;

import com.glauber.appdataanalisys.service.FileService;
import com.glauber.appdataanalisys.service.ProcessaDados;
import com.glauber.appdataanalisys.model.Interface.IRelatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${app.data.directory.in}")
    private String inDirectory;

    @Value("${app.data.directory.out}")
    private String outDirectory;

    @Autowired
    private ProcessaDados processaDados;

    @Autowired
    private FileService fileService;

    private List<String> filesDone = new ArrayList<>();

    @Scheduled(initialDelayString = "${app.job.interval}", fixedRateString = "#{${app.job.interval} * 1000}")
    public void executaJob() {
        try {
            Path inPath = Paths.get(inDirectory);

            LOGGER.info("Checando Arquivos...");

            Files.list(inPath)
                    .filter(path -> path.toString().endsWith(".dat") != fileService.foiExecutado(path))
                    .forEach(this::processarArquivo);

        } catch (Exception e) {
            LOGGER.error("Erro ao iniviar novos arquivos", e);
        }
    }

    private void processarArquivo(Path path) {
        LOGGER.info("Processando arquivo: " + path.getFileName());
        try {
            IRelatorio relatorio = processaDados.processFile(path);

            String fileName = fileService.getNameFileDone(path,relatorio.withError());

            criaArquivo(fileName,relatorio.getDadosRelatorio());

            LOGGER.info("Arquivo: " + path.getFileName() + " - finalizado !");
        } catch (Exception e) {
            String error = "Erro ao processar o arquivo: " + path.getFileName() +""+ e;
            String fileName = fileService.getNameFileDone(path,true);
            criaArquivo(fileName,error.getBytes());

            LOGGER.error("Erro ao processar o arquivo: " + path.getFileName(), e);
        }
    }

    private void criaArquivo(String fileName, byte[] data ){
        Path outPath = Paths.get(outDirectory, fileName);
        try {
            Files.write(outPath, data);
        } catch (IOException e) {
            LOGGER.error("Erro ao escrever arquivo.");
        }
        fileService.done(outPath);
    }
}
