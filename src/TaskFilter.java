// TaskFilter.java  (Filter pattern interface)
import java.util.List;

public interface TaskFilter {
    List<Task> apply(List<Task> tasks);
}
