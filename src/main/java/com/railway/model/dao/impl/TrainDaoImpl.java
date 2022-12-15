package com.railway.model.dao.impl;

import com.railway.exception.ConnectionPoolException;
import com.railway.exception.DaoException;
import com.railway.model.dao.TrainDAO;
import com.railway.model.entity.Train;
import com.railway.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of TrainDao interface.
 */
public class TrainDaoImpl implements TrainDAO {

    private static final Logger logger = LogManager.getLogger(TrainDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_TRAIN =
            "INSERT INTO trains (routeID, date, departureTime, destinationTime, suiteReserved, coupeReserved, berthReserved) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_TRAIN =
            "UPDATE trains SET routeID = ?, date = ?, departureTime = ?, destinationTime = ?, suiteReserved = ?, coupeReserved = ?, berthReserved = ? " +
                    "WHERE id=?";

    private static final String SQL_DELETE_TRAIN =
            "DELETE FROM trains " +
                    "WHERE id = ?";

    private static final String SQL_SELECT_TRAIN_BY_ID =
            "SELECT trains.id, routeID, date, departureTime, destinationTime, suiteReserved, coupeReserved, berthReserved " +
                    "FROM trains " +
                    "JOIN routes ON routes.id = trains.routeID " +
                    "WHERE trains.id = ?";

    private static final String SQL_SELECT_TRAIN_BY_ROUTE_ID =
            "SELECT trains.id, routeID, date, departureTime, destinationTime, suiteReserved, coupeReserved, berthReserved " +
                    "FROM trains " +
                    "JOIN routes ON routes.id = trains.routeID " +
                    "WHERE trains.routeID = ?";

    private static final String SQL_SELECT_TRAINS_BY_DATE =
            "SELECT trains.id, routeID, date, departureTime, destinationTime, suiteReserved, coupeReserved, berthReserved " +
                    "FROM trains " +
                    "JOIN routes ON routes.id = trains.routeID " +
                    "WHERE trains.date = ?";

    private TrainDaoImpl() {

    }

    private static final class MySqlTrainDaoInstanceHolder {
        private static final TrainDAO INSTANCE = new TrainDaoImpl();
    }

    public static TrainDAO getInstance() {
        return TrainDaoImpl.MySqlTrainDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Train train) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TRAIN, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, train.getRouteID());
            statement.setDate(2, train.getDate());
            statement.setTime(3, train.getDepartureTime());
            statement.setTime(4, train.getDestinationTime());
            statement.setInt(5, train.getSuitReserved());
            statement.setInt(6, train.getCoupeReserved());
            statement.setInt(7, train.getBerthReserved());

            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Integer key = resultSet.getInt(1);
                    train.setId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a train", e);
            throw new DaoException("Error while adding a train", e);
        }
    }

    @Override
    public int update(Train train) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TRAIN)
        ) {
            statement.setString(1, train.getRouteID());
            statement.setDate(2, train.getDate());
            statement.setTime(3, train.getDepartureTime());
            statement.setTime(4, train.getDestinationTime());
            statement.setInt(5, train.getSuitReserved());
            statement.setInt(6, train.getCoupeReserved());
            statement.setInt(7, train.getBerthReserved());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a train", e);
            throw new DaoException("Error while updating a train", e);
        }
    }

    @Override
    public int delete(Train train) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TRAIN)
        ) {
            statement.setInt(1, train.getId());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while deleting a train", e);
            throw new DaoException("Error while deleting a train", e);
        }
    }

    @Override
    public Optional<Train> findTrainById(Integer trainId) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAIN_BY_ID)
        ) {
            statement.setInt(1, trainId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Train train = createTrain(resultSet);
                    return Optional.of(train);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Train> findTrainsByRouteId(String routeId) throws DaoException {
        List<Train> trains = new ArrayList<>();
//        routeId = "%" + routeId + "%";
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAIN_BY_ROUTE_ID)
        ) {
            statement.setString(1, routeId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trains.add(createTrain(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a train", e);
            throw new DaoException("Error while selecting a train", e);
        }
        return trains;
    }

    @Override
    public List<Train> findTrainsByDate(Date date) throws DaoException {
        List<Train> trains = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAINS_BY_DATE)
        ) {
            statement.setDate(1, date);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trains.add(createTrain(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return trains;
    }

    private Train createTrain(ResultSet resultSet) throws SQLException {
        return Train.builder()
                .setId(resultSet.getInt(1))
                .setRouteId(resultSet.getString(2))
                .setDate(resultSet.getDate(3))
                .setDepartureTime(resultSet.getTime(4))
                .setDestinationTime(resultSet.getTime(5))
                .setSuitReserved(resultSet.getInt(6))
                .setCoupeReserved(resultSet.getInt(7))
                .setBerthReserved(resultSet.getInt(8))
                .build();
    }
}
