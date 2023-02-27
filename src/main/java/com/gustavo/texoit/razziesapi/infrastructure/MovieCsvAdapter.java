package com.gustavo.texoit.razziesapi.infrastructure;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCsvAdapter {

    @CsvBindByName(column = "year")
    private Long year;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "studios")
    private String studio;

    @CsvBindByName(column = "producers")
    private String producer;

    @CsvBindByName(column = "winner")
    private Boolean winner;
}
