package com.railway.controller.command;

/**
 * The enum PagePath.
 */
public enum PagePath {
    INDEX_PAGE("index.jsp"),
    MAIN_PAGE("pages/main.jsp"),
    LOGIN_PAGE("pages/login.jsp"),
    REGISTER_PAGE("pages/register.jsp"),
    ERROR_PAGE("pages/error.jsp"),
    PROFILE_PAGE("pages/profile.jsp");

    private final String address;

    PagePath(String address) {
        this.address = address;
    }

    /**
     * Gets address.
     *
     * @return the address of page
     */
    public String getAddress() {
        return address;
    }
}
