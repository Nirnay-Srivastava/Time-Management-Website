package com.taskpilot.taskmanager;

public class WorkTask extends Task {
    private int deadlineHoursLeft;

    public WorkTask(String id, String title, int estimatedMinutes, int priorityLevel, int deadlineHoursLeft) {
        super(id, title, estimatedMinutes, priorityLevel);
        this.deadlineHoursLeft = Math.max(1, deadlineHoursLeft);
    }

    public int getDeadlineHoursLeft() {
        return deadlineHoursLeft;
    }

    @Override
    public double calculateUrgencyScore() {
        return (getPriorityLevel() * 10.0) / (double) deadlineHoursLeft;
    }
}