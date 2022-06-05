package com.nadir.task.service.impl;

import com.nadir.task.exception.NotFoundException;
import com.nadir.task.file.Table;
import com.nadir.task.service.Database;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DatabaseImpl implements Database {
    @Value("${file.directory}")
    private String fileDirectory;


    @Override
    public int insert(String tableName, List<String> values) {
        String collect = String.join(",", values);
        List<Table> tables = null;
        try {
            tables = getTable(tableName);
        } catch (FileNotFoundException e) {
            System.out.println("Bu adda cedvel tapilmadi yenisi yaradilir");
        }
        if (tables != null) {
            tables.add(new Table(tables.size() + 1, collect));
        } else {
            tables = new ArrayList<>();
            tables.add(new Table(1, collect));
        }
        writeToCSV(tableName, tables);
        return tables.size();
    }

    @Override
    public void update(String tableName, int rowId, List<String> values) {
        List<Table> tables;
        try {
            tables = getTable(tableName);
        } catch (FileNotFoundException e) {
            throw new NotFoundException(e.getMessage(), 404);
        }
        Map<Integer, Table> collect = tables.stream().collect(Collectors.toMap(Table::getId, Function.identity()));
        Table table = collect.get(rowId);
        if (table == null) {
            throw new NotFoundException("Cannot Find ROW", 404);
        } else {
            table.setStringList(String.join(",", values));
        }
        tables.set(rowId - 1, table);
        writeToCSV(tableName, tables);
    }

    @Override
    public List<String> select(String tableName, int rowId) {
        List<Table> tables = null;
        try {
            tables = getTable(tableName);
        } catch (FileNotFoundException e) {
            throw new NotFoundException(e.getMessage(), 404);
        }
        if (rowId > tables.size()) {
            throw new NotFoundException("NOT FOUND ROW", 404);
        }
        return Arrays.asList(tables.get(rowId - 1).getStringList().split(","));
    }

    private String tableNameGenerator(String tableName) {
        return String.format("%s%s.csv", fileDirectory, tableName);
    }

    private List<Table> getTable(String tableName) throws FileNotFoundException {
        return new CsvToBeanBuilder<Table>(new FileReader(tableNameGenerator(tableName)))
                .withType(Table.class)
                .build()
                .parse();
    }

    private void writeToCSV(String tableName, List<Table> tables) {
        try (Writer writer = new FileWriter(tableNameGenerator(tableName))) {
            StatefulBeanToCsv<Table> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Table>(writer).build();
            statefulBeanToCsv.write(tables);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
