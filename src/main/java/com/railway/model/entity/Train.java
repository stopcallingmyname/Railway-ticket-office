package com.railway.model.entity;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Train {
    private Integer id = -1;
    private String routeID;
    private Date date;

    private Time departureTime;
    private Time destinationTime;
    private Integer suitReserved;
    private Integer coupeReserved;
    private Integer berthReserved;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getRouteID() { return routeID; }
    public void setRouteID(String routeID) { this.routeID = routeID; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }
    public Time getDestinationTime() { return destinationTime; }
    public void setDestinationTime(Time destinationTime) { this.destinationTime = destinationTime; }
    public Integer getSuitReserved() { return suitReserved; }
    public void setSuitReserved(Integer suitReserved) { this.suitReserved = suitReserved; }
    public Integer getCoupeReserved() { return coupeReserved; }
    public void setCoupeReserved(Integer coupeReserved) { this.coupeReserved = coupeReserved; }
    public Integer getBerthReserved() { return berthReserved; }
    public void setBerthReserved(Integer berthReserved) { this.berthReserved = berthReserved; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Train train = (Train) o;
        return id.equals(train.id)
                && routeID.equals(train.routeID)
                && date.equals(train.date)
                && departureTime.equals(train.departureTime)
                && destinationTime.equals(train.destinationTime)
                && Objects.equals(suitReserved, train.suitReserved)
                && coupeReserved.equals(train.coupeReserved)
                && berthReserved.equals(train.berthReserved);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = result * 31 + routeID.hashCode();
        result = result * 31 + date.hashCode();
        result = result * 31 + departureTime.hashCode();
        result = result * 31 + destinationTime.hashCode();
        result = result * 31 + suitReserved;
        result = result * 31 + coupeReserved.hashCode();
        result = result * 31 + berthReserved.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Train{")
                .append(", id=").append(id)
                .append(", routeID=").append(routeID)
                .append(", date=").append(date)
                .append(", departureTime=").append(departureTime)
                .append(", destinationTime=").append(destinationTime)
                .append(", suitReserved=").append(suitReserved)
                .append(", coupeReserved=").append(coupeReserved)
                .append(", berthReserved=").append(berthReserved)
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
     * The Builder for Train.
     */
    public static class Builder {
        private final Train train;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            train = new Train();
        }

        /**
         * Sets train id.
         *
         * @param id the train id
         * @return the train id
         */
        public Builder setId(Integer id) {
            train.setId(id);
            return this;
        }

        /**
         * Sets route id.
         *
         * @param routeId the route id
         * @return the route id
         */
        public Builder setRouteId(String routeId) {
            train.setRouteID(routeId);
            return this;
        }

        /**
         * Sets date.
         *
         * @param date the date
         * @return the date
         */
        public Builder setDate(Date date) {
            train.setDate(date);
            return this;
        }

        /**
         * Sets departure time.
         *
         * @param time the departure time
         * @return the departure time
         */
        public Builder setDepartureTime(Time time) {
            train.setDepartureTime(time);
            return this;
        }

        /**
         * Sets destination time.
         *
         * @param time the destination time
         * @return the destination time
         */
        public Builder setDestinationTime(Time time) {
            train.setDestinationTime(time);
            return this;
        }

        /**
         * Sets suit reserved.
         *
         * @param suitReserved the suit reserved
         * @return the suit reserved
         */
        public Builder setSuitReserved(Integer suitReserved) {
            train.setSuitReserved(suitReserved);
            return this;
        }

        /**
         * Sets coupe reserved.
         *
         * @param coupeReserved the coupe reserved
         * @return the coupe reserved
         */
        public Builder setCoupeReserved(Integer coupeReserved) {
            train.setCoupeReserved(coupeReserved);
            return this;
        }

        /**
         * Sets berth reserved.
         *
         * @param berthReserved the berth reserved
         * @return the berth reserved
         */
        public Builder setBerthReserved(Integer berthReserved) {
            train.setBerthReserved(berthReserved);
            return this;
        }

        /**
         * Build train.
         *
         * @return the train
         */
        public Train build() {
            return train;
        }
    }
}
