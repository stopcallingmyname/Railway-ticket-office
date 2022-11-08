package com.railway.model.service;

import com.railway.exception.ServiceException;
import com.railway.model.entity.User;
import com.railway.model.entity.UserRole;

import java.util.List;

public interface UserService {
    User signIn(String email, String password) throws ServiceException;
    User signUp(String email, String name, String surname, String phone, String password, String repeatedPassword) throws ServiceException;
    User updateIsActive(User user, boolean is_active) throws ServiceException;
    User updateRole(User user, UserRole userRole) throws ServiceException;
    User findUserByEmail(String email) throws ServiceException;
    List<User> findAll() throws ServiceException;
}
