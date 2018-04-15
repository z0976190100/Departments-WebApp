package com.depart.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class RequestRouter {

    private static RequestRouter instance = null;

    public static RequestRouter getInstance() {
        if (instance == null) instance = new RequestRouter();
        return instance;
    }

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestRouter() {
        commands.put("login", new CommandLogin_());
    }

    public Command getCommand(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }


}