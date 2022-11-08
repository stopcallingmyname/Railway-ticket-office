package com.railway.controller.command.impl.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class DefaultCommand.
 */
public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
