package com.gustavo.texoit.razziesapi.domain;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.gustavo.texoit.razziesapi.domain.worstmovie.Movie;
import com.gustavo.texoit.razziesapi.domain.worstmovie.MovieAwardGap;
import com.gustavo.texoit.razziesapi.domain.worstmovie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerService {

    private final MovieRepository movieRepository;

    public MovieAwardGap getIntervalBetweenAwards() {
        List<ProducerRazzies> razzies = Lists.newArrayList();
        List<Movie> winners = movieRepository.findByWinner(true);

        Map<String, List<Movie>> producers = winners.stream().collect(Collectors.groupingBy(Movie::getProducer));

        producers.forEach((producer, movies) -> {
            movies.sort(Comparator.comparing(Movie::getYear));
            if (movies.size() >= 2 ) {
                    ProducerRazzies razzie = new ProducerRazzies();
                    long previous = movies.get(0).getYear();
                    long current = Iterables.getLast(movies).getYear();
                    razzie.setProducer(producer);
                    razzie.setPreviousAward(previous);
                    razzie.setCurrentAward(current);
                    razzie.setTimeGap(current - previous);
                    razzies.add(razzie);

            }
        });

        List<ProducerRazzies> max = razzies.stream().filter(
                razzie -> razzie.getTimeGap().equals(
                        razzies.stream().max(Comparator.comparing(ProducerRazzies::getTimeGap))
                                .orElseThrow(NoSuchElementException::new).getTimeGap())).collect(Collectors.toList());

        List<ProducerRazzies> min = razzies.stream().filter(
                razzie -> razzie.getTimeGap().equals(
                        razzies.stream().min(Comparator.comparing(ProducerRazzies::getTimeGap))
                                .orElseThrow(NoSuchElementException::new).getTimeGap())).collect(Collectors.toList());

        return new MovieAwardGap(max, min);
    }

}
