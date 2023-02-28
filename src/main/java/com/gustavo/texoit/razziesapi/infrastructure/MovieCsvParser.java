package com.gustavo.texoit.razziesapi.infrastructure;

import com.gustavo.texoit.razziesapi.domain.worstmovie.Movie;
import com.gustavo.texoit.razziesapi.domain.worstmovie.MovieRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MovieCsvParser implements ApplicationRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        movieBuilder().forEach(i -> {
            String[] producers = i.getProducer().split("( and |,)");
            for (String producer : producers) {
                if(Strings.isNotBlank(producer)) {
                    Movie movie = new Movie();
                    movie.setYear(i.getYear());
                    movie.setTitle(i.getTitle());
                    movie.setStudio(i.getStudio());
                    movie.setWinner(i.getWinner());
                    movie.setProducer(producer.trim());
                    movieRepository.save(movie);
                }
            }
        });

    }

    private List<MovieCsvAdapter> movieBuilder() throws IOException {
        MovieCsvTransfer transfer = new MovieCsvTransfer();

        log.info("Obtendo arquivo com a lista de indicados e vencedores");
        File file = ResourceUtils.getFile("classpath:movielist.csv");

        try (Reader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            CsvToBean<MovieCsvAdapter> bean = new CsvToBeanBuilder<MovieCsvAdapter>(reader)
                    .withSeparator(';')
                    .withType(MovieCsvAdapter.class)
                    .build();

            transfer.setCsvList(bean.parse());
            log.info("Itens da lista trasnformados em objetos");
        }
        return transfer.getCsvList();
    }
}
