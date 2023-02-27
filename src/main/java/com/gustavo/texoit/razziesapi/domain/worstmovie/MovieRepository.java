package com.gustavo.texoit.razziesapi.domain.worstmovie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByWinner(Boolean winner);
}
