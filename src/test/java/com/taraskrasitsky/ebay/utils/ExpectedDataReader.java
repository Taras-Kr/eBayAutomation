package com.taraskrasitsky.ebay.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ExpectedDataReader {
    List<String[]> dataLines;
    String filePath;

    public ExpectedDataReader(String filePath) {
        this.filePath = filePath;
        dataLines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVParser csvParser = new CSVParserBuilder().withSeparator('~').build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(csvParser)
                    .withSkipLines(1)
                    .build();
            dataLines = csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public List<String[]> getDataLines() {
        return dataLines;
    }

    public List<String> getColumn(int columnNumber) {
        List<String> column = new ArrayList<>();
        try {
            if (columnNumber <= 0 || columnNumber > getDataLines().get(0).length) {
                throw new IllegalArgumentException();
            } else {
                column = getDataLines().stream()
                        .map(s -> s[columnNumber - 1])
                        .collect(Collectors.toList());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return column;
    }

}
