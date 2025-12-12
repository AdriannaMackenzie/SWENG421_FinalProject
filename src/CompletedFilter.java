// CompletedFilter.java
import java.util.List;
import java.util.stream.Collectors;

public class CompletedFilter implements TaskFilter {
    @Override
    public List<Task> apply(List<Task> tasks) {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }
}
