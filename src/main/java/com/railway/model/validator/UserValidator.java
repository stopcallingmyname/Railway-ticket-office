package com.railway.model.validator;

import com.railway.model.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class UserValidator.
 */
public final class UserValidator implements Validator{
    private static  final UserValidator INSTANCE = new UserValidator();
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private UserValidator() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of user validator
     */
    public static UserValidator getInstance() {
        return INSTANCE;
    }

    /**
     * Is email valid.
     *
     * @param email the email
     * @return the true if email valid and false if not
     */
    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean isValid(Object object) {
        if (object == null || object.getClass() != User.class)
            return false;
        User user = (User) object;
        return user.getEmail() != null
                && !user.getEmail().isEmpty()
                && user.getName() != null
                && !user.getName().isEmpty()
                && user.getSurname() != null
                && !user.getSurname().isEmpty()
                && user.getIsActivated() != null
                && user.getRole() != null;
    }
}
