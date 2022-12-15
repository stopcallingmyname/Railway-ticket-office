package com.railway.model.service.impl;

import com.railway.exception.DaoException;
import com.railway.exception.ServiceException;
import com.railway.model.dao.TrainDAO;
import com.railway.model.dao.impl.TrainDaoImpl;
import com.railway.model.entity.Train;
import com.railway.model.service.TrainService;
import com.railway.model.validator.TrainValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of TrainService interface.
 */
public class TrainServiceImpl implements TrainService {
    private static final Logger logger = LogManager.getLogger(TrainServiceImpl.class);
    private final TrainDAO trainDao = TrainDaoImpl.getInstance();

    private TrainServiceImpl() {

    }

    private static final class TrainServiceInstanceHolder {
        private static final TrainServiceImpl INSTANCE = new TrainServiceImpl();
    }

    /**
     * Gets instance of train service.
     *
     * @return the instance of train service
     */
    public static TrainService getInstance() {
        return TrainServiceImpl.TrainServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addTrain(Train train) throws ServiceException {
        if (!TrainValidator.getInstance().isValid(train)) {
            logger.error("Invalid train");
            throw new ServiceException("Invalid train");
        }
        try {
            trainDao.add(train);
        } catch (DaoException e) {
            logger.error("Can't handle addTrain request at TrainService", e);
            throw new ServiceException("Can't handle addTrain request at TrainService", e);
        }
    }

    @Override
    public void update(Train train) throws ServiceException {
        if (!TrainValidator.getInstance().isValid(train)) {
            logger.error("Invalid train");
            throw new ServiceException("Invalid train");
        }
        try {
            trainDao.update(train);
        } catch (DaoException e) {
            logger.error("Can't handle update request at TrainService", e);
            throw new ServiceException("Can't handle update request at TrainService", e);
        }
    }

    @Override
    public void deleteTrain(Train train) throws ServiceException {
        if (!TrainValidator.getInstance().isValid(train)) {
            logger.error("Invalid train");
            throw new ServiceException("Invalid train");
        }
        try {
            trainDao.delete(train);
        } catch (DaoException e) {
            logger.error("Can't handle deleteTrain request at TrainService", e);
            throw new ServiceException("Can't handle deleteTrain request at TrainService", e);
        }
    }

    @Override
    public Train findTrainById(Integer trainId) throws ServiceException {
        if (trainId == null) {
            logger.error("trainId doesn't present");
            throw new ServiceException("trainId doesn't present");
        }
        try {
            Optional<Train> optionalTrain = trainDao.findTrainById(trainId);
            if (optionalTrain.isPresent()) {
                return optionalTrain.get();
            } else {
                logger.error("Train with id={} not found", trainId);
                throw new ServiceException("Train with id=" + trainId + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findTrainById request at TrainService", e);
            throw new ServiceException("Can't handle findTrainById request at TrainService", e);
        }
    }

    @Override
    public List<Train> findTrainsByRouteId(String routeId) throws ServiceException {
        if (routeId == null || routeId.isEmpty()) {
            logger.error("route id doesn't present");
            throw new ServiceException("route id doesn't present");
        }
        List<Train> trains;
        try {
            trains = trainDao.findTrainsByRouteId(routeId);
        } catch (DaoException e) {
            logger.error("Can't handle findTrainsByRouteId request at TrainService", e);
            throw new ServiceException("Can't handle findTrainsByRouteId request at TrainService", e);
        }
        return trains;
    }

    @Override
    public List<Train> findTrainsByDate(Date date) throws ServiceException {
        if (date == null) {
            logger.error("Date doesn't present");
            throw new ServiceException("Date doesn't present");
        }
        List<Train> trains;
        try {
            trains = trainDao.findTrainsByDate(date);
        } catch (DaoException e) {
            logger.error("Can't handle findTrainsByDate request at TrainService", e);
            throw new ServiceException("Can't handle findTrainsByDate request at TrainService", e);
        }
        return trains;
    }
}
