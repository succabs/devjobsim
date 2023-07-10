package com.succabs.devjobsim.player;

public class PlayerStats {
    private String name;
    private int stress;
    private int hunger;
    private int money;

    public String getName() {
        return name;
    }

    public int getStress() {
        return stress;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
