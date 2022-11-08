package com.railway.model.dao.impl;

import com.railway.exception.ConnectionPoolException;
import com.railway.exception.DaoException;
import com.railway.model.entity.User;
import com.railway.model.entity.UserRole;
import com.railway.model.pool.CustomConnectionPool;
import com.railway.model.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserDaoImpl implements UserDAO {

    private static final Logger logger = (Logger)LogManager.getLogger(UserDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_USER =
            "INSERT INTO users (email, password, first_name, surname, phone, is_activated, role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_USER =
            "UPDATE users " +
                    "SET email = ?, first_name = ?, surname = ?, phone = ?, is_activated = ?, role_id = ? " +
                    "WHERE id = ?";

    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, email, first_name, surname, phone, is_activated, roles.name AS role_type " +
                    "FROM users " +
                    "JOIN roles ON roles.role_id = users.role_id " +
                    "WHERE users.id = ?";

    private static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT users.id, email, first_name, surname, phone, is_activated, roles.name as role_type " +
                    "FROM users " +
                    "JOIN roles ON roles.role_id = users.role_id " +
                    "WHERE email = ?";

    private static final String SQL_SELECT_USER_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE email = ?";

    private static final String SQL_SELECT_USERS_BY_STATUS =
            "SELECT id, email, first_name, surname, phone, is_activated, roles.name AS role_type " +
                    "FROM users " +
                    "JOIN roles ON roles.role_id = users.role_id " +
                    "WHERE is_activated = ?";

    private static final String SQL_SELECT_USERS_BY_ROLE =
            "SELECT id, email, first_name, surname, phone, is_activated, roles.name AS role_type " +
                    "FROM users " +
                    "JOIN roles ON roles.role_id = users.role_id " +
                    "WHERE roles.name = ?";


    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, email, first_name, surname, phone, is_activated, roles.name AS role_type " +
                    "FROM users " +
                    "JOIN roles ON roles.role_id = users.role_id";

    @Override
    public int add(User user, String hashedPassword) throws DaoException {
        try (
            Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, hashedPassword);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, user.getIsActivated());
            statement.setInt(7, user.getRole().getId());

            int rowsUpdate = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Integer key = resultSet.getInt(1);
                    user.setId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a user={}", user, e);
            throw new DaoException("Error while adding a user=" + user + e);
        }
    }

    @Override
    public int update(User user) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPhone());
            statement.setBoolean(5, user.getIsActivated());
            statement.setLong(6, user.getRole().getId());
            statement.setInt(7, user.getId());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a user={}", user, e);
            throw new DaoException("Error while updating a user=" + user, e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)
        ) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(createUser(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a user", e);
            throw new DaoException("Error while selecting a user", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findUserPasswordByEmail(String email) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_PASSWORD_BY_LOGIN)
        ) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString(1);
                    return Optional.of(password);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a password", e);
            throw new DaoException("Error while selecting a password", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a users", e);
            throw new DaoException("Error while selecting a users", e);
        }
        return users;

    }

    private static final class MySqlUserDaoInstanceHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }


    public static UserDaoImpl getInstance() {
        return MySqlUserDaoInstanceHolder.INSTANCE;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .setUserId(resultSet.getInt(1))
                .setEmail(resultSet.getString(2))
                .setName(resultSet.getString(3))
                .setSurname(resultSet.getString(4))
                .setPhone(resultSet.getString(5))
                .setIsActivated(resultSet.getBoolean(6))
                .setRole(UserRole.valueOf(resultSet.getString(7).toUpperCase()))
                .build();
    }
}





