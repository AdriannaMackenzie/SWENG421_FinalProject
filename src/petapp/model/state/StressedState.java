package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public class StressedState implements PetState
{

    @Override
    public PetState handleTick(BasePet pet)
    {
        // When stressed, happiness drops quickly
        pet.setHappiness(pet.getHappiness() - 4);
        pet.setBoredom(pet.getBoredom() + 1);
        // stay stressed
        return this;
    }

    @Override
    public PetState handleTask(BasePet pet, Task task)
    {
        // Tasks can help, but not as much as in Bored
        pet.setHappiness(pet.getHappiness() + 5);
        pet.setBoredom(pet.getBoredom() - 3);

        if (pet.getHappiness() > 19 && pet.getBoredom() < 71)
        {
            return new BoredState();
        }
        return this;
    }
}
