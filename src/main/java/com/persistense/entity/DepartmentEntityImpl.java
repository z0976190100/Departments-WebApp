package com.persistense.entity;

import java.util.HashMap;
import java.util.Map;

public class DepartmentEntityImpl implements IEntity {


    final  String TABLE_NAME = "department1.department";
    final  String UNIQUE_TITLE = "title";

    public Map<String, String > coloumnValueMap = new HashMap<>();
    public long id;
    public String title; // as UniqueTitle
    public int empQuant;



    public DepartmentEntityImpl(){}

    public DepartmentEntityImpl(String title){
        this.title = title;
        this.coloumnValueMap.put("title", this.title);

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
    public Map getColoumnValueMap() {
        return this.coloumnValueMap;
    }
}
