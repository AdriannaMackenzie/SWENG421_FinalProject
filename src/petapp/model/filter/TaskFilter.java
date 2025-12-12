package petapp.model.filter;

import petapp.model.task.Task;

import java.util.List;

public interface TaskFilter
{

    List<Task> apply(List<Task> input);
}
