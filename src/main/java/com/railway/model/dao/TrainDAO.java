package com.railway.model.dao;

import com.railway.exception.DaoException;
import com.railway.model.entity.Train;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Train} entity
 *
 * @author Khaletsky N.V.
 */
public interface TrainDAO {
    /**
     * Add train. Throws DaoException if writing to data source throws exception.
     *
     * @param train the train to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Train train) throws DaoException;

    /**
     * Update train. Throws DaoException if updating of data source throws exception.
     *
     * @param train the updated train
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Train train) throws DaoException;

    /**
     * Delete train. Throws DaoException if updating of data source throws exception.
     *
     * @param train the train to delete
     * @throws DaoException if updating of data source throws exception
     */
    int delete(Train train) throws DaoException;

    /**
     * Find train by id. Throws DaoException if reading of data source throws exception.
     *
     * @param trainId the train id
     * @return the optional of train
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Train> findTrainById(Integer trainId) throws DaoException;

    /**
     * Find trains by route id. Throws DaoException if reading of data source throws exception.
     *
     * @param routeId the route id
     * @return the list of trains
     * @throws DaoException if reading of data source throws exception
     */
    List<Train> findTrainsByRouteId(String routeId) throws DaoException;

    /**
     * Find trains by date. Throws DaoException if reading of data source throws exception.
     *
     * @param date the date
     * @return the list of trains
     * @throws DaoException if reading of data source throws exception
     */
    List<Train> findTrainsByDate(Date date) throws DaoException;
}
