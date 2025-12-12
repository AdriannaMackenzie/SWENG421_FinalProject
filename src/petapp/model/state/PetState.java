package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public interface PetState
{
    PetState handleTick(BasePet pet);
    PetState handleTask(BasePet pet, Task task);
}