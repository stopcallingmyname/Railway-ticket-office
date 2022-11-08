package com.railway.controller.command.impl.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class SignOutCommand.
 */
public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        deleteSession(request);
        createNewSession(request);
        return new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.REDIRECT);
    }

    private void deleteSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    private void createNewSession(HttpServletRequest request) {
        request.getSession();
    }
}
