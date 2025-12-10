package petapp.model.filter;

import petapp.model.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public class CompletionFilter implements TaskFilter
{

    private final boolean completed;

    public CompletionFilter(boolean completed)
    {
        this.completed = completed;
    }

    @Override
    public List<Task> apply(List<Task> input)
    {
        if (input == null)
        {
            return List.of();
        }
        return input.stream()
                .filter(t -> t.isCompleted() == completed)
                .collect(Collectors.toList());
    }
}
