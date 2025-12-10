package petapp.model.decorator;

import petapp.model.pet.Pet;
import petapp.model.state.PetState;
import petapp.model.task.Task;

public abstract class PetDecorator implements Pet
{

    protected final Pet innerPet;

    protected PetDecorator(Pet innerPet)
    {
        this.innerPet = innerPet;
    }

    @Override
    public void onTick()
    {
        innerPet.onTick();
    }

    @Override
    public void applyTask(Task task)
    {
        innerPet.applyTask(task);
    }

    @Override
    public String getMoodLabel()
    {
        return innerPet.getMoodLabel();
    }

    @Override
    public PetState getState()
    {
        return innerPet.getState();
    }
}
