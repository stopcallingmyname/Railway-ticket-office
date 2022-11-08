package com.railway.model.entity;
import java.sql.Date;

public class Train {
    private Integer id;
    private String routeID;
    private Date date;
    private Integer suitReserved;
    private Integer coupeReserved;
    private Integer berthReserved;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getRouteID() { return routeID; }
    public void setRouteID(String routeID) { this.routeID = routeID; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Integer getSuitReserved() { return suitReserved; }
    public void setSuitReserved(Integer suitReserved) { this.suitReserved = suitReserved; }
    public Integer getCoupeReserved() { return coupeReserved; }
    public void setCoupeReserved(Integer coupeReserved) { this.coupeReserved = coupeReserved; }
    public Integer getBerthReserved() { return berthReserved; }
    public void setBerthReserved(Integer berthReserved) { this.berthReserved = berthReserved; }
}
