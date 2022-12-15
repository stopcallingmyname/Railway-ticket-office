package com.railway.model.validator;

import com.railway.model.entity.Route;

/**
 * The class TrainValidator.
 */
public final class RouteValidator implements Validator {
    private static final RouteValidator INSTANCE = new RouteValidator();

    private RouteValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of route validator
     */
    public static RouteValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != Route.class)
            return false;
        Route route = (Route) object;
        return !route.getId().isEmpty()
                && route.getDepartureStation() != null
                && route.getDestinationStation() != null;
    }
}
