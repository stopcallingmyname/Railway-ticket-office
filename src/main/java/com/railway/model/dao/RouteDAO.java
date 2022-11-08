package com.railway.model.dao;

import com.railway.model.entity.Route;

public interface RouteDAO {
    /* Find the route by departure and
     * destination stations, departure
     * time.
     */
    Iterable<Route> findRoutes(Route route);
}
