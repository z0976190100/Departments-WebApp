package com.depart.project.service.utils;

import com.depart.project.persistense.entity.EmployeeEntityImpl;

import javax.servlet.http.HttpServletRequest;

public class EmployeeBuilder {

    public EmployeeEntityImpl build(HttpServletRequest req) {

        EmployeeEntityImpl newE = new EmployeeEntityImpl(
                req.getParameter("empfname"),
                req.getParameter("emplname"),
                req.getParameter("birthDate"),
                Long.valueOf(req.getParameter("deppid")),
                req.getParameter("emplogin"),
                req.getParameter("emppass"));

        newE.setId(Long.valueOf(req.getParameter("empid")));
        newE.setPass2(req.getParameter("emppass2"));

        return newE;
    }

}
