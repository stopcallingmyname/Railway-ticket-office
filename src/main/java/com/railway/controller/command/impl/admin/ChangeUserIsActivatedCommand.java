package com.railway.controller.command.impl.admin;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.User;
import com.railway.model.service.UserService;
import com.railway.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChangeUserIsActivatedCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserIsActivatedCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String userEmail = requestUtil.getParameterAsString(request, RequestParameter.EMAIL);
            User userToChange = userService.findUserByEmail(userEmail);
//            boolean newUserStatus = userToChange.getIsActivated() ? false : false;
            boolean newUserStatus;
            if (userToChange.getIsActivated())
                newUserStatus = false;
            else
                newUserStatus = true;

            userService.updateIsActive(userToChange, newUserStatus);
            List<User> users = userService.findAll();
            removeCurrentUserFromList(request, users);
            request.setAttribute(RequestAttribute.USERS_LIST, users);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ChangeUserStatusCommand", e);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private void removeCurrentUserFromList(HttpServletRequest request, List<User> users) {
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        users.remove(currentUser);
    }
}
