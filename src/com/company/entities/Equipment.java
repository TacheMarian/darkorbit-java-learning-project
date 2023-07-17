package com.company.entities;

public class Equipment {
    private int idEquipment;
    private int lf1;
    private int mp1;
    private int lf2;
    private int lf3;
    private int sg3NA01;
    private int sg3NA02;
    private int sg3NA03;
    private int sg3NB01;
    private int sg3NB02;

    public Equipment() {
    }

    public Equipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    @Override
    public String toString() {
        return "UserInventory{" +
                "idUserInventory=" + idEquipment +
                ", lf1=" + lf1 +
                ", mp1=" + mp1 +
                ", lf2=" + lf2 +
                ", lf3=" + lf3 +
                ", sg3NA01=" + sg3NA01 +
                ", sg3NA02=" + sg3NA02 +
                ", sg3NA03=" + sg3NA03 +
                ", sg3NB01=" + sg3NB01 +
                ", sg3NB02=" + sg3NB02 +
                '}';
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idUserInventory) {
        this.idEquipment = idUserInventory;
    }

    public int getLf1() {
        return lf1;
    }

    public void setLf1(int lf1) {
        this.lf1 = lf1;
    }

    public int getMp1() {
        return mp1;
    }

    public void setMp1(int mp1) {
        this.mp1 = mp1;
    }

    public int getLf2() {
        return lf2;
    }

    public void setLf2(int lf2) {
        this.lf2 = lf2;
    }

    public int getLf3() {
        return lf3;
    }

    public void setLf3(int lf3) {
        this.lf3 = lf3;
    }

    public int getSg3NA01() {
        return sg3NA01;
    }

    public void setSg3NA01(int sg3NA01) {
        this.sg3NA01 = sg3NA01;
    }

    public int getSg3NA02() {
        return sg3NA02;
    }

    public void setSg3NA02(int sg3NA02) {
        this.sg3NA02 = sg3NA02;
    }

    public int getSg3NA03() {
        return sg3NA03;
    }

    public void setSg3NA03(int sg3NA03) {
        this.sg3NA03 = sg3NA03;
    }

    public int getSg3NB01() {
        return sg3NB01;
    }

    public void setSg3NB01(int sg3NB01) {
        this.sg3NB01 = sg3NB01;
    }

    public int getSg3NB02() {
        return sg3NB02;
    }

    public void setSg3NB02(int sg3NB02) {
        this.sg3NB02 = sg3NB02;
    }
}
