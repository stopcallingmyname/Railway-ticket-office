package com.railway.controller.command.impl.moveto.general;

import com.railway.controller.command.Command;
import com.railway.controller.command.PagePath;
import com.railway.controller.command.RequestAttribute;
import com.railway.controller.command.Router;
import com.railway.exception.ServiceException;
import com.railway.model.entity.Route;
import com.railway.model.service.RouteService;
import com.railway.model.service.impl.RouteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoveToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToMainPageCommand.class);
    private static final RouteService routeService = RouteServiceImpl.getInstance();
    
    @Override
    public Router execute(HttpServletRequest request) {

        try {
           List<Route> routeList = new ArrayList<>();
           routeList.addAll(routeService.findAll());
            request.setAttribute(RequestAttribute.ROUTES_LIST, routeList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToMainPageCommand", e);
        }
        return new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
