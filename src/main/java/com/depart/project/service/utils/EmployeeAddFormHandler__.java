package com.depart.project.service.utils;

import java.util.HashMap;

public class EmployeeAddFormHandler__ {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String login;
    private String pass1;
    private String pass2;
    private HashMap<String, String > errors;
    private Validator validator = new Validator();

    public EmployeeAddFormHandler__() {
        this.firstName="";
        this.lastName="";
        this.login ="";
        this.pass1 ="";
        this.pass2 ="";
        this.errors = new HashMap<String, String>();
    }


    public boolean validate() {
        boolean success=true;
        if (firstName.equals("")) {
            errors.put("firstName",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));
            success=false;
        }
        if (lastName.equals("")) {
            errors.put("lastName",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));
            success=false;
        }
        if (!validator.isValidEmail(login)) {
            errors.put("login",
                    MessageManager.getInstance().getProperty(MessageManager.INVALID_EMAIL_MESSAGE));
            success=false;
        }

        // pass validations ?
        if (pass1.equals("") ) {
            errors.put("pass1",
                    MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));
            success=false;
        }
        if (!pass1.equals("") && (pass2.equals("") ||
                !pass1.equals(pass2))) {
            errors.put("pass2",
                    MessageManager.getInstance().getProperty(MessageManager.FAILED_PASS_CONFIRMATION_MESSAGE));
            success=false;
        }
        return success;
    }

    public String getErrorMsg(String s) {
        String errorMsg =(String)errors.get(s.trim());
        return (errorMsg == null) ? "":errorMsg;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
