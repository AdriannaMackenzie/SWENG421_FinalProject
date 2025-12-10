package petapp.model.filter;

import petapp.model.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TodayFilter implements TaskFilter
{

    @Override
    public List<Task> apply(List<Task> input)
    {
        if (input == null)
        {
            return List.of();
        }
        LocalDate today = LocalDate.now();
        return input.stream()
                .filter(t -> today.equals(t.getDueDate()))
                .collect(Collectors.toList());
    }
}
