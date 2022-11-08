package com.railway.controller.command.impl.general;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.User;
import com.railway.model.service.UserService;
import com.railway.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private static final UserService service = UserServiceImpl.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();

        try {
            String email = requestUtil.getParameterAsString(request, RequestParameter.EMAIL);
            String password = requestUtil.getParameterAsString(request, RequestParameter.PASSWORD);
            User user = service.signIn(email, password);
            if (!user.getIsActivated()) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Oops your account is not activated");
                return new Router(PagePath.LOGIN_PAGE.getAddress(), Router.RouterType.FORWARD);
            }
            setSessionAttributes(request, user);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Wrong login or password");
            router = new Router(PagePath.LOGIN_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private void setSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SessionAttribute.USER, user);
        request.getSession().setAttribute(SessionAttribute.EMAIL, user.getEmail());
        request.getSession().setAttribute(SessionAttribute.ROLE, user.getRole());
    }
}
