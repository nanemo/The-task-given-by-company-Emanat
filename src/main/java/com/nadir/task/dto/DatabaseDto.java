package com.nadir.task.dto;

import java.io.Serializable;
import java.util.List;

public class DatabaseDto implements Serializable {
    private String tableName;
    private Integer rowId;
    private List<String> values;

    public DatabaseDto() {
    }

    public DatabaseDto(String tableName, Integer rowId, List<String> values) {
        this.tableName = tableName;
        this.rowId = rowId;
        this.values = values;
    }

    public Integer getRowId() {
        return rowId;
    }

    public DatabaseDto setRowId(Integer rowId) {
        this.rowId = rowId;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public DatabaseDto setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public List<String> getValues() {
        return values;
    }

    public DatabaseDto setValues(List<String> values) {
        this.values = values;
        return this;
    }
}
