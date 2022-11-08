package com.railway.controller.command.impl.moveto.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.Router;
import com.railway.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoveToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToMainPageCommand.class);

//    @Override
//    public Router execute(HttpServletRequest request) {
////        try {
////
////        } catch (ServiceException e) {
////            logger.error("Error at MoveToMainPageCommand", e);
////        }
//        return new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
//    }

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
