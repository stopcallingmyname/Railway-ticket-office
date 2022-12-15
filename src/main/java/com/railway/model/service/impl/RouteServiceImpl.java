package com.railway.model.service.impl;

import com.railway.exception.DaoException;
import com.railway.exception.ServiceException;
import com.railway.model.dao.RouteDAO;
import com.railway.model.dao.impl.RouteDaoImpl;
import com.railway.model.entity.Route;
import com.railway.model.service.RouteService;
import com.railway.model.validator.RouteValidator;
import com.railway.model.validator.TrainValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of RouteService interface.
 */
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LogManager.getLogger(RouteServiceImpl.class);
    private final RouteDAO routeDao = RouteDaoImpl.getInstance();

    private RouteServiceImpl() {

    }

    private static final class RouteServiceInstanceHolder {
        private static final RouteServiceImpl INSTANCE = new RouteServiceImpl();
    }

    /**
     * Gets instance of route service.
     *
     * @return the instance of route service
     */
    public static RouteService getInstance() {
        return RouteServiceImpl.RouteServiceInstanceHolder.INSTANCE;
    }


    @Override
    public void addRoute(Route route) throws ServiceException {
        if (!RouteValidator.getInstance().isValid(route)) {
            logger.error("Invalid route");
            throw new ServiceException("Invalid route");
        }
        try {
            routeDao.add(route);
        } catch (DaoException e) {
            logger.error("Can't handle addRoute request at RouteService", e);
            throw new ServiceException("Can't handle addRoute request at RouteService", e);
        }
    }

    @Override
    public void update(Route route) throws ServiceException {
        if (!RouteValidator.getInstance().isValid(route)) {
            logger.error("Invalid route");
            throw new ServiceException("Invalid route");
        }
        try {
            routeDao.update(route);
        } catch (DaoException e) {
            logger.error("Can't handle update request at RouteService", e);
            throw new ServiceException("Can't handle update request at RouteService", e);
        }
    }

    @Override
    public void deleteRoute(Route route) throws ServiceException {
        if (!RouteValidator.getInstance().isValid(route)) {
            logger.error("Invalid route");
            throw new ServiceException("Invalid route");
        }
        try {
            routeDao.delete(route);
        } catch (DaoException e) {
            logger.error("Can't handle deleteRoute request at RouteService", e);
            throw new ServiceException("Can't handle deleteRoute request at RouteService", e);
        }
    }

    @Override
    public List<Route> findAll() throws ServiceException {
        List<Route> routes;
        try {
            routes = routeDao.findAll();
        } catch (DaoException e) {
            logger.error("Can't handle findAll request at RouteService", e);
            throw new ServiceException("Can't handle findAll request at RouteService", e);
        }
        return routes;
    }

    @Override
    public List<Route> findRouteOfTrainByTrainId(Integer trainId) throws ServiceException {
        return null;
    }

    @Override
    public void addRouteForTrainByTrainId(Integer trainId, Route route) throws ServiceException {

    }

    @Override
    public Route findRouteById(String routeId) throws ServiceException {
        if (routeId == null || routeId.isEmpty()) {
            logger.error("routeId doesn't present");
            throw new ServiceException("routeId doesn't present");
        }
        try {
            Optional<Route> optionalRoute = routeDao.findRouteById(routeId);
            if (optionalRoute.isPresent()) {
                return optionalRoute.get();
            } else {
                logger.error("Movie with id={} not found", routeId);
                throw new ServiceException("Movie with id=" + routeId + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findRouteById request at MovieService", e);
            throw new ServiceException("Can't handle findRouteById request at MovieService", e);
        }
    }

}
