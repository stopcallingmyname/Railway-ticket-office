package com.railway.model.dao.impl;

import com.railway.exception.ConnectionPoolException;
import com.railway.exception.DaoException;
import com.railway.model.dao.RouteDAO;
import com.railway.model.entity.Route;
import com.railway.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of RouteDao interface.
 */
public class RouteDaoImpl implements RouteDAO {
    private static final Logger logger = LogManager.getLogger(RouteDAO.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_ROUTE =
            "INSERT INTO routes (id, departureStation, destinationStation) " +
                    "VALUES (?, ?, ?)";

    private static final String SQL_SELECT_ALL_ROUTES =
            "SELECT * FROM routes";

    private static final String SQL_SELECT_ROUTE_BY_ID =
            "SELECT id, departureStation, destinationStation " +
                    "FROM routes " +
                    "WHERE id=?";

    private static final String SQL_DELETE_ROUTE =
            "DELETE FROM routes " +
                    "WHERE id=?";

    private static final String SQL_UPDATE_ROUTE =
            "UPDATE routes SET id=?, departureStation=?, destinationStation=? " +
                    "WHERE id=?";

    private RouteDaoImpl() {

    }

    private static final class MySqlRouteDaoInstanceHolder {
        private static final RouteDAO INSTANCE = new RouteDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of route dao
     */
    public static RouteDAO getInstance() {
        return RouteDaoImpl.MySqlRouteDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Route route) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ROUTE, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, route.getId());
            statement.setString(2, route.getDepartureStation());
            statement.setString(3, route.getDestinationStation());

            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    String key = resultSet.getString(1);
                    route.setId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a route={}", route, e);
            throw new DaoException("Error while adding a route=" + route, e);
        }
    }

    @Override
    public List<Route> findAll() throws DaoException {
        List<Route> routes = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ROUTES);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a routes=", e);
            throw new DaoException("Error while adding a routes", e);
        }
        return routes;
    }

    @Override
    public int update(Route route) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROUTE)
        ) {
            statement.setString(1, route.getId());
            statement.setString(2, route.getDepartureStation());
            statement.setString(3, route.getDestinationStation());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a route", e);
            throw new DaoException("Error while updating a route", e);
        }
    }

    @Override
    public int delete(Route route) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ROUTE)
        ) {
            statement.setString(1, route.getId());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while deleting a route", e);
            throw new DaoException("Error while deleting a route", e);
        }
    }

    @Override
    public Optional<Route> findRouteById(String routeId) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ROUTE_BY_ID)
        ) {
            statement.setString(1, routeId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Route route = createRoute(resultSet);
                    return Optional.of(route);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a route", e);
            throw new DaoException("Error while selecting a route", e);
        }
        return Optional.empty();
    }

    private Route createRoute(ResultSet resultSet) throws SQLException {
        return Route.builder()
                .setRouteId(resultSet.getString(1))
                .setDepartureStation(resultSet.getString(2))
                .setDestinationStation(resultSet.getString(3))
                .build();
    }
}
