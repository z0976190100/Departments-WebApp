package com.depart.project.service.utils;


import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.persistense.entity.IEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isExist(IEntity entity, String colName, String flag) {

        DAOGenericImpl actor = new DAOGenericImpl();

        try (PreparedStatement ps = actor.selectAllWhere(entity, colName, flag);
             ResultSet rs = ps.getResultSet()) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isValidEmail(String toValidate) {

        Pattern emailRegexp = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegexp.matcher(toValidate);
        return matcher.find();

    }

    public boolean isValidDate(String toValidate) {

        toValidate = toValidate.trim();

        try {
            LocalDate date = LocalDate.parse(toValidate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public boolean employeeFormValidate(Map<String, String> respMess, EmployeeEntityImpl newE) {

        // TODO for updating check if login and pass not changed, say "formValid=true", but if it changes, check if unique in DB

        boolean formValid = true;

        if (!isValidEmail(newE.getLogin())) {
            respMess.put("INVALID_EMAIL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.INVALID_EMAIL_MESSAGE));
            formValid = false;
        }

        if (isExist(newE, "login", newE.getLogin())) {
            respMess.put("LOGIN_SAVE_PROBLEM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.LOGIN_SAVE_PROBLEM_MESSAGE));
            formValid = false;
        }

        if (!newE.getPass().equals(newE.getPass2())) {
            respMess.put("FAILED_PASS_CONFIRMATION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.FAILED_PASS_CONFIRMATION_MESSAGE));
            formValid = false;
        }

        return formValid;
    }

}
