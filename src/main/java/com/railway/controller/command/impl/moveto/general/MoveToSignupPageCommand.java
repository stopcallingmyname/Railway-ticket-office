package com.railway.controller.command.impl.moveto.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class MoveToSignupPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.REGISTER_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
