package com.depart.project.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {


        public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
