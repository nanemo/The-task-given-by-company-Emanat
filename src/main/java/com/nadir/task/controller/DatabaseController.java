package com.nadir.task.controller;

import com.nadir.task.dto.DatabaseDto;
import com.nadir.task.service.Database;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("db")
public class DatabaseController {

    private final Database database;

    public DatabaseController(Database database) {
        this.database = database;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> insertTable(@RequestBody DatabaseDto databaseDto) {
        return ResponseEntity.ok(database.insert(databaseDto.getTableName(), databaseDto.getValues()));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTable(@RequestBody DatabaseDto databaseDto) {
        database.update(databaseDto.getTableName(), databaseDto.getRowId(), databaseDto.getValues());
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getValues(@RequestBody DatabaseDto databaseDto) {
        return ResponseEntity.ok(database.select(databaseDto.getTableName(), databaseDto.getRowId()));
    }

}
