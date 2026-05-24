package edu.asu.sdt.tasktracker.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents one task in the task tracker.
 */
public class Task {
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private String category;
    private LocalDate dueDate;
    private TaskStatus status;

    public Task() {
        // Required by JSON serializers.
    }

    private Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.category = builder.category;
        this.dueDate = builder.dueDate;
        this.status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean containsKeyword(String keyword) {
        String normalizedKeyword = keyword.toLowerCase();
        return contains(title, normalizedKeyword)
                || contains(description, normalizedKeyword)
                || contains(category, normalizedKeyword);
    }

    private boolean contains(String value, String keyword) {
        return value != null && value.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task task)) {
            return false;
        }
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Builder for creating Task objects with readable setup code.
     */
    public static class Builder {
        private int id;
        private String title = "";
        private String description = "";
        private Priority priority = Priority.MEDIUM;
        private String category = "General";
        private LocalDate dueDate = LocalDate.now();
        private TaskStatus status = TaskStatus.TODO;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
