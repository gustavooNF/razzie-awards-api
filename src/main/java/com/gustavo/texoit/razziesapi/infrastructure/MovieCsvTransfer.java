package com.gustavo.texoit.razziesapi.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class MovieCsvTransfer {

    private List<MovieCsvAdapter> csvList;

    public MovieCsvTransfer() {}

    public void setCsvList(List<MovieCsvAdapter> csvList) {
        this.csvList = csvList;
    }

    public List<MovieCsvAdapter> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<>();
    }
}