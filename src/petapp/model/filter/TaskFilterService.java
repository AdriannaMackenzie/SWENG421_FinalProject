package petapp.model.filter;

import petapp.model.task.Task;
import petapp.model.task.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskFilterService
{

    private final TaskRepository taskRepo;
    private List<TaskFilter> activeFilters = new ArrayList<>();

    public TaskFilterService(TaskRepository taskRepo)
    {
        this.taskRepo = taskRepo;
    }

    public void setActiveFilters(List<TaskFilter> filters)
    {
        if (filters == null) {
            this.activeFilters = new ArrayList<>();
        } else {
            this.activeFilters = new ArrayList<>(filters);
        }
    }

    public List<Task> getFilteredTasks()
    {
        List<Task> result = new ArrayList<>(taskRepo.getAllTasks());
        for (TaskFilter filter : activeFilters)
        {
            result = filter.apply(result);
        }
        return result;
    }
}
