package petapp.model.state;

import petapp.model.pet.Pet;
import petapp.model.task.Task;

public interface PetState
{

    void handleTick(Pet pet);

    void handleTask(Pet pet, Task task);
}
