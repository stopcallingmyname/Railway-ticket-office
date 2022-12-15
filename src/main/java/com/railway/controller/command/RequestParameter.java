package com.railway.controller.command;

/**
 * The class RequestParameter that stores request parameter names.
 */
public final class RequestParameter {
    public static final String COMMAND = "command";
    public static final String SEARCH_QUERY = "search_query";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String SURNAME = "surname";
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String REPEATED_PASSWORD = "repeated_password";

    public static final String TRAIN_ID = "train_id";
    public static final String TRAIN_ROUTE_ID = "train_route_id";
    public static final String TRAIN_DATE = "train_date";
    public static final String TRAIN_DEPARTURE_TIME = "train_departure_time";
    public static final String TRAIN_DESTINATION_TIME = "train_destination_time";
    public static final String TRAIN_SUIT_RESERVED = "train_suit_reserved";
    public static final String TRAIN_COUPE_RESERVED = "train_coupe_reserved";
    public static final String TRAIN_BERTH_RESERVED = "train_berth_reserved";

    public static final String ROUTE_ID = "route_id";
    public static final String ROUTE_DEPARTURE_STATION = "route_departure_station";
    public static final String ROUTE_DESTINATION_STATION = "route_destination_station";

    private RequestParameter() {

    }
}
