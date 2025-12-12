// TaskManager.java  (holds tasks + uses filters)
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public List<Task> getFilteredTasks(TaskFilter filter) {
        return filter.apply(tasks);
    }
}
