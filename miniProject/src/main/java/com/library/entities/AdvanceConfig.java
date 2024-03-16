package com.library.entities;

import jakarta.persistence.*;

@Entity

public class AdvanceConfig {
    @Id
    private int id;
    @Column(length = 2)
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AdvanceConfigRepository{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
