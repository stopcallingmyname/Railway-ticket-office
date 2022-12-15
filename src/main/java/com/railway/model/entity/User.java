package com.railway.model.entity;

import java.security.PublicKey;

public class User {
    private Integer id = -1;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Boolean is_activated;
    private UserRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActivated() {
        return is_activated;
    }

    public void setIsActivated(Boolean is_activated) {
        this.is_activated = is_activated;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id.equals(user.id)
                && email.equals(user.email)
//                && name.equals(user.name) //useless imho
//                && surname.equals(user.surname)
                && phone.equals(user.phone)
                && role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = result * 31 + email.hashCode();
        result = result * 31 + name.hashCode();
        result = result * 31 + surname.hashCode();
        result = result * 31 + phone.hashCode();
        result = result * 31 + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("id=").append(id)
                .append(", email=").append(email)
                .append(", name=").append(name)
                .append(", surname=").append(surname)
                .append(", role=").append(role)
                .append(", phone=").append(phone)
                .append(", is_activated=").append(is_activated)
                .append('}');
        return sb.toString();
    }

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Builder for User.
     */
    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder setUserId(Integer id) {
            user.setId(id);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setName(String name) {
            user.setName(name);
            return this;
        }

        public Builder setSurname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public Builder setPhone(String phone) {
            user.setPhone(phone);
            return this;
        }

        public Builder setIsActivated(boolean is_activated) {
            user.setIsActivated(is_activated);
            return this;
        }

        public Builder setRole(UserRole role) {
            user.setRole(role);
            return this;
        }

        public User build() {
            return user;
        }
    }
}


