package com.succabs.devjobsim.gameLogic;

import java.util.Map;

public class Job {
    private String company;
    private String position;
    private String content;
    private int salary;
    private Map<String, String> skillNeeds;

    public Job(String company, String position, String content, int salary, Map<String, String> skills) {
        this.company = company;
        this.position = position;
        this.content = content;
        this.salary = salary;
        this.skillNeeds = skills;
    }

    public String getCompany() {
        return company;
    }

    public Map<String, String> getSkillneeds() {
        return skillNeeds;
    }

    public void addSkill(Job job, String skill, String level) {
        skillNeeds.put(skill, level);
    }

    public String getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    public int getSalary() {
        return salary;
    }
}
