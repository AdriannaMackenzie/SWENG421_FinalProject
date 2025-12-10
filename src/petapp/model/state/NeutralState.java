package petapp.model.state;

import petapp.model.pet.BasePet;
import petapp.model.task.Task;

public class NeutralState implements PetState
{

    @Override
    public void handleTick(BasePet pet)
    {
        // Over time, happiness decays and boredom grows faster than in Happy state
        pet.setHappiness(pet.getHappiness() - 2);
        pet.setBoredom(pet.getBoredom() + 2);

        // If happiness falls or boredom climbs beyond boundary, go to Bored
        if (pet.getHappiness() < 40 || pet.getBoredom() > 40)
        {
            pet.setState(new BoredState());
        }
    }

    @Override
    public void handleTask(BasePet pet, Task task)
    {
        // Tasks increase happiness and reduce boredom moderately
        pet.setHappiness(pet.getHappiness() + 4);
        pet.setBoredom(pet.getBoredom() - 4);

        if (pet.getHappiness() > 69 && pet.getBoredom() < 21)
        {
        pet.setState(new HappyState());
        }
    }
}
