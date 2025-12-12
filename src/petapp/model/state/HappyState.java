package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public class HappyState implements PetState
{

    @Override
    public PetState handleTick(BasePet pet)
    {
        // Over time, happiness slowly decays and boredom grows
        pet.setHappiness(pet.getHappiness() - 1);
        pet.setBoredom(pet.getBoredom() + 1);

        // If happiness falls or boredom climbs beyond boundary, drop to Neutral
        if (pet.getHappiness() < 70 || pet.getBoredom() > 20)
        {
            return new NeutralState();
        }
        return this;
    }

    @Override
    public PetState handleTask(BasePet pet, Task task)
    {
        // Tasks cheer the pet up and reduce boredom
        pet.setHappiness(pet.getHappiness() + 5);
        pet.setBoredom(pet.getBoredom() - 3);
        // stay happy
        return this;
    }
}
