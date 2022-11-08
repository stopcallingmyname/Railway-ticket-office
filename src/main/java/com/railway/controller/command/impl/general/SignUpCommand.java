package com.railway.controller.command.impl.general;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.User;
import com.railway.model.service.UserService;
import com.railway.model.service.impl.UserServiceImpl;
import com.railway.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * The class SignUpCommand.
 */
public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Map<String, String>  signupParameters = getSignupParameters(request);
        if (!isParametersValid(signupParameters)) {
            setSignupParameters(request, signupParameters);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Wrong data");
            return new Router(PagePath.REGISTER_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        try {
            User user = signUpUser(signupParameters);
//            EmailSenderUtil.getInstance().sendAuthenticationMessage(user);
            request.getSession().setAttribute(SessionAttribute.USER, user);
//            router = new Router(PagePath.VERIFY_EMAIL_PAGE.getAddress(), Router.RouterType.FORWARD);
            router = new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
//        } catch (ServiceException | MessagingException | IOException e) {
        } catch (ServiceException e) {
            logger.error("Error at SignUpCommand", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Wrong data");
            setSignupParameters(request, signupParameters);
            router = new Router(PagePath.REGISTER_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private User signUpUser(Map<String, String> signupParameters) throws ServiceException {
        return userService.signUp (
                signupParameters.get(RequestParameter.EMAIL),
                signupParameters.get(RequestParameter.FIRST_NAME),
                signupParameters.get(RequestParameter.SURNAME),
                signupParameters.get(RequestParameter.PHONE),
                signupParameters.get(RequestParameter.PASSWORD),
                signupParameters.get(RequestParameter.REPEATED_PASSWORD)
        );
    }

    private Map<String, String> getSignupParameters(HttpServletRequest request) {
        Map<String, String> signUpParameters = new HashMap<>();
        signUpParameters.put(RequestParameter.EMAIL, request.getParameter(RequestParameter.EMAIL));
        signUpParameters.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        signUpParameters.put(RequestParameter.FIRST_NAME, request.getParameter(RequestParameter.FIRST_NAME));
        signUpParameters.put(RequestParameter.SURNAME, request.getParameter(RequestParameter.SURNAME));
        signUpParameters.put(RequestParameter.PHONE, request.getParameter(RequestParameter.PHONE));
        signUpParameters.put(RequestParameter.REPEATED_PASSWORD, request.getParameter(RequestParameter.REPEATED_PASSWORD));
        return signUpParameters;
    }

    private boolean isParametersValid(Map<String, String> signupParameters) {
        boolean flag = true;
        for (Map.Entry<String, String> entry : signupParameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                signupParameters.put(entry.getKey(), "");
                flag = false;
            }
        }

        if (isEmailPresence(signupParameters.get(RequestParameter.EMAIL))) {
            signupParameters.put(RequestParameter.EMAIL, "");
            flag = false;
        }
        if (!signupParameters.get(RequestParameter.PASSWORD).equals(signupParameters.get(RequestParameter.REPEATED_PASSWORD))) {
            signupParameters.put(RequestParameter.PASSWORD, "");
            signupParameters.put(RequestParameter.REPEATED_PASSWORD, "");
            flag = false;
        }
        if (!UserValidator.getInstance().isEmailValid(signupParameters.get(RequestParameter.EMAIL))) {
            signupParameters.put(RequestParameter.EMAIL, "");
            flag = false;
        }

        return flag;
    }

    private boolean isEmailPresence(String email) {
        try {
            userService.findUserByEmail(email);
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    private void setSignupParameters(HttpServletRequest request, Map<String, String> signupParameters) {
        signupParameters.forEach(request::setAttribute);
    }
}
