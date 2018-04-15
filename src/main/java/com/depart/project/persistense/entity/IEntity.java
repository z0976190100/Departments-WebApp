package com.depart.project.persistense.entity;

import java.util.Map;

public interface IEntity {

    String getTableName();
    String getUniqueTitle();
    Map getColoumnValueMap();


}
