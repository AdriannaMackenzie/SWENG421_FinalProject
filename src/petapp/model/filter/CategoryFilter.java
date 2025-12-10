package petapp.model.filter;

import petapp.model.task.Task;
import petapp.model.task.TaskCategory;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements TaskFilter
{

    private final TaskCategory category;

    public CategoryFilter(TaskCategory category)
    {
        this.category = category;
    }

    @Override
    public List<Task> apply(List<Task> input)
    {
        if (input == null)
        {
            return List.of();
        }
        return input.stream()
                .filter(t -> t.getCategory() == category)
                .collect(Collectors.toList());
    }
}
