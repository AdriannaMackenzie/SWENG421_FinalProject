package petapp.model.task;

import java.time.LocalDate;

public class Task
{

    private String title;
    private TaskCategory category;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String title, TaskCategory category, LocalDate dueDate)
    {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getTitle()
    {
        return title;
    }

    public TaskCategory getCategory()
    {
        return category;
    }

    public LocalDate getDueDate()
    {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markComplete()
    {
        this.completed = true;
    }
}
