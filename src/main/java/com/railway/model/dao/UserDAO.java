package com.railway.model.dao;

import com.railway.exception.DaoException;
import com.railway.model.entity.User;
import java.util.Optional;
import java.util.List;

public interface UserDAO {
    int add(User user, String hashedPassword) throws DaoException;
    int update(User user) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    Optional<String> findUserPasswordByEmail(String email) throws DaoException;
    List<User> findAll() throws DaoException;
}
