# PetAppController API Documentation

This document describes the API for `PetAppController` and related classes, focusing on how to connect GUI code to the backend logic. Use this as a reference for integrating GUI actions and displaying data.

---

## PetAppController

### Constructor
```
PetAppController(TaskRepository taskRepository, TaskFilterService filterService)
```
- **Purpose:** Initializes the controller with task and filter services.

### Theme Selection
```
void selectTheme(PetThemeChoice choice)
```
- **Purpose:** Called when the user selects a pet theme in the GUI.
- **Effect:** Sets up the pet, decorators, sprite, and scheduler.

### Task Management
```
void createTask(Task task)
```
- **Purpose:** Called when the user creates a new task in the GUI.

```
void completeTask(Task task)
```
- **Purpose:** Called when the user marks a task as complete.
- **Effect:** Updates task and pet state.

### Task Filtering
```
void updateActiveFilters(Set<TaskFilterType> filters)
```
- **Purpose:** Updates the set of active filters based on GUI selection.

```
List<Task> getFilteredTasks()
```
- **Purpose:** Returns the list of tasks after applying all active filters. Use this to update the task list view in the GUI.

### Focus Mode Controls
```
void startFocusMode()
void stopFocusMode()
```
- **Purpose:** Activates/deactivates focus mode for the pet.

### Read-Only Info for GUI
```
Pet getPet()
PetSprite getPetSprite()
int getStreakCount()
```
- **Purpose:** Provides current pet, sprite, and streak info for GUI display.

### Shutdown
```
void shutdown()
```
- **Purpose:** Stops the scheduler (call on app exit).

---

## Related Model Classes

### Task
- `String getTitle()`
- `TaskCategory getCategory()`
- `LocalDate getDueDate()`
- `boolean isCompleted()`
- `void markComplete()`

### TaskRepository
- `void addTask(Task t)`
- `List<Task> getAllTasks()`

### TaskFilterService
- `void setActiveFilters(List<TaskFilter> filters)`
- `List<Task> getFilteredTasks()`

### Pet (interface)
- `void onTick()`
- `void applyTask(Task task)`
- `String getMoodLabel()`
- `PetState getState()`

### PetSprite
- `Object getImage()`

---

## Integration Points for GUI
- **Theme selection:** Call `selectTheme` when user picks a theme.
- **Task creation:** Call `createTask` with new task from GUI.
- **Task completion:** Call `completeTask` when user marks done.
- **Task filtering:**
	- Call `updateActiveFilters(Set<TaskFilterType> filters)` when the user changes filter selections in the GUI.
	- Use `getFilteredTasks()` to retrieve the filtered task list for display.
- **Focus mode:** Call `startFocusMode`/`stopFocusMode` for focus UI.
- **Pet display:** Use `getPet`, `getPetSprite`, and `getStreakCount` for pet/status display.
- **Shutdown:** Call `shutdown` on app exit.

---

For more details, see the JavaDoc comments in each class.
