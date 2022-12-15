package com.railway.model.service;

import com.railway.exception.ServiceException;
import com.railway.model.entity.Route;
import com.railway.model.entity.Train;

import java.util.List;

/**
 * The interface RouteService provides methods to manage routes of trains.
 */
public interface RouteService {
    /**
     * Add route. Throws ServiceException if genre is null or if genre is presented already or if writing to data source throws the exception.
     *
     * @param route the genre to add
     * @throws ServiceException if genre is null or if genre is presented already or if writing to data source throws the exception
     */
    void addRoute(Route route) throws ServiceException;

    /**
     * Update route. Throws ServiceException if train is null or if writing to data source throws the exception.
     *
     * @param route the updated route
     * @throws ServiceException if train is null or if writing to data source throws the exception
     */
    void update(Route route) throws ServiceException;

    /**
     * Delete train. Throws ServiceException if train is null or if updating of data source throws the exception.
     *
     * @param route the route to delete
     * @throws ServiceException if train is null or if updating of data source throws the exception
     */
    void deleteRoute(Route route) throws ServiceException;

    /**
     * Find all routes. Throws ServiceException if reading from data source throws the exception.
     *
     * @return the list of routes
     * @throws ServiceException if reading from data source throws the exception
     */
    List<Route> findAll() throws ServiceException;

    /**
     * Find route of train by train id. Throws ServiceException if train id is null or if reading from data source throws the exception.
     *
     * @param trainId the train id
     * @return the list of genres
     * @throws ServiceException if route id is null or if reading from data source throws the exception
     */
    List<Route> findRouteOfTrainByTrainId(Integer trainId) throws ServiceException;

    /**
     * Add route for train by train id. Throws ServiceException if train id is null or if route is null or if writing to data source throws the exception.
     *
     * @param trainId the train id
     * @param route   the route
     * @throws ServiceException if train id is null or if genre is null or if writing to data source throws the exception
     */
    void addRouteForTrainByTrainId(Integer trainId, Route route) throws ServiceException;

    /**
     * Find route for train by route id. Throws ServiceException if train id is null or if route is null or if writing to data source throws the exception.
     *
     * @param routeId the movie id
     * @throws ServiceException if route id is null or if genre is null or if writing to data source throws the exception
     */
    Route findRouteById(String routeId) throws ServiceException;
}
