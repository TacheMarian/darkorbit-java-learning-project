package com.company.entities;

public class StarshipStats {
    private  int idStarshipStats;
    private String starshipName;
    private int damage;
    private int shield;
    private int hp;


    @Override
    public String toString() {
        return "StarshipStats{" +
                "idStarshipStats=" + idStarshipStats +
                ", starshipName='" + starshipName + '\'' +
                ", damage=" + damage +
                ", shield=" + shield +
                ", hp=" + hp +
                '}';
    }

    public int getIdStarshipStats() {
        return idStarshipStats;
    }

    public void setIdStarshipStats(int idStarshipStats) {
        this.idStarshipStats = idStarshipStats;
    }

    public String getStarshipName() {
        return starshipName;
    }

    public void setStarshipName(String starshipName) {
        this.starshipName = starshipName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
