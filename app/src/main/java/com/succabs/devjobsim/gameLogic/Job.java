package com.succabs.devjobsim.gameLogic;

public class Job {
    private String company;
    private String position;
    private String content;
    private int salary;

    public Job(String company, String position, String content, int salary) {
        this.company = company;
        this.position = position;
        this.content = content;
        this.salary = salary;
    }

    public String getCompany() {
        return company;
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
