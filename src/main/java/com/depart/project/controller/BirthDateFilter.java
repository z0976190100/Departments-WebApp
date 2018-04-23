package com.depart.project.controller;

import com.depart.project.service.utils.Validator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthDateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getParameter("birthDate") != null) {
            Validator validator = new Validator();
            HttpServletRequest req = (HttpServletRequest) request;

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


}
