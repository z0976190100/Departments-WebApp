package com.depart.project.persistense.entity;

import java.util.HashMap;
import java.util.Map;

public class DepartmentEntityImpl implements IEntity {


    final  String TABLE_NAME = "department1.department2";
    final  String UNIQUE_TITLE = "title";

    private Map<String, String > coloumnValueMap = new HashMap<>();
    private long id;
    private String title; // as UniqueTitle
    private int empQuant;



    public DepartmentEntityImpl(){}

    public DepartmentEntityImpl(String title){
        this.title = title;
        this.coloumnValueMap.put("title", this.title);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmpQuant() {
        return empQuant;
    }

    public void setEmpQuant(int empQuant) {
        this.empQuant = empQuant;
    }

    @Override
    public String getTableName() {
        return this.TABLE_NAME;
    }

    @Override
    public String getUniqueTitle() {
        return this.UNIQUE_TITLE;
    }

    @Override
    public Map<String, String> getColoumnValueMap() {
        return this.coloumnValueMap;
    }
}
