package com.nhom3.zoomanagement.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String testNotNullField;

    @Column()
    private String nullableField;

    @Column(unique = true)
    private String uniqueField;

    @OneToMany
    private List<TestNY> testNYList;

    public List<TestNY> getTestNYList() {
        return testNYList;
    }

    public void setTestNYList(List<TestNY> testNYList) {
        this.testNYList = testNYList;
    }

    public String getUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(String uniqueField) {
        this.uniqueField = uniqueField;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestNotNullField() {
        return testNotNullField;
    }

    public void setTestNotNullField(String testNotNullField) {
        this.testNotNullField = testNotNullField;
    }

    public String getNullableField() {
        return nullableField;
    }

    public void setNullableField(String nullableField) {
        this.nullableField = nullableField;
    }


}
