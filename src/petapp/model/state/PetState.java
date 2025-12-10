package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public interface PetState {

    void handleTick(BasePet pet);

    void handleTask(BasePet pet, Task task);
}