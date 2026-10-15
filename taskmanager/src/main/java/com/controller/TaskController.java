package com.controller;

import com.taskpilot.taskmanager.Task;
import com.taskpilot.taskmanager.WorkTask;
import com.taskpilot.taskmanager.StudyTask;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new WorkTask(UUID.randomUUID().toString(), "Prepare Presentation Deck", 60, 5, 3));
        tasks.add(new StudyTask(UUID.randomUUID().toString(), "DSA Binary Search Practice", 45, 4, 4));
        tasks.add(new WorkTask(UUID.randomUUID().toString(), "Send Lab Status Report", 20, 2, 24));
    }

    @GetMapping
    public List<Task> getSortedTasks() {
        tasks.sort(Comparator.comparingDouble(Task::calculateUrgencyScore).reversed());
        return tasks;
    }

    @PostMapping("/work")
    public Task createWorkTask(@RequestBody Map<String, Object> body) {
        String title = body.get("title").toString();
        int duration = Integer.parseInt(body.get("duration").toString());
        int priority = Integer.parseInt(body.get("priority").toString());
        int deadline = Integer.parseInt(body.get("deadlineHours").toString());

        WorkTask task = new WorkTask(UUID.randomUUID().toString(), title, duration, priority, deadline);
        tasks.add(task);
        return task;
    }

    @PostMapping("/study")
    public Task createStudyTask(@RequestBody Map<String, Object> body) {
        String title = body.get("title").toString();
        int duration = Integer.parseInt(body.get("duration").toString());
        int priority = Integer.parseInt(body.get("priority").toString());
        int energy = Integer.parseInt(body.get("energy").toString());

        StudyTask task = new StudyTask(UUID.randomUUID().toString(), title, duration, priority, energy);
        tasks.add(task);
        return task;
    }
}