package com.company.entities;

public class Generator {
    private int idGenerators;
    private String name;
    private int shield;
    private int priceCredits;
    private int priceUridium;

    public Generator() {
    }

    @Override
    public String toString() {
        return "Generator{" +
                "idGenerators=" + idGenerators +
                ", name='" + name + '\'' +
                ", shield=" + shield +
                ", priceCredits=" + priceCredits +
                ", priceUridium=" + priceUridium +
                '}';
    }

    public int getIdGenerators() {
        return idGenerators;
    }

    public void setIdGenerators(int idGenerators) {
        this.idGenerators = idGenerators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
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
