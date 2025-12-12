package petapp.controller;

import petapp.model.pet.Pet;
import petapp.model.pet.BasePet;
import petapp.model.decorator.FocusModeDecorator;
import petapp.model.decorator.StreakBonusDecorator;
import petapp.model.task.Task;
import petapp.model.task.TaskRepository;
import petapp.model.filter.TaskFilterService;
import petapp.model.filter.TaskFilterType;
import petapp.model.theme.ThemeFactory;
import petapp.model.theme.CatThemeFactory;
import petapp.model.theme.DogThemeFactory;
import petapp.model.theme.LionThemeFactory;
import petapp.model.theme.PetSprite;
import petapp.model.theme.PetThemeChoice;
import petapp.scheduler.Scheduler;

import java.util.List;

public class PetAppController
{

    private Pet pet;                          // outermost decorator
    private FocusModeDecorator focusDecorator;
    private StreakBonusDecorator streakDecorator;

    private ThemeFactory themeFactory;
    private PetSprite petSprite;

    private final TaskRepository taskRepository;
    private final TaskFilterService filterService;
    private Scheduler scheduler;

    private PetThemeChoice currentTheme;
    private Set<TaskFilterType> currentFilterTypes;

    public PetAppController(TaskRepository taskRepository,
                            TaskFilterService filterService)
    {
        this.taskRepository = taskRepository;
        this.filterService = filterService;
    }

    // Called when user selects a theme in the GUI
    public void selectTheme(PetThemeChoice choice)
    {
        this.currentTheme = choice;
        this.themeFactory = createFactory(choice);

        // Create base pet from factory
        Pet base = themeFactory.createPet();

        // Wrap with decorators: base -> streak -> focus
        this.streakDecorator = new StreakBonusDecorator(base);
        this.focusDecorator = new FocusModeDecorator(streakDecorator);

        // The pet reference always points to the outermost decorator
        this.pet = focusDecorator;

        // Create sprite from factory
        this.petSprite = themeFactory.createPetSprite();

        // (Re)start scheduler with the new pet
        if (scheduler != null)
        {
            scheduler.stop();
        }
        this.scheduler = new Scheduler(pet, 5000);
        scheduler.start();
    }

    private ThemeFactory createFactory(PetThemeChoice choice)
    {
        return switch (choice)
        {
            case CAT -> new CatThemeFactory();
            case DOG -> new DogThemeFactory();
            case LION -> new LionThemeFactory();
        };
    }

    // Called by the Create Task button in the GUI
    public void createTask(Task task)
    {
        taskRepository.addTask(task);
    }

    // Called by the Done button when the user marks a task as complete
    public void completeTask(Task task)
    {
        // Mark the task as completed so filters and GUI can see it
        task.markComplete(true);

        // Now treat this as a completion event for the pet
        pet.applyTask(task);
    }

    public void updateActiveFilters(Set<TaskFilterType> filters) 
    {
        this.currentFilterTypes = filters;
        filterService.setActiveFilters(filters);
    }

    // Apply a chosen filter to the task list
    public List<Task> getFilteredTasks()
    {
       return filterService.getFilteredTasks(taskRepository.getAllTasks());  
    }

    // Focus mode controls for GUI
    public void startFocusMode()
    {
        if (focusDecorator != null)
        {
            focusDecorator.setFocusActive(true);
        }
    }

    public void stopFocusMode()
    {
        if (focusDecorator != null)
        {
            focusDecorator.setFocusActive(false);
        }
    }

    // Expose read only info to the GUI

    public Pet getPet()
    {
        return pet;
    }

    public PetSprite getPetSprite()
    {
        return petSprite;
    }

    public int getStreakCount()
    {
        return streakDecorator != null ? streakDecorator.getStreakCount() : 0;
    }

    public void shutdown()
    {
        if (scheduler != null) {
            scheduler.stop();
        }
    }
}