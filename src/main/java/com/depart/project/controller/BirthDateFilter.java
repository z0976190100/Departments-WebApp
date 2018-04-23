package com.depart.project.controller;

import com.depart.project.service.utils.Validator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;



public class BirthDateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();*/


    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        setDateBoundry(req);

        if (request.getParameter("birthDate") != null) {
            Validator validator = new Validator();

            String bdate = req.getParameter("birthDate");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date bDate = formatter.parse(bdate);
                if (validator.isValidDate(bDate)){
                    java.sql.Date sqlDate = new java.sql.Date(bDate.getTime());
                    req.setAttribute("birthDate", sqlDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // TODO message WRONG DATE FORMAT
            }
        }
        chain.doFilter(request, response);
    }

    private void setDateBoundry(HttpServletRequest req){

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        LocalDate lowestDateBoundry = date.minusYears(100);
        String minDate = dateFormat.format(lowestDateBoundry);
        LocalDate higestDateBoundry = date.minusYears(18);
        String maxDate = dateFormat.format(higestDateBoundry);

        req.setAttribute("minDate", minDate);
        req.setAttribute("maxDate", maxDate);

    }

}
