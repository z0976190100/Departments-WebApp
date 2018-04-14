package com.controller;

import com.service.utils.Validator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParseFilter extends HttpFilter {

    private Validator validator = new Validator();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String uri = req.getRequestURI();

        System.out.println(req.getParameter("birthDate").toString());
        chain.doFilter(req, res);

        switch (uri){
            case("/employee"):
                dateParser(req);

                break;
                default: chain.doFilter(req,res);
        }
    }

    private void dateParser(HttpServletRequest req) {

        String toParse = req.getParameter("birthDate");
        if (validator.isValidDate(toParse)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");

            try {
                LocalDate date = LocalDate.parse(toParse, formatter);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }
    }
}
