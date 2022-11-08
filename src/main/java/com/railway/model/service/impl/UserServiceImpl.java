package com.railway.model.service.impl;

import com.railway.exception.DaoException;
import com.railway.exception.ServiceException;
import com.railway.model.dao.UserDAO;
import com.railway.model.dao.impl.UserDaoImpl;
import com.railway.model.entity.User;
import com.railway.model.entity.UserRole;
import com.railway.model.service.UserService;
import com.railway.model.validator.UserValidator;
import com.railway.util.security.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDAO userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {

    }

    private static final class UserServiceInstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance() {
        return UserServiceInstanceHolder.INSTANCE;
    }

    @Override
    public User signIn(String email, String password) throws ServiceException {
        if (isStringValid(email) && isStringValid(password)) {
            Optional<User> user;
            try {
                Optional<String> hashedPassword = userDao.findUserPasswordByEmail(email);
                if (hashedPassword.isPresent()) {
                    user = userDao.findUserByEmail(email);
                    if (user.isPresent() && PasswordEncryptor.getInstance().checkPassword(password, hashedPassword.get())) {
                        return user.get();
                    }
                    return user.get();
                }
            } catch (DaoException e) {
                logger.error("Can't handle signIn request at UserService", e);
                throw new ServiceException("Can't handle signIn request at UserService", e);
            }
        }
        logger.error("Invalid login or password");
        throw new ServiceException("Invalid login or password");
    }

    @Override
    public User signUp(String email, String name, String surname, String phone, String password, String repeatedPassword) throws ServiceException {
        if (!isStringValid(email, name, surname, phone, password, repeatedPassword)) {
            logger.error("Invalid parameters");
            throw new ServiceException("Invalid parameters");
        }

        User user = User.builder()
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setIsActivated(true)
                .setRole(UserRole.USER)
                .build();

        String hashedPassword = PasswordEncryptor.getInstance().encryptPassword(password);

        try {
            userDao.add(user, hashedPassword);
            return user;
        } catch (DaoException e) {
            logger.error("Can't handle signUp request at UserService", e);
            throw new ServiceException("Can't handle signUp request at UserService", e);
        }
    }

    @Override
    public User updateIsActive(User user, boolean is_active) throws ServiceException {
        if (!UserValidator.getInstance().isValid(user)) {
            logger.error("Invalid user");
            throw new ServiceException("Invalid user");
        }
        if (is_active == false) {
            logger.error("User not active");
            throw new ServiceException("User not active");
        }

        User updatedUser = buildUser(user.getId(), user.getEmail(), user.getName(),
                user.getSurname(), user.getPhone(), is_active, user.getRole());
        try {
            userDao.update(updatedUser);
        } catch (DaoException e) {
            logger.error("Can't handle updateStatus request at UserService", e);
            throw new ServiceException("Can't handle updateStatus request at UserService", e);
        }
        return updatedUser;
    }

    @Override
    public User updateRole(User user, UserRole userRole) throws ServiceException {
        if (!UserValidator.getInstance().isValid(user)) {
            logger.error("Invalid user");
            throw new ServiceException("Invalid user");
        }
        if (userRole == null) {
            logger.error("User role doesn't present");
            throw new ServiceException("User role doesn't present");
        }

        User updatedUser = buildUser(user.getId(), user.getEmail(), user.getName(),
                user.getSurname(), user.getPhone(), user.getIsActivated(), userRole);
        try {
            userDao.update(updatedUser);
        } catch (DaoException e) {
            logger.error("Can't handle updateRole request at UserService", e);
            throw new ServiceException("Can't handle updateRole request at UserService", e);
        }
        return updatedUser;
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        if (!isStringValid(email)) {
            logger.error("Login doesn't present");
            throw new ServiceException("Login doesn't present");
        }
        try {
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            } else {
                logger.error("User with email={} doesn't exist", email);
                throw new ServiceException("User with email=" + email + " doesn't exist");
            }
        } catch (DaoException e) {
            logger.error("User  with login {} doesn't exist", email, e);
            throw new ServiceException("User with email " + email + " doesn't exist");
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error("Can't handle findAll request at UserService", e);
            throw new ServiceException("Can't handle findAll request at UserService", e);
        }
        return users;
    }

    private User buildUser(Integer userId, String email, String name, String surname, String phone, boolean is_activated, UserRole userRole) {
        return User.builder()
                .setUserId(userId)
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setIsActivated(is_activated)
                .setRole(userRole)
                .build();
    }

    private boolean isStringValid(String... params) {
        for (String param : params) {
            if (param != null && !param.isEmpty())
                return true;
        }
        return false;
    }
}
