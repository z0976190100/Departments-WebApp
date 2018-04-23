package com.depart.project.persistense.entity;

import java.sql.Date;
import java.util.Map;

public interface IEntity {

    String getTableName();
    String getUniqueTitle();
    Map<String, String> getColoumnValueMap();
    Date getBirthDate();


}
