package com.railway.controller.command.impl.general;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.Route;
import com.railway.model.service.RouteService;
import com.railway.model.service.impl.RouteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class GetRoutesListCommand.
 */
public class GetRoutesListCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetRoutesListCommand.class);
    private static final RouteService routeService = RouteServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            List<Route> routes = routeService.findAll();
            request.setAttribute(RequestAttribute.ROUTES_LIST, routes);
            router = new Router(PagePath.GET_ROUTES_LIST_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetRoutesListCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
