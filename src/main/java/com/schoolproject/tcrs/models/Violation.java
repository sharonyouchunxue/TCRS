package com.schoolproject.tcrs.models;

public class Violation {
    private int violationCode;
    private String description;
    private double fineAmount;

    public Violation(int violationCode, String description, double fineAmount) {
        this.violationCode = violationCode;
        this.description = description;
        this.fineAmount = fineAmount;
    }

    // Getters and setters
    public int getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(int violationCode) {
        this.violationCode = violationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
