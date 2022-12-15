package com.railway.controller.command.impl.general;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.Route;
import com.railway.model.entity.Train;
import com.railway.model.service.RouteService;
import com.railway.model.service.TrainService;
import com.railway.model.service.impl.RouteServiceImpl;
import com.railway.model.service.impl.TrainServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class GetRouteCommand.
 */
public class GetRouteCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetRouteCommand.class);
    private static final RouteService routeService = RouteServiceImpl.getInstance();
    private static final TrainService trainService = TrainServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String routeId = requestUtil.getParameterAsString(request, RequestParameter.ROUTE_ID);
            Route route = routeService.findRouteById(routeId);
            List<Train> trainsList = trainService.findTrainsByRouteId(routeId);
            request.setAttribute(RequestAttribute.TRAINS_LIST, trainsList);
            request.setAttribute(RequestAttribute.ROUTE, route);
            router = new Router(PagePath.GET_ROUTE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetRouteCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
