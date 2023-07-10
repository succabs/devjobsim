package com.succabs.devjobsim.player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    private String name;
    private String address;
    private String phone;
    private String mail;

    private int stress;
    private int hunger;
    private int money;

    private Map<String, String> skills;

    public PlayerStats() {
        skills = new HashMap<>();
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void addSkill(String skill, String level) {
        skills.put(skill, level);
    }

    public String getSkillLevel(String skill) {
        return skills.get(skill);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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