package petapp.model.pet;

import petapp.model.task.Task;
import petapp.model.state.PetState;

public interface Pet
{

    void onTick();

    void applyTask(Task task);

    String getMoodLabel();

    PetState getState();
}
