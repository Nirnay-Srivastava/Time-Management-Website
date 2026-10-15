package com.taskpilot.taskmanager;

public class StudyTask extends Task {
    private int energyRequired; // 1 to 5

    public StudyTask(String id, String title, int estimatedMinutes, int priorityLevel, int energyRequired) {
        super(id, title, estimatedMinutes, priorityLevel);
        this.energyRequired = energyRequired;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    @Override
    public double calculateUrgencyScore() {
        return (getPriorityLevel() * 2.0) + (energyRequired * 1.5);
    }
}