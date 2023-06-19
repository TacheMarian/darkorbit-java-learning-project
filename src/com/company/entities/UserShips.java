package com.company.entities;

public class UserShips {

    private int idUserShips;
    private int idAccounts;
    private int idShips;

    public UserShips(int idAccounts) {
        this.idAccounts = idAccounts;
    }

    public UserShips(int idAccounts, int idShips) {
        this.idAccounts = idAccounts;
        this.idShips = idShips;
    }

    @Override
    public String toString() {
        return "UserShips{" +
                "idUserShips=" + idUserShips +
                ", idAccounts=" + idAccounts +
                ", idShips=" + idShips +
                '}';
    }

    public int getIdUserShips() {
        return idUserShips;
    }

    public void setIdUserShips(int idUserShips) {
        this.idUserShips = idUserShips;
    }

    public int getIdAccounts() {
        return idAccounts;
    }

    public void setIdAccounts(int idAccounts) {
        this.idAccounts = idAccounts;
    }

    public int getIdShips() {
        return idShips;
    }

    public void setIdShips(int idShips) {
        this.idShips = idShips;
    }
}
