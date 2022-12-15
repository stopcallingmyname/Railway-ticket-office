package com.railway.controller.command;

/**
 * The enum PagePath.
 */
public enum PagePath {
    INDEX_PAGE("index.jsp"),
    MAIN_PAGE("pages/main.jsp"),
    LOGIN_PAGE("pages/login.jsp"),
    REGISTER_PAGE("pages/register.jsp"),
    PROFILE_PAGE("pages/profile.jsp"),
    GET_USERS_PAGE("pages/admin/get_users.jsp"),
    GET_ROUTES_LIST_PAGE("pages/get_routes_list.jsp"),
    GET_ROUTE_PAGE("pages/get_route.jsp"),

    ADD_TRAIN_PAGE("pages/admin/add_train.jsp"),
    ADD_ROUTE_PAGE("pages/admin/add_route.jsp"),

    ERROR_PAGE("pages/error.jsp");

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
