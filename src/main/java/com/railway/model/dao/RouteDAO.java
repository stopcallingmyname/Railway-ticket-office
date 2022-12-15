package com.railway.model.dao;

import com.railway.exception.DaoException;
import com.railway.model.entity.Route;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Route} entity
 *
 * @author Khaletsky N.V.
 */
public interface RouteDAO {
    /**
     * Add route. Throws DaoException if writing to data source throws exception.
     *
     * @param route the route to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Route route) throws DaoException;

    /**
     * Find all routes. Throws DaoException if reading from data source throws exception.
     *
     * @return the list of routes
     * @throws DaoException if reading from data source throws exception
     */
    List<Route> findAll() throws DaoException;

    /**
     * Update route. Throws DaoException if updating of data source throws exception.
     *
     * @param route the updated route
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Route route) throws DaoException;


    /**
     * Delete route. Throws DaoException if updating of data source throws exception.
     *
     * @param route the route to delete
     * @throws DaoException if updating of data source throws exception
     * @return
     */
    int delete(Route route) throws DaoException;

    /**
     * Find route by id. Throws DaoException if reading of data source throws exception.
     *
     * @param routeId the route id
     * @return the optional of route
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Route> findRouteById(String routeId) throws DaoException;

}
