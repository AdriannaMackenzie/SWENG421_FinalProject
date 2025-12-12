// TodayFilter.java
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TodayFilter implements TaskFilter {
    @Override
    public List<Task> apply(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        return tasks.stream()
                .filter(t -> t.getDueDate().equals(today))
                .collect(Collectors.toList());
    }
}
