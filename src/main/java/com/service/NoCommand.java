package com.service;

import com.service.Command;
import com.service.utils.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand implements Command {

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);

        return pagePath;
    }
}