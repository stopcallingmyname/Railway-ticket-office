package com.railway.controller.command.impl.admin;

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

import java.sql.Date;
import java.sql.Time;

public class AddRouteCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddTrainCommand.class);
    private static final RouteService routeService = RouteServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String routeId = requestUtil.getParameterAsString(request, RequestParameter.ROUTE_ID);
            String departureStation = requestUtil.getParameterAsString(request, RequestParameter.ROUTE_DEPARTURE_STATION);
            String destinationStation = requestUtil.getParameterAsString(request, RequestParameter.ROUTE_DESTINATION_STATION);

            Route route = Route.builder()
                    .setRouteId(routeId)
                    .setDepartureStation(departureStation)
                    .setDestinationStation(destinationStation)
                    .build();
            routeService.addRoute(route);
            request.setAttribute(RequestAttribute.ROUTE, route);
            router = new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddRouteCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
