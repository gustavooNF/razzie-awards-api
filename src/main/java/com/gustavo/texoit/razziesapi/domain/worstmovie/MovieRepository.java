package com.gustavo.texoit.razziesapi.domain.worstmovie;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @ApiResponse(description = "Busca todos os filmes vencedores")
    List<Movie> findByWinner(Boolean winner);

}
