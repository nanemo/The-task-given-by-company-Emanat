package com.nadir.task.file;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {
    private Integer id;
    private String stringList;

    public Table() {
    }

    public Table(Integer id, String stringList) {
        this.id = id;
        this.stringList = stringList;
    }

    public Integer getId() {
        return id;
    }

    public Table setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStringList() {
        return stringList;
    }

    public Table setStringList(String stringList) {
        this.stringList = stringList;
        return this;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", stringList=" + stringList +
                '}';
    }
}
