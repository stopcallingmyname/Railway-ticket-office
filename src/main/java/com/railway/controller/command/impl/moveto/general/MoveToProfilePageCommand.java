package com.railway.controller.command.impl.moveto.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import com.railway.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public class MoveToProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.PROFILE_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
