package com.railway.model.entity;
import java.sql.Time;

public class Route {
    private String id;
    private String departureStation;
    private String destinationStation;
    private Time departureTime;
    private Time destinationTime;
    private int suitPlaces;
    private int coupePlaces;
    private int berthPlaces;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDepartureStation() { return departureStation; }
    public void setDepartureStation(String departureStation) { this.departureStation = departureStation; }
    public String getDestinationStation() { return destinationStation; }
    public void setDestinationStation(String destinationStation) { this.destinationStation = destinationStation; }
    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }
    public Time getDestinationTime() { return destinationTime; }
    public void setDestinationTime(Time destinationTime) { this.destinationTime = destinationTime; }
    public int getSuitPlaces() { return suitPlaces; }
    public void setSuitPlaces(int suitPlaces) { this.suitPlaces = suitPlaces; }
    public int getCoupePlaces() { return coupePlaces; }
    public void setCoupePlaces(int coupePlaces) { this.coupePlaces = coupePlaces; }
    public int getBerthPlaces() { return berthPlaces; }
    public void setBerthPlaces(int berthPlaces) { this.berthPlaces = berthPlaces; }
}
