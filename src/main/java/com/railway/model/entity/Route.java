package com.railway.model.entity;
import java.sql.Time;

public class Route {
    private String id;
    private String departureStation;
    private String destinationStation;

    /**
     * Gets route id.
     *
     * @return the route id
     */
    public String getId() { return id; }
    /**
     * Sets route id.
     *
     * @param id the route id
     */
    public void setId(String id) { this.id = id; }
    /**
     * Gets departure station.
     *
     * @return the departure station
     */
    public String getDepartureStation() { return departureStation; }
    /**
     * Sets departure station.
     *
     * @param  departureStation the departure station
     */
    public void setDepartureStation(String departureStation) { this.departureStation = departureStation; }
    /**
     * Gets destination station.
     *
     * @return the destination station
     */
    public String getDestinationStation() { return destinationStation; }
    /**
     * Sets destination station.
     *
     * @return the destination station
     */
    public void setDestinationStation(String destinationStation) { this.destinationStation = destinationStation; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route{")
                .append("id=").append(id)
                .append(", departureStation=").append(departureStation)
                .append(", destinationStation=").append(destinationStation)
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

    public static class Builder {
        private final Route route;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            route = new Route();
        }

        /**
         * Sets route id.
         *
         * @param routeId the route id
         * @return the route id
         */
        public Builder setRouteId(String routeId) {
            route.setId(routeId);
            return this;
        }

        /**
         * Sets departure station.
         *
         * @param name the name
         * @return the departure station
         */
        public Builder setDepartureStation(String name) {
            route.setDepartureStation(name);
            return this;
        }

        /**
         * Sets departure station.
         *
         * @param name the name
         * @return the departure station
         */
        public Builder setDestinationStation(String name) {
            route.setDestinationStation(name);
            return this;
        }

        /**
         * Build genre.
         *
         * @return the genre
         */
        public Route build() {
            return route;
        }
    }
}
