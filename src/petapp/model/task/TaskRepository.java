package petapp.model.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskRepository
{

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task t)
    {
        if (t != null) {
            tasks.add(t);
        }
    }

    public List<Task> getAllTasks()
    {
        return Collections.unmodifiableList(tasks);
    }
}