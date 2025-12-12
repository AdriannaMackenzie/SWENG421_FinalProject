// Task.java
import java.time.LocalDate;

public class Task {
    private final String title;
    private final String category;
    private final LocalDate dueDate;
    private boolean completed;

    public Task(String title, String category, LocalDate dueDate) {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public void toggleCompleted() { completed = !completed; }

    @Override
    public String toString() {
        String s = completed ? "[✓] " : "[ ] ";
        return s + title + " (due " + dueDate + ")";
    }
}
