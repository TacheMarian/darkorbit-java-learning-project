package com.company.entities;

public class Account {
    private int id;
    private String accountName;
    private String accountPassword;
    private int experience;
    private int uridium;
    private int credits;

    public Account(int id, String accountName, String accountPassword, int experience, int uridium, int credits) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.experience = experience;
        this.uridium = uridium;
        this.credits = credits;
    }

    public Account(String accountName, int experience, int uridium, int credits) {
        this.accountName = accountName;
        this.experience = experience;
        this.uridium = uridium;
        this.credits = credits;
    }

    public Account(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", experience=" + experience +
                ", uridium=" + uridium +
                ", credits=" + credits +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getUridium() {
        return uridium;
    }

    public void setUridium(int uridium) {
        this.uridium = uridium;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
