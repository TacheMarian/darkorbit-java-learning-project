package com.company.entities;

public class Laser {
    private int idLasers;
    private String name;
    private int damage;
    private int priceCredits;
    private int priceUridium;

    public Laser() {
    }

    @Override
    public String toString() {
        return "Laser{" +
                "idLasers=" + idLasers +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", priceCredits=" + priceCredits +
                ", priceUridium=" + priceUridium +
                '}';
    }

    public int getIdLasers() {
        return idLasers;
    }

    public void setIdLasers(int idLasers) {
        this.idLasers = idLasers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPriceCredits() {
        return priceCredits;
    }

    public void setPriceCredits(int priceCredits) {
        this.priceCredits = priceCredits;
    }

    public int getPriceUridium() {
        return priceUridium;
    }

    public void setPriceUridium(int priceUridium) {
        this.priceUridium = priceUridium;
    }
}
