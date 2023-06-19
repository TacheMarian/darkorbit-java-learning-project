package com.company.entities;

public class Ship {
    private int shipId;
    private String shipName;
    private int shipHp;
    private int shipsLaserSlots;
    private int shipsGenSlots;
    private int shipPriceCredits;
    private int shipPriceUridium;

    public Ship(String shipName) {
        this.shipName = shipName;
    }

    public Ship() {
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipId=" + shipId +
                ", shipName='" + shipName + '\'' +
                ", shipHp=" + shipHp +
                ", shipsLaserSlots=" + shipsLaserSlots +
                ", shipsGenSlots=" + shipsGenSlots +
                ", shipPriceCredits=" + shipPriceCredits +
                ", shipPriceUridium=" + shipPriceUridium +
                '}';
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipHp() {
        return shipHp;
    }

    public void setShipHp(int shipHp) {
        this.shipHp = shipHp;
    }

    public int getShipsLaserSlots() {
        return shipsLaserSlots;
    }

    public void setShipsLaserSlots(int shipsLaserSlots) {
        this.shipsLaserSlots = shipsLaserSlots;
    }

    public int getShipsGenSlots() {
        return shipsGenSlots;
    }

    public void setShipsGenSlots(int shipsGenSlots) {
        this.shipsGenSlots = shipsGenSlots;
    }

    public int getShipPriceCredits() {
        return shipPriceCredits;
    }

    public void setShipPriceCredits(int shipPriceCredits) {
        this.shipPriceCredits = shipPriceCredits;
    }

    public int getShipPriceUridium() {
        return shipPriceUridium;
    }

    public void setShipPriceUridium(int shipPriceUridium) {
        this.shipPriceUridium = shipPriceUridium;
    }
}
