package com.depart.project.persistense.dao;

import java.sql.PreparedStatement;

public interface DAO<T>{

   T selectEntryByProperty(String key);
   PreparedStatement selectEntryById(long id, T entity);
   PreparedStatement selectAll(T entity);
   PreparedStatement selectAllWhere(T entity, String colName, long id);
   PreparedStatement selectAllWhere(T entity, String colName, String flag);
   boolean saveEntry(T entity);
   boolean updateEntry(T entity, long id);
   boolean updateEntryDate(T entity, long id, java.sql.Date date);
   boolean deleteEntry(T entity, long id);
   boolean deleteAllEntriesWhere(T entity, String colName, long id);
   boolean deleteAllEntriesWhere(T entity, String colName, String flag);
   boolean updateEntryColoumnWhereId(T entry, String colName, long id, String newValue);

}
