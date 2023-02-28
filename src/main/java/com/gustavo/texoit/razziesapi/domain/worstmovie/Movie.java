package com.gustavo.texoit.razziesapi.domain.worstmovie;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID", updatable = false)
    private Long id;

    @Column(name = "MOVIE_YEAR")
    private Long year;

    @Column(name = "MOVIE_TITLE")
    private String title;

    @Column(name = "MOVIE_STUDIO")
    private String studio;

    @Column(name = "MOVIE_PRODUCER")
    private String producer;

    @Column(name = "MOVIE_WINNER")
    private Boolean winner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
