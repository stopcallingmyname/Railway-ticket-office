package com.railway.model.dao;

import com.railway.exception.DaoException;
import com.railway.model.entity.User;
import java.util.Optional;
import java.util.List;

/**
 * Interface describes the behavior of {@link User} entity
 *
 * @author Khaletsky N.V.
 */
public interface UserDAO {
    /**
     * Add user. Throws DaoException if writing to data source throws exception.
     *
     * @param user           the user to add
     * @param hashedPassword the hashed password
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(User user, String hashedPassword) throws DaoException;

    /**
     * Update user. Throws DaoException if writing to data source throws exception.
     *
     * @param user the updated user
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int update(User user) throws DaoException;

    /**
     * Find user by login. Throws DaoException if reading of data source throws exception.
     *
     * @param email the email
     * @return the optional of user
     * @throws DaoException if reading of data source throws exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find user password by email. Throws DaoException if reading of data source throws exception.
     *
     * @param email the email
     * @return the optional of user
     * @throws DaoException if reading of data source throws exception
     */
    Optional<String> findUserPasswordByEmail(String email) throws DaoException;

    /**
     * Find all users. Throws DaoException if reading of data source throws exception.
     *
     * @return the list of users
     * @throws DaoException if reading of data source throws exception
     */
    List<User> findAll() throws DaoException;
}
