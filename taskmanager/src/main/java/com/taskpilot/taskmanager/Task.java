package com.taskpilot.taskmanager;

public abstract class Task {
    private String id;
    private String title;
    private int estimatedMinutes;
    private int priorityLevel; // 1 to 5

    public Task(String id, String title, int estimatedMinutes, int priorityLevel) {
        this.id = id;
        this.title = title;
        this.estimatedMinutes = estimatedMinutes;
        this.priorityLevel = priorityLevel;
    }

    // Polymorphic method
    public abstract double calculateUrgencyScore();

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getEstimatedMinutes() { return estimatedMinutes; }
    public int getPriorityLevel() { return priorityLevel; }

    public double getUrgencyScore() {
        return calculateUrgencyScore();
    }
}