package com.railway.model.validator;

import com.railway.model.entity.Train;

/**
 * The class TrainValidator.
 */
public final class TrainValidator implements Validator {
    private static final TrainValidator INSTANCE = new TrainValidator();

    private TrainValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of train validator
     */
    public static TrainValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != Train.class)
            return false;
        Train train = (Train) object;
        return !train.getRouteID().isEmpty()
                && train.getDate() != null
                && train.getSuitReserved() != null
                && train.getCoupeReserved() != null
                && train.getBerthReserved() != null;
    }
}
