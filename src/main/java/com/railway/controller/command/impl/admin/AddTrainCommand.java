package com.railway.controller.command.impl.admin;

import com.railway.controller.command.*;
import com.railway.exception.ServiceException;
import com.railway.model.entity.Train;
import com.railway.model.service.TrainService;
import com.railway.model.service.impl.TrainServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;

/**
 * The class AddTrainCommand.
 */
public class AddTrainCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddTrainCommand.class);
    private static final TrainService trainService = TrainServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String routeId = requestUtil.getParameterAsString(request, RequestParameter.TRAIN_ROUTE_ID);
            Date date = requestUtil.getParameterAsDate(request);
            Time departureTime = requestUtil.getParameterAsTime(request, RequestParameter.TRAIN_DEPARTURE_TIME);
            Time destinationTime = requestUtil.getParameterAsTime(request, RequestParameter.TRAIN_DESTINATION_TIME);
            Integer suitReserved = requestUtil.getParameterAsInt(request, RequestParameter.TRAIN_SUIT_RESERVED);
            Integer coupeReserved = requestUtil.getParameterAsInt(request, RequestParameter.TRAIN_COUPE_RESERVED);
            Integer berthReserved = requestUtil.getParameterAsInt(request, RequestParameter.TRAIN_BERTH_RESERVED);
            Train train = Train.builder()
                    .setRouteId(routeId)
                    .setDate(date)
                    .setDepartureTime(departureTime)
                    .setDestinationTime(destinationTime)
                    .setSuitReserved(suitReserved)
                    .setCoupeReserved(coupeReserved)
                    .setBerthReserved(berthReserved)
                    .build();
            trainService.addTrain(train);
            request.setAttribute(RequestAttribute.TRAIN, train);
            router = new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddTrainCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
