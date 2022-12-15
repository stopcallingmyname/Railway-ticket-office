package com.railway.model.service;

import com.railway.exception.ServiceException;
import com.railway.model.entity.User;
import com.railway.model.entity.UserRole;

import java.util.List;

/**
 * The interface UserService provides methods to manage users.
 */
public interface UserService {
    /**
     * Sign in user. Throws ServiceException if params is null or empty or if user with specified params does not exist or if reading from data source throws exception.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     * @throws ServiceException if params is null or empty or if user with specified params does not exist or if reading from data source throws exception
     */
    User signIn(String email, String password) throws ServiceException;

    /**
     * Sign up user. Throws ServiceException if params is null or empty or if writing to data source throws exception.
     *
     * @param email            the email
     * @param name             the name
     * @param surname          the surname
     * @param phone            the phone
     * @param password         the password
     * @param repeatedPassword the repeated password
     * @return the user
     * @throws ServiceException if params is null or empty or if writing to data source throws exception
     */
    User signUp(String email, String name, String surname, String phone, String password, String repeatedPassword) throws ServiceException;

    /**
     * Update status of user. Throws ServiceException if params is null or empty or if writing to data source throws exception.
     *
     * @param user       the user
     * @param is_active the user activation status
     * @return the updated user
     * @throws ServiceException if params is null or empty or if writing to data source throws exception
     */
    User updateIsActive(User user, boolean is_active) throws ServiceException;

    /**
     * Update role of user. Throws ServiceException if params is null or empty or if writing to data source throws exception.
     *
     * @param user     the user
     * @param userRole the user role
     * @return the updated user
     * @throws ServiceException if params is null or empty or if writing to data source throws exception
     */
    User updateRole(User user, UserRole userRole) throws ServiceException;

    /**
     * Find user by user email. Throws ServiceException if login is null or if user does not exist or if reading of data source throws exception.
     *
     * @param email the email
     * @return the user
     * @throws ServiceException if email is null or if user does not exist or if reading of data source throws exception
     */
    User findUserByEmail(String email) throws ServiceException;

    /**
     * Find all users. Throws ServiceException if reading of data source throws exception.
     *
     * @return the list of users
     * @throws ServiceException if reading of data source throws exception
     */
    List<User> findAll() throws ServiceException;
}
