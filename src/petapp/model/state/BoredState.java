package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public class BoredState implements PetState
{

    @Override
    public PetState handleTick(BasePet pet)
    {
        // Bored pet gets more bored and less happy per tick than in Neutral
        pet.setHappiness(pet.getHappiness() - 3);
        pet.setBoredom(pet.getBoredom() + 3);

        // Check for transitions
        if (pet.getHappiness() < 20 || pet.getBoredom() > 70)
        {
            return new HappyState();
        }
        return this;
    }

    @Override
    public PetState handleTask(BasePet pet, Task task)
    {
        // Tasks are very helpful when bored
        pet.setHappiness(pet.getHappiness() + 8);
        pet.setBoredom(pet.getBoredom() - 6);

    if (pet.getHappiness() > 39 && pet.getBoredom() < 41)
    {
        return new NeutralState();
    }
    return this;
    }

}
