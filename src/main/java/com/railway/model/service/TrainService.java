package com.railway.model.service;

import com.railway.exception.ServiceException;
import com.railway.model.entity.Train;

import java.sql.Date;
import java.util.List;

/**
 * The interface TrainService provides methods to manage trains.
 */
public interface TrainService {
    /**
     * Add train. Throws ServiceException if train is null or if writing to data source throws the exception.
     *
     * @param train the train to add
     * @throws ServiceException if train is null or if writing to data source throws the exception
     */
    void addTrain(Train train) throws ServiceException;

    /**
     * Update train. Throws ServiceException if train is null or if writing to data source throws the exception.
     *
     * @param train the updated train
     * @throws ServiceException if train is null or if writing to data source throws the exception
     */
    void update(Train train) throws ServiceException;

    /**
     * Delete train. Throws ServiceException if train is null or if updating of data source throws the exception.
     *
     * @param train the train to delete
     * @throws ServiceException if train is null or if updating of data source throws the exception
     */
    void deleteTrain(Train train) throws ServiceException;

    /**
     * Find train by train id. Throws ServiceException if train id is null or if reading from data source throws the exception.
     *
     * @param trainId the train id
     * @return the train
     * @throws ServiceException if train id is null or if reading from data source throws the exception
     */
    Train findTrainById(Integer trainId) throws ServiceException;

    /**
     * Find trains by route id (search by part of route id). Throws ServiceException if route id is null or empty or if reading from data source throws the exception.
     *
     * @param routeId the route id
     * @return the list of trains
     * @throws ServiceException if route id is null or empty or if reading from data source throws the exception
     */
    List<Train> findTrainsByRouteId(String routeId) throws ServiceException;

    /**
     * Find trains by date. Throws ServiceException if date is null or if reading from data source throws the exception.
     *
     * @param date the date
     * @return the list of trains
     * @throws ServiceException if date is null or if reading from data source throws the exception
     */
    List<Train> findTrainsByDate(Date date) throws ServiceException;
}
