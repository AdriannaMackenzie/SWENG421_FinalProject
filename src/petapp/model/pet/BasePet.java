package petapp.model.pet;

import petapp.model.state.HappyState;
import petapp.model.state.PetState;
import petapp.model.task.Task;

public class BasePet implements Pet
{

    private int happiness;
    private int energy;
    private int boredom;
    private PetState currentState;

    public BasePet()
    {
        this.happiness = 70;
        this.energy = 70;
        this.boredom = 0;
        this.currentState = new HappyState();
    }

    @Override
    public void onTick()
    {
        currentState.handleTick(this);
    }

    @Override
    public void applyTask(Task task)
    {
        currentState.handleTask(this, task);
    }

    @Override
    public String getMoodLabel()
    {
        // You can refine this later if you want nicer labels
        return currentState.getClass().getSimpleName();
    }

    @Override
    public PetState getState()
    {
        return currentState;
    }

    public void setState(PetState state)
    {
        this.currentState = state;
    }

    public int getHappiness()
    {
        return happiness;
    }

    public void setHappiness(int happiness)
    {
        this.happiness = clamp(happiness);
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setEnergy(int energy)
    {
        this.energy = clamp(energy);
    }

    public int getBoredom()
    {
        return boredom;
    }

    public void setBoredom(int boredom)
    {
        this.boredom = clamp(boredom);
    }

    private int clamp(int value)
    {
        if (value < 0)
        {
            return 0;
        }
        if (value > 100)
        {
            return 100;
        }
        return value;
    }
}
