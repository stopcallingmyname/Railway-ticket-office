package com.railway.controller.command;

import com.railway.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Scanner;

/**
 * The class RequestUtil.
 */
public class RequestUtil {
    private static final Logger logger = LogManager.getLogger(RequestUtil.class);
    private static final RequestUtil INSTANCE = new RequestUtil();

    private RequestUtil() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of request util.
     */
    public static RequestUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Gets parameter as long. Throws ServiceException if parameter is invalid.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as long
     * @throws ServiceException if parameter is invalid
     */
    public Long getParameterAsLong(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try (Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextLong()) {
                    return Long.valueOf(parameter.get());
                } else {
                    logger.error("Invalid parameter");
                    throw new ServiceException("Invalid parameter");
                }
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    /**
     * Gets parameter as int. Throws ServiceException if parameter is invalid.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as int
     * @throws ServiceException if parameter is invalid
     */
    public int getParameterAsInt(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try(Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextInt()) {
                    return Integer.parseInt(parameter.get());
                } else {
                    logger.error("Invalid parameter");
                    throw new ServiceException("Invalid parameter");
                }
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    /**
     * Gets parameter as string. Throws ServiceException is parameter is null or empty.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as string
     * @throws ServiceException if parameter is null or empty
     */
    public String getParameterAsString(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (!parameter.isPresent()) {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
        return parameter.get();
    }

    /**
     * Gets parameter as date. Throws ServiceException is parameter is null or empty.
     *
     * @param request       the request
     * @return the parameter as string
     * @throws ServiceException if parameter is null or empty
     */
    public Date getParameterAsDate(HttpServletRequest request) throws ServiceException {

        Optional<String> parameter = getParameter(request, RequestParameter.TRAIN_DATE);
        if (parameter.isPresent()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                long date = sdf.parse(parameter.get()).getTime();
                return new Date(date);
            } catch (ParseException | IllegalArgumentException e) {
                logger.error("Invalid parameter");
                logger.error(e);
                throw new ServiceException("Invalid parameter");
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    /**
     * Gets parameter as time. Throws ServiceException is parameter is null or empty.
     *
     * @param request       the request
     * @return the parameter as string
     * @throws ServiceException if parameter is null or empty
     */
    public Time getParameterAsTime(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            try {
                long time = sdf.parse(parameter.get()).getTime();
                return new Time(time);
            }
            catch (IllegalArgumentException e) {
                logger.error("Invalid parameter");
                logger.error(e);
                throw new ServiceException("Invalid parameter");
            } catch (ParseException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        }
        else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    private Optional<String> getParameter(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        if (parameter == null) {
            return Optional.empty();
        } else {
            return Optional.of(parameter);
        }
    }
}
